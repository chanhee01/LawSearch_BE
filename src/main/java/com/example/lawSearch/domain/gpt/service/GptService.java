package com.example.lawSearch.domain.gpt.service;

import com.example.lawSearch.domain.gpt.dto.request.GptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GptService {

    public String getBills(GptRequestDto request) {
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://43.203.106.131:5000/api/send-data";

        HttpEntity<GptRequestDto> entity = new HttpEntity<>(request);

        // Flask 서버에 POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(flaskUrl, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
