package com.mutify.mutify.speech.controller;

import com.mutify.mutify.speech.service.AzureSpeechService;
import com.mutify.mutify.speech.service.AdminAssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/assistant")
public class AzureSpeechController {

    private final AzureSpeechService azureSpeechService;

    private final AdminAssistantService adminAssistantService;

    public AzureSpeechController(AzureSpeechService azureSpeechService, AdminAssistantService adminAssistantService) {
        this.azureSpeechService = azureSpeechService;
        this.adminAssistantService = adminAssistantService;
    }

    @GetMapping("/live-voice-command")
    public ResponseEntity<String> processLiveVoiceCommand() {
        String recognizedText = azureSpeechService.recognizeLiveSpeech();

        if (recognizedText.isEmpty() || recognizedText.equals("Speech not recognized. Try again.")) {
            return ResponseEntity.badRequest().body("Could not understand the speech.");
        }

        String response = adminAssistantService.processCommand(recognizedText);
        return ResponseEntity.ok(response);
    }
}
