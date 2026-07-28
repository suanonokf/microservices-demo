package com.example.AI.Service.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queues.moderation-queue}")
    private String moderationSubmissionQueue;
    @Value("${rabbitmq.exchanges.moderation-exchange}")
    private String moderationExchange;
    @Value("${rabbitmq.routing-keys.moderation-key}")
    private String moderationRoutingKey;
    @Bean
    public MessageConverter messageConverter(){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

        // Trust DTO package
        typeMapper.setTrustedPackages("com.example.content_submission_service.Dto");

        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }
    @Bean
    public Queue contentQueue(){
        return QueueBuilder
                .durable(moderationSubmissionQueue)
                .withArgument("x-message-ttl",120000)
                .withArgument("x-max-length",10000)
                .build();

    }

    // Create Exchange
    @Bean
    public TopicExchange contentExchange(){
        return ExchangeBuilder.topicExchange(moderationExchange).build();
    }
    // Create Binding between queue and exchange

    @Bean
    public Binding createBinding(
            Queue contentQueue,
            TopicExchange contentExchange
    ){
        return BindingBuilder
                .bind(contentQueue)
                .to(contentExchange)
                .with(moderationRoutingKey);

    }
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returned -> {
            System.err.println("Exchange: "+returned.getExchange());
            System.err.println("Reply Text: "+returned.getReplyText());
            System.err.println("Message: "+returned.getMessage());
        });

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                System.out.println("Message published to exchange");
            }
            else{
                System.out.println("Failure: "+cause);
            }
        });

        return rabbitTemplate;
    }
}
