package com.meditrack.counterfeit.service;

import com.meditrack.counterfeit.model.MedicineBatch;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
public class CryptoService {
    public String generateMedicineHash(MedicineBatch batch){
       if (batch == null){
           throw new IllegalArgumentException("MedicineBatch cannot be null");
       }
       String rawData = batch.getBatchId() + ":" +
               batch.getMedicineName() + ":" +
               batch.getManufacturer();

       return applyHash256(rawData);
    }

    /**
     * Generate a Sha-256 Hash of the MedicineBatch Object using java MessageDigest class
     */
    private String applyHash256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Modern, single-line byte-to-hex conversion
            return HexFormat.of().formatHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}
