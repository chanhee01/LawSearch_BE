package com.example.lawSearch.domain.gpt.service;

import com.example.lawSearch.domain.gpt.dto.request.GptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GptService {

    public String getBills(GptRequestDto request) {
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://13.209.249.200:5000/api/send-data";
        // 서비스 배포 종료 후 깃허브 코드 public으로 바꿀 예정. 인바운드 규칙을 통해서 접근을 제안

        HttpEntity<GptRequestDto> entity = new HttpEntity<>(request);

        // Flask 서버에 POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(flaskUrl, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
