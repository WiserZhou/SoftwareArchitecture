package com.example.logistics.interfaces;

import com.example.logistics.LogisticsInfo;

public interface MessageListener {
    void onMessageChanged(LogisticsInfo logisticsInfo) throws InterruptedException;
}