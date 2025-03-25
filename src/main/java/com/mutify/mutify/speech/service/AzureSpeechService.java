package com.mutify.mutify.speech.service;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.concurrent.Future;

@Service
public class AzureSpeechService {

    @Value("${azure.speech.key}")
    private String azureSpeechKey;

    @Value("${azure.speech.region}")
    private String azureSpeechRegion;

    public String recognizeLiveSpeech() {
        SpeechConfig speechConfig = null;
        AudioConfig audioConfig = null;
        SpeechRecognizer recognizer = null;

        try {
            speechConfig = SpeechConfig.fromSubscription(azureSpeechKey, azureSpeechRegion);
            speechConfig.setSpeechRecognitionLanguage("en-US");

            audioConfig = AudioConfig.fromDefaultMicrophoneInput();

            recognizer = new SpeechRecognizer(speechConfig, audioConfig);

            System.out.println("🎤 Listening... Speak now.");

            Future<SpeechRecognitionResult> future = recognizer.recognizeOnceAsync();
            SpeechRecognitionResult result = future.get();

            if (result.getReason() == ResultReason.RecognizedSpeech) {
                return result.getText();
            } else {
                return "Speech not recognized. Try again.";
            }
        } catch (Exception e) {
            return "Error processing live speech: " + e.getMessage();
        } finally {
            if (recognizer != null) recognizer.close();
            if (audioConfig != null) audioConfig.close();
            if (speechConfig != null) speechConfig.close();
        }
    }
}
