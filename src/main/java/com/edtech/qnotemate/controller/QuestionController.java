package com.edtech.qnotemate.controller;

import com.edtech.qnotemate.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestHeader("uuid") UUID uuid, @RequestBody String message) {

        // logic or operations with the UUID and message



        // Return a response indicating success
        return ResponseEntity.status(HttpStatus.CREATED).body("Message created successfully");
    }


}
