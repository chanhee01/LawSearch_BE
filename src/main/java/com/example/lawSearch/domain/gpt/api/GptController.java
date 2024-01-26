package com.example.lawSearch.domain.gpt.api;

import com.example.lawSearch.domain.gpt.dto.request.GptRequestDto;
import com.example.lawSearch.domain.gpt.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gpt")
public class GptController {

    private final GptService gptService;

    @PostMapping(value = "", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> getBills(@RequestBody GptRequestDto data) {
        String response = gptService.getBills(data);
        return ResponseEntity.ok(response);
    }
}
