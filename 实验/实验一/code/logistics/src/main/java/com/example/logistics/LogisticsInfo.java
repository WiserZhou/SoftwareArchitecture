package com.example.logistics;

public class LogisticsInfo {
    private final String packageId; // 包裹编号
    private String status; // 包裹状态

    public LogisticsInfo(String packageId, String status) {
        this.packageId = packageId;
        this.status = status;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



