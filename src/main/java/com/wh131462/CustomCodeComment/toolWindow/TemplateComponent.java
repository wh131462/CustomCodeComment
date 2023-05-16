package com.wh131462.CustomCodeComment.toolWindow;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBAutoScroller;
import com.intellij.ui.JBCardLayout;
import com.intellij.ui.components.*;
import org.jetbrains.annotations.NotNull;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class TemplateComponent {
    private final JPanel containerPanel = new JPanel();
    private JPanel inputPanel;
    private JPanel propertyPanel;


    public TemplateComponent(ToolWindow toolWindow) {
        inputPanel = createInputPanel(toolWindow);
        propertyPanel = createPropertyPanel(toolWindow);

        containerPanel.setLayout(new BorderLayout(0, 20));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        containerPanel.add(inputPanel, BorderLayout.PAGE_START);
        containerPanel.add(propertyPanel);
        containerPanel.add(createControlsPanel(toolWindow), BorderLayout.PAGE_END);
    }


    public JBScrollPane AutoAdjustTextArea() {
        JBTextArea textArea = new JBTextArea(10,30);
        JBScrollPane scrollPane = new JBScrollPane(textArea);
        /**
         * 添加事件监听
         */
        textArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                // 处理得到焦点事件
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 处理失去焦点事件
                updatePropertyPanel();
            }
        });


        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Dimension size = new Dimension(Short.MAX_VALUE, 100); // 设置固定高度为100像素
        scrollPane.setMinimumSize(size);
        scrollPane.setPreferredSize(size);
        scrollPane.setMaximumSize(size);
        return scrollPane;
    }

    /**
     * 创建输入框panel
     * @param toolWindow
     * @return
     */
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
        //初始化grid 布局
        GridLayoutManager layoutManager = new GridLayoutManager(2 , 2);
        propertyPanel.setLayout(layoutManager);
        propertyPanel.add(new JBLabel("属性设置:"), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        return propertyPanel;
    }

    private void updatePropertyPanel(){
        JBTextArea jbTextArea = getTextAreaFromPanel(inputPanel);
        System.out.println(jbTextArea);
        if(jbTextArea == null){
            return;
        }
        ArrayList<String> values = TemplateToolUtil.getValueList(jbTextArea.getText());
        this.propertyPanel.removeAll();
        int count = values.size();
        int start = 1;
        //初始化grid 布局
        GridLayoutManager layoutManager = new GridLayoutManager(count + 2 , 2);
        propertyPanel.setLayout(layoutManager);
        //添加属性设置的label
        propertyPanel.add(new JBLabel("属性设置:"), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        //遍历生成变量列表
        for (String value : values) {
            propertyPanel.add(new JBLabel(value+':'), new GridConstraints(start, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
            propertyPanel.add(new JBTextField(), new GridConstraints(start, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
            start++;
        }
    }
    public JBTextArea getTextAreaFromPanel(Container container) {
        Component[] components = container.getComponents();
        JBTextArea textArea = null;
        for (Component component : components) {
            if (component instanceof JBTextArea) {
                textArea = (JBTextArea) component;
            } else if (component instanceof Container) {
                JBTextArea childTextArea = getTextAreaFromPanel((Container) component);
                if (childTextArea != null) {
                    textArea = childTextArea;
                }
            }
        }
        return textArea;
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
