package com.mutify.mutify.receipt.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ReceiptImageService {

    private final ObjectMapper objectMapper;

    public ReceiptImageService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public File generateReceiptImage(String jsonReceipt, String filePath) throws IOException {
        // Convert JSON to Map
        Map<String, Object> receiptData = objectMapper.readValue(jsonReceipt, Map.class);

        // Create image
        BufferedImage image = new BufferedImage(400, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Set Background & Font
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 400, 600);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        // Draw Text
        int y = 50;
        for (Map.Entry<String, Object> entry : receiptData.entrySet()) {
            g2d.drawString(entry.getKey() + ": " + entry.getValue(), 20, y);
            y += 30;
        }

        g2d.dispose();

        // Save as PNG
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);

        return outputFile;
    }
}
