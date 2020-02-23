package com.example.demo.model;

import java.sql.Timestamp;

public class Device {
    private long deviceId;
    private long uID;
    private String vendor;
    private Timestamp creationDate;
    private boolean status;

    public Device() {
    }

    public Device(long deviceId, long uID, String vendor, Timestamp creationDate, boolean status) {
        this.deviceId = deviceId;
        this.uID = uID;
        this.vendor = vendor;
        this.creationDate = creationDate;
        this.status = status;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getuID() {
        return uID;
    }

    public void setuID(long uID) {
        this.uID = uID;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", uID=" + uID +
                ", vendor='" + vendor + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }
}
