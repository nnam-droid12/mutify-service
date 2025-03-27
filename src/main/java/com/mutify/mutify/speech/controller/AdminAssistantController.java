package com.mutify.mutify.speech.controller;

import com.mutify.mutify.speech.service.AdminAssistantService;
import com.mutify.mutify.speech.service.AzureSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/assistant")
public class AdminAssistantController {

    private final AzureSpeechService azureSpeechService;
    private final AdminAssistantService adminAssistantService;

    public AdminAssistantController(AzureSpeechService azureSpeechService,
                                    AdminAssistantService adminAssistantService) {
        this.azureSpeechService = azureSpeechService;
        this.adminAssistantService = adminAssistantService;
    }

    @GetMapping("/voice-command")
    public ResponseEntity<String> processVoiceCommand() {
        try {
            String transcribedText = azureSpeechService.simulateSpeechRecognition();

            String response = adminAssistantService.processCommand(transcribedText);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing voice command: " + e.getMessage());
        }
    }
}