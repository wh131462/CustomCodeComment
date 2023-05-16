package com.wh131462.CustomCodeComment.toolWindow;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class MyToolWindow implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        TemplateComponent toolWindowContent = new TemplateComponent(toolWindow);
        Content content = ContentFactory.SERVICE.getInstance().createContent(toolWindowContent.getContainerPanel(), "Setting", false);
        toolWindow.getContentManager().addContent(content);
    }
}
