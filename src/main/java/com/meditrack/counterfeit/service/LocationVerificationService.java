package com.meditrack.counterfeit.service;

import com.meditrack.counterfeit.model.ScanLog;
import com.meditrack.counterfeit.util.DistanceCalculator;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationVerificationService {

    // Maximum physically realistic transport speed (e.g., fast flight / rapid transport in km/h)
    private static final double MAX_ALLOWED_SPEED_KMH = 800.0;

    // In-memory store: Key = Generated Hash, Value = Historical List of ScanLogs
    private final Map<String, List<ScanLog>> scanHistoryMap = new HashMap<>();

    /**
     * Processes an incoming scan log and evaluates it for location or velocity anomalies.
     *
     * @param newScan The incoming ScanLog object
     * @return Verification status string ("GENUINE", "FLAGGED_IMPOSSIBLE_SPEED", etc.)
     */
    public String verifyAndLogScan(ScanLog newScan) {
        if (newScan == null || newScan.getGeneratedHash() == null) {
            throw new IllegalArgumentException("ScanLog and its generatedHash cannot be null");
        }

        String hash = newScan.getGeneratedHash();
        scanHistoryMap.putIfAbsent(hash, new ArrayList<>());
        List<ScanLog> history = scanHistoryMap.get(hash);

        // First scan recorded for this batch
        if (history.isEmpty()) {
            history.add(newScan);
            return "GENUINE_INITIAL_SCAN";
        }

        // Retrieve the most recent scan from the history
        ScanLog previousScan = history.get(history.size() - 1);

        // Calculate distance between coordinates in kilometers
        double distanceKm = DistanceCalculator.calculateDistance(
                previousScan.getLatitude(), previousScan.getLongitude(),
                newScan.getLatitude(), newScan.getLongitude()
        );

        // Calculate elapsed time in hours
        long secondsBetween = Duration.between(previousScan.getTimestamp(), newScan.getTimestamp()).getSeconds();
        double hoursBetween = secondsBetween / 3600.0;

        // Fraud Check 1: Near-instantaneous distance jump (Cloned QR detection)
        if (distanceKm > 10.0 && hoursBetween < (1.0 / 60.0)) { // >10km within 1 minute
            history.add(newScan);
            return "FLAGGED_CLONED_QR_DETECTED";
        }

        // Fraud Check 2: Speed calculation check
        if (hoursBetween > 0) {
            double calculatedSpeedKmh = distanceKm / hoursBetween;
            if (calculatedSpeedKmh > MAX_ALLOWED_SPEED_KMH) {
                history.add(newScan);
                return "FLAGGED_IMPOSSIBLE_SPEED";
            }
        }

        // Record the scan and confirm genuine status
        history.add(newScan);
        return "GENUINE";
    }

    /**
     * Retrieves the complete scan log history for a specific batch hash.
     */
    public List<ScanLog> getScanHistory(String hash) {
        return scanHistoryMap.getOrDefault(hash, new ArrayList<>());
    }
}