package com.wh131462.CustomCodeComment.toolWindow;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateToolUtil {
    /**
     * 获取变量列表
     * @param content
     * @return
     */
    public static ArrayList<String> getValueList(String content){
        ArrayList<String> variables = new ArrayList<String>(); // 初始化变量列表
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}"); // 匹配${}包裹的变量
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) { // 逐个添加变量
            String variable = matcher.group(1);
            if (!variables.contains(variable)) {
                variables.add(variable); //添加变量到列表
            }
        }
        System.out.println("ValueList:" + variables);
        return variables;
    }
}
