package com.mutify.mutify.speech.service;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

@Service
public class AzureSpeechService {

    @Value("${azure.speech.key}")
    private String azureSpeechKey;

    @Value("${azure.speech.region}")
    private String azureSpeechRegion;

    public String simulateSpeechRecognition() {
        SpeechConfig speechConfig = null;

        try {
            speechConfig = SpeechConfig.fromSubscription(azureSpeechKey, azureSpeechRegion);
            speechConfig.setSpeechRecognitionLanguage("en-US");


            List<String> commands = Arrays.asList(
                    "total transactions today",
                    "last week transactions",
                    "receipt with highest amount",
                    "all receipts"
            );


            Random random = new Random();
            return commands.get(random.nextInt(commands.size()));
        } catch (Exception e) {
            return "Error in speech simulation: " + e.getMessage();
        } finally {
            if (speechConfig != null) speechConfig.close();
        }
    }
}