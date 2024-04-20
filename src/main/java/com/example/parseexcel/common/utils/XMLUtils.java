package com.example.parseexcel.common.utils;


import java.util.Queue;
import java.util.Stack;

/**
 * @author liuhuan
 * @date 2024/4/16
 */
public class XMLUtils {

    private String text;

    private XMLUtils(String text){
        this.text = text;
    }


    /**
     * 判断标签是否成对存在
     *
     * @return 0 是成对存在的； 非0 不是成对存在的并且返回当前位置的index
     */
    public Integer checkXml(){
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if ('<' == c){
                String left = getLeft(i, 1);
                stack.push(left);
            }else if ('>' == c){
                String right = getRight(i, 1);
                String stackTopStr = stack.pop();
                if (!right.equals(stackTopStr)){
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 获取左标签
     * @param startIndex 开始索引
     * @param len 字符长度
     * @return
     */
    private String getLeft(Integer startIndex, Integer len){
        char c = text.charAt(startIndex + len);
        if (Character.isSpaceChar(c)){
            return text.substring(startIndex, startIndex + len);
        }else {
            return getLeft(startIndex, len + 1);
        }
    }

    /**
     * 获取右标签
     * @param endIndex
     * @param len
     * @return
     */
    private String getRight(Integer endIndex, Integer len){
        char c = text.charAt(endIndex - len);
        if (Character.isSpaceChar(c)){
            return text.substring(endIndex - len +  1, endIndex + 1);
        }else {
            return getRight(endIndex, len + 1);
        }
    }

    public static void main(String[] args) {
        String testStr = "<1234 dfaf>";
        XMLUtils xmlUtils = new XMLUtils(testStr);
        System.out.println(xmlUtils.getRight(testStr.indexOf(">"), 1));
    }
}
