package com.example.activities.webclient;

import com.example.activities.model.dto_input.ActivityInputDTO;
import org.springframework.web.client.RestTemplate;

public class WebClient {

    private static final String URL_BORED_API = "https://www.boredapi.com/api/activity";
    RestTemplate restTemplate = new RestTemplate();

    public ActivityInputDTO callApi() {
        return restTemplate.getForObject(
                URL_BORED_API, ActivityInputDTO.class);
    }
}