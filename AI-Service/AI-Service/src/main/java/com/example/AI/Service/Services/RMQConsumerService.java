package com.example.AI.Service.Services;

import com.example.AI.Service.DTO.ContentRecord;
import com.example.AI.Service.DTO.MessageToxicityResponse;
import com.example.AI.Service.DTO.ReputationResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class RMQConsumerService {
    private static final Logger log = LoggerFactory.getLogger(RMQConsumerService.class);
    @Value("${ai.python-service.url}")
    private String textToxicityUrl;
    @Value("${rabbitmq.exchanges.moderation-exchange}")
    private String moderationExchange;
    @Value("${rabbitmq.routing-keys.moderation-key}")
    private String moderationRoutingKey;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserReputationInfo userReputationInfo;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "${rabbitmq.queues.content-submission}")
    public void receiveContent(ContentRecord contentRecord){
        log.info("Message received\nSend by: "
                +contentRecord.getUsername()
                +"\nContent status: "
        +contentRecord.getContentStatus()+"\nContent data: "+contentRecord.getTextData());
        log.info("Analyzing message toxicity level...");
        try{
            int textToxicityScore = analyzeText(contentRecord);
            boolean willPublish =decideToPublish(textToxicityScore,contentRecord);
            MessageToxicityResponse messageToxicityResponse =new MessageToxicityResponse(
                    contentRecord.getUsername(),
                    contentRecord.getContentId(),
                    contentRecord.getContentCategory(),
                    textToxicityScore,
                    willPublish
            );
            rabbitTemplate.convertAndSend(
                    moderationExchange,
                    moderationRoutingKey,
                    messageToxicityResponse
            );
            log.info("Review published successfully");
        }
        catch (Exception e){
            log.error("Error: "+e.getMessage());
        }
    }
    private int analyzeText(ContentRecord contentRecord){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> request = Map.of("text",contentRecord.getTextData());
        HttpEntity<Map<String,String>> requestBody = new HttpEntity<>(request,headers);
        ResponseEntity<AITEstResponse> responseEntity = restTemplate.postForEntity(
                textToxicityUrl,
                requestBody,
                AITEstResponse.class
        );

        return responseEntity.getBody().getToxicityScore();
    }
    private boolean decideToPublish(int toxicityScore,ContentRecord contentRecord){
        String username = contentRecord.getUsername();
        ReputationResponse userReputation = userReputationInfo.getUserReputation(username, username);
        int reputationScore = userReputation.reputationScore();
        if(reputationScore >= 80){
            if(toxicityScore<= 70){
                return true;
            }
            else if(toxicityScore<=95){
                return true;
            }
            else{
                return false;
            }
        }
        else if(reputationScore >= 50){
            if(toxicityScore<=80) return true;
            else if (toxicityScore<=90) return true;
            else return false;
        }
        else{
            if(toxicityScore<=90) return true;
            else if (toxicityScore<95) return true;
            else return false;
        }
    }
}

class AITEstResponse{
    private String label;
    private int toxicityScore;
    private Object rawResults;

    public AITEstResponse() {
    }

    public String getLabel() {
        return label;
    }

    public int getToxicityScore() {
        return toxicityScore;
    }

    public Object getRawResults() {
        return rawResults;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setToxicityScore(int toxicityScore) {
        this.toxicityScore = toxicityScore;
    }

    public void setRawResults(Object rawResults) {
        this.rawResults = rawResults;
    }
}
