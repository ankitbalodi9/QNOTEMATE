package com.edtech.qnotemate.serviceImpl;

import com.edtech.qnotemate.entity.Question;
import com.edtech.qnotemate.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final String CHATGPT_URL = "https://api.chatgpt.com/v1/chat/completions";
    private static final String API_KEY = "YOUR_API_KEY";
    private final RestTemplate restTemplate;

    @Autowired
    public QuestionServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public String askQuestion(UUID uuid, String message) {
        Question question = new Question();
        question.setMessage(message);

        String answer = callChatGPTAPI(question.getMessage());

        return answer;
    }


    private String callChatGPTAPI(String question) {
        // Set up the headers and request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("messages", question);

        // Make the API call to the ChatGPT API
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(CHATGPT_URL, HttpMethod.POST, requestEntity, String.class);

        // Process the response
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String answer = responseEntity.getBody();
            return answer;
        } else {
            throw new RuntimeException("Failed to retrieve answer from ChatGPT API.");
        }

    }



}
