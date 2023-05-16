package com.wh131462.CustomCodeComment.toolWindow;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBAutoScroller;
import com.intellij.ui.JBCardLayout;
import com.intellij.ui.components.*;
import org.jetbrains.annotations.NotNull;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class TemplateComponent {
    private final JPanel containerPanel = new JPanel();

    public TemplateComponent(ToolWindow toolWindow) {
        containerPanel.setLayout(new BorderLayout(0, 20));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        containerPanel.add(createInputPanel(toolWindow), BorderLayout.PAGE_START);
        containerPanel.add(createPropertyPanel(toolWindow));
        containerPanel.add(createControlsPanel(toolWindow), BorderLayout.PAGE_END);
    }


    public JBScrollPane AutoAdjustTextArea() {
        JTextArea textArea = new JTextArea(10,30);
        JBScrollPane scrollPane = new JBScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Dimension size = new Dimension(Short.MAX_VALUE, 100); // 设置固定高度为100像素
        scrollPane.setMinimumSize(size);
        scrollPane.setPreferredSize(size);
        scrollPane.setMaximumSize(size);
        return scrollPane;
    }


    @NotNull
    private JPanel createInputPanel(ToolWindow toolWindow) {
        JPanel inputPanel = new JPanel();

        GridLayoutManager layoutManager = new GridLayoutManager(2, 2);
        inputPanel.setLayout(layoutManager);

        inputPanel.add(new JLabel("模板:"), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        inputPanel.add(AutoAdjustTextArea(), new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        return inputPanel;
    }

    @NotNull
    private JPanel createPropertyPanel(ToolWindow toolWindow) {
        JPanel propertyPanel = new JPanel();
        JBLabel inputLabel = new JBLabel("变量设置");
        propertyPanel.add(inputLabel);
        return propertyPanel;
    }

    @NotNull
    private JPanel createControlsPanel(ToolWindow toolWindow) {
        JPanel controlsPanel = new JPanel();
        JButton refreshDateAndTimeButton = new JButton("Refresh");
        refreshDateAndTimeButton.addActionListener(e -> {
            System.out.println("刷新");
        });
        controlsPanel.add(refreshDateAndTimeButton);
        JButton hideToolWindowButton = new JButton("Hide");
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        controlsPanel.add(hideToolWindowButton);
        return controlsPanel;
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
