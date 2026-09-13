package com.meditrack.counterfeit.model;

public class MedicineBatch {
    private Long id;
    private String medicineName;
    private String batchId;
    private String manufacturer;

    public MedicineBatch(Long id, String medicineName, String batchId, String manufacturer) {
        this.id = id;
        this.medicineName = medicineName;
        this.batchId = batchId;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String toString() {
        return "MedicineBatch{" +
                "id=" + id +
                ", medicineName='" + medicineName + '\'' +
                ", batchId='" + batchId + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
