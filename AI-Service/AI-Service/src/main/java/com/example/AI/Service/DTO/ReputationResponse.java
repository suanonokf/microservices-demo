package com.example.AI.Service.DTO;

import com.example.AI.Service.Enum.Trustworthiness;

public record ReputationResponse (
        String userName,
        int reputationScore,
        Trustworthiness trustworthiness
){
}
