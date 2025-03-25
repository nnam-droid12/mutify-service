package com.mutify.mutify.speech.controller;

import com.mutify.mutify.speech.service.AdminAssistantService;
import com.mutify.mutify.speech.service.AzureSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/v1/admin/assistant")
public class AdminAssistantController {


    private final AzureSpeechService speechService;


    private final AdminAssistantService assistantService;

    public AdminAssistantController(AzureSpeechService speechService, AdminAssistantService assistantService) {
        this.speechService = speechService;
        this.assistantService = assistantService;
    }

    @GetMapping("/voice-command")
    public String handleVoiceCommand() {
        String userInput = speechService.recognizeLiveSpeech();

        return assistantService.processCommand(userInput);
    }
}
