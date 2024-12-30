package com.example.logistics.gui;

import com.example.logistics.LogisticsInfo;
import com.example.logistics.interfaces.MessageQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TaskPanel extends JPanel {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JButton changeStatusButton;
    private Map<String, LogisticsInfo> taskMap;
    private MessageQueue messageQueue;

    public TaskPanel(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
        setLayout(new BorderLayout());
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        taskMap = new HashMap<>();

        changeStatusButton = new JButton("Change Status");
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedTask = taskListModel.getElementAt(selectedIndex);
                    LogisticsInfo logisticsInfo = taskMap.get(selectedTask.split(":")[0]);
                    StatusDialog dialog = new StatusDialog(logisticsInfo);
                    dialog.setVisible(true);
                    LogisticsInfo updatedLogisticsInfo = dialog.getUpdatedLogisticsInfo();
                    if (updatedLogisticsInfo != null) {
                        taskMap.put(updatedLogisticsInfo.getPackageId(), updatedLogisticsInfo);
                        taskListModel.setElementAt(
                                updatedLogisticsInfo.getPackageId() + ": " + updatedLogisticsInfo.getStatus(),
                                selectedIndex);
                        try {
                            messageQueue.updateLogisticsInfo(updatedLogisticsInfo);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
        add(changeStatusButton, BorderLayout.SOUTH);
    }

    public void addTask(LogisticsInfo logisticsInfo) {
        String task = logisticsInfo.getPackageId() + ": " + logisticsInfo.getStatus();
        taskListModel.addElement(task);
        taskMap.put(logisticsInfo.getPackageId(), logisticsInfo);
    }

    public void updateTask(LogisticsInfo logisticsInfo) {
        String task = logisticsInfo.getPackageId() + ": " + logisticsInfo.getStatus();
        if (taskMap.containsKey(logisticsInfo.getPackageId())) {
            int index = taskListModel.indexOf(
                    logisticsInfo.getPackageId() + ": " + taskMap.get(logisticsInfo.getPackageId()).getStatus());
            taskListModel.set(index, task);
            taskMap.put(logisticsInfo.getPackageId(), logisticsInfo);
        }
    }
}