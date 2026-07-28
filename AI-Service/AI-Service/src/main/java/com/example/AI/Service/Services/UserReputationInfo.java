package com.example.AI.Service.Services;

import com.example.AI.Service.Config.FeignConfig;
import com.example.AI.Service.DTO.ReputationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service",configuration = FeignConfig.class)
public interface UserReputationInfo {
    @PostMapping(path = "/reputation-service/getReputation")
    ReputationResponse getUserReputation(@RequestBody String username,
                                         @RequestHeader("X-User-id")String usernameAsId);
}
