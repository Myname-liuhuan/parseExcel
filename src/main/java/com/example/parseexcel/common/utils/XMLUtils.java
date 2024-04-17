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


    public  boolean checkXml(){
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if ('<' == c){
                String left = getLeft(i, 1);
            }
        }
        return false;
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
            return text.substring(endIndex - len, endIndex);
        }else {
            return getRight(endIndex, len + 1);
        }
    }

    public static void main(String[] args) {
        XMLUtils xmlUtils = new XMLUtils("<1234 dfaf");
        System.out.println(xmlUtils.getLeft(0, 1));
    }
}
