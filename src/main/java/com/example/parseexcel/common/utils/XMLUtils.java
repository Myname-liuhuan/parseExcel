package com.example.parseexcel.common.utils;


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

        Integer start = 0;
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if ('<' == c){
                start = i;
            }  else if ('>' == c){
               String sub = text.substring(start, i);
               sub = sub.indexOf('\u0020') > -1 ? sub.substring(0, sub.indexOf('\u0020')):sub;
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
        if ('\\' == c){
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
        String testStr =
                FileContentUtil.readByPath("/Users/huanliu/Documents/privatefile/sharedStrings.xml");
        XMLUtils xmlUtils = new XMLUtils(testStr);
        System.out.println(xmlUtils.checkXml());


    }
}
