package com.example.AI.Service.Config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes =(ServletRequestAttributes) requestAttributes;
        if(requestAttributes!=null){
            HttpServletRequest request = attributes.getRequest();
            String header = request.getHeader("Authorization");
            if(header!=null){
                requestTemplate.header("Authorization",header);
            }
            else{
                System.out.println("No Authorization Header in the request");
            }
        }
    }
}
