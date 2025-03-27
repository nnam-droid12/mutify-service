package com.mutify.mutify.receipt.service;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptImageService {

    private final ObjectMapper objectMapper;

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    public ReceiptImageService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String generateAndUploadReceiptImage(String jsonReceipt) throws IOException {

        File imageFile = generateReceiptImage(jsonReceipt);
        String imageUrl = uploadToAzureBlob(imageFile);

        return imageUrl;
    }

    private File generateReceiptImage(String jsonReceipt) throws IOException {
        Map<String, Object> receiptData = objectMapper.readValue(jsonReceipt, Map.class);
        BufferedImage image = new BufferedImage(400, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 400, 600);
        g2d.setColor(Color.BLACK);


        Font font;
        if (GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames().length > 0) {
            font = new Font("Arial", Font.BOLD, 14);
        } else {
            font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        }
        g2d.setFont(font);

        int y = 50;
        for (Map.Entry<String, Object> entry : receiptData.entrySet()) {
            g2d.drawString(entry.getKey() + ": " + entry.getValue(), 20, y);
            y += 30;
        }

        g2d.dispose();

        File outputFile = new File("receipt-" + UUID.randomUUID() + ".png");
        ImageIO.write(image, "png", outputFile);

        return outputFile;
    }


    private String uploadToAzureBlob(File file) throws IOException {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        String blobName = "receipts/" + file.getName();
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        blobClient.upload(Files.newInputStream(file.toPath()), file.length(), true);

        return blobClient.getBlobUrl();  // Return the public URL
    }
}
