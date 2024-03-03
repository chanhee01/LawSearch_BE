package com.example.lawSearch.domain.gpt.service;

import com.example.lawSearch.domain.gpt.dto.request.GptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GptService {

    @Value("${gpt_url}")
    private String gpt_url;

    public String getBills(GptRequestDto request) {
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = gpt_url;

        HttpEntity<GptRequestDto> entity = new HttpEntity<>(request);

        // Flask 서버에 POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(flaskUrl, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
