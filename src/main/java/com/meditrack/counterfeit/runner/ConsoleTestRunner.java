package com.meditrack.counterfeit.runner;

import com.meditrack.counterfeit.model.MedicineBatch;
import com.meditrack.counterfeit.model.ScanLog;
import com.meditrack.counterfeit.service.CryptoService;
import com.meditrack.counterfeit.service.LocationVerificationService;

public class ConsoleTestRunner {

    public static void main(String[] args){

        CryptoService cryptoService = new CryptoService();
        LocationVerificationService verificationService = new LocationVerificationService();

        MedicineBatch medBa1 = new MedicineBatch(12345L, "Paracetamol","2026-150",
                "ParaMed");
        System.out.println("batch 1 :" + medBa1);
        MedicineBatch medBa2 = new MedicineBatch(12345L, "Paracetamol","2026-150",
                "ParaMed");
        System.out.println("batch 2 :" + medBa2);
        MedicineBatch medBa3 = new MedicineBatch(12346L, "Paracetamol","2026-150",
                "ParaMed");
        System.out.println("batch 3 :" + medBa3);


        String batchHash1 = cryptoService.generateMedicineHash(medBa1);
        System.out.println("batch hash 1: " + batchHash1);
        String batchHash2 = cryptoService.generateMedicineHash(medBa2);
        System.out.println("batch hash 2: " + batchHash2);
        String batchHash3 = cryptoService.generateMedicineHash(medBa3);
        System.out.println("batch hash 3: " + batchHash3);

        ScanLog scan1 = new ScanLog("User-end","Consumer", batchHash1,72.8777, 19.0760); //Mumbai
        System.out.println("result 1:" + verificationService.verifyAndLogScan(scan1));
        ScanLog scan2 = new ScanLog("User-end","Consumer", batchHash2,73.8567, 18.5204); //Pune
        System.out.println("result 2:" + verificationService.verifyAndLogScan(scan2));

        System.out.println("\n=========================================================\n");
    }
}
