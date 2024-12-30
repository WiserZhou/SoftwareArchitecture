package com.example.logistics.gui;

import com.example.logistics.LogisticsInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusDialog extends JDialog {
    private JComboBox<String> statusComboBox;
    private JButton okButton;
    private LogisticsInfo logisticsInfo;

    public StatusDialog(LogisticsInfo logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
        setTitle("Change Status");
        setSize(300, 150);
        setLayout(new BorderLayout());

        // 定义所有状态
        String[] statuses = {"未出库", "已出库", "已接收"};
        statusComboBox = new JComboBox<>(statuses);

        // 初始化时设置当前状态
        statusComboBox.setSelectedItem(logisticsInfo.getStatus());
        add(statusComboBox, BorderLayout.CENTER);

        // 设置 OK 按钮
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取当前状态的索引

                // 计算下一个状态的索引 (循环切换状态)
                int nextIndex = statusComboBox.getSelectedIndex();

                if(nextIndex>=2){
                    nextIndex = 2;
                }

                // 更新 logisticsInfo 中的状态
                logisticsInfo.setStatus(statuses[nextIndex]);

                // 更新 JComboBox 的显示状态
                statusComboBox.setSelectedIndex(nextIndex);

                // 关闭对话框
                dispose();
            }
        });

        // 将 OK 按钮添加到对话框底部
        add(okButton, BorderLayout.SOUTH);
    }

    // 获取更新后的物流信息
    public LogisticsInfo getUpdatedLogisticsInfo() {
        return logisticsInfo;
    }
}
