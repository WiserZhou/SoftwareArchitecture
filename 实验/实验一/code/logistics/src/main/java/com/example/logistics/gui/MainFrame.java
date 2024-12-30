
package com.example.logistics.gui;

import com.example.logistics.interfaces.MessageQueue;
import com.example.logistics.factory.MessageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private LogPanel logPanel;
    private TaskPanel taskPanel;
    private JButton startConsumerButton;
    private boolean isConsumerRunning = false;

    public MainFrame(MessageQueue messageQueue) {
        setTitle("Logistics Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logPanel = new LogPanel();
        taskPanel = new TaskPanel(messageQueue);
        startConsumerButton = new JButton("Start Consumer");

        startConsumerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isConsumerRunning = !isConsumerRunning;
                startConsumerButton.setText(isConsumerRunning ? "Stop Consumer" : "Start Consumer");
            }
        });

        add(logPanel, BorderLayout.CENTER);
        add(taskPanel, BorderLayout.EAST);
        add(startConsumerButton, BorderLayout.SOUTH);
    }

    public LogPanel getLogPanel() {
        return logPanel;
    }

    public TaskPanel getTaskPanel() {
        return taskPanel;
    }

    public boolean isConsumerRunning() {
        return isConsumerRunning;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageQueue queue = MessageFactory.createMessageQueue(10);
            MainFrame frame = new MainFrame(queue);
            frame.setVisible(true);
        });
    }
}