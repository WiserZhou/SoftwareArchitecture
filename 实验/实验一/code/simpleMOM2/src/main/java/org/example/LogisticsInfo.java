package org.example;

/**
 * @description:
 * @author: xyc
 * @date: 2023-05-10 10:50
 */
// 定义一个物流信息类
class LogisticsInfo {
    private String packageId; // 包裹编号
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
}


