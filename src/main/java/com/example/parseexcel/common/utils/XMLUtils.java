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
               //判断当前标签是开始标签还是结束标签

            }
        }
        return 0;
    }

    /**
     * 判断标签类型
     * @param tag 标签字符串
     * @return  0 开始标签    1 结束标签
     */
    private Integer checkTag(String tag){
        String startPatten = "^</.+>$";
        if (tag.matches(startPatten)){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        String testStr =
                FileContentUtil.readByPath("/Users/huanliu/Documents/privatefile/sharedStrings.xml");
        XMLUtils xmlUtils = new XMLUtils(testStr);
        System.out.println(xmlUtils.checkXml());


    }
}
