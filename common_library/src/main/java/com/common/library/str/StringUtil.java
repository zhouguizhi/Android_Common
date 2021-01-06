package com.common.library.str;
import android.text.TextUtils;
/**
 * @Description: 字符串常用功能
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午2:49
 * @Version: 1.0
 */
public class StringUtil {
    private StringUtil(){}

    /**
     * 每四个字符串 添加一个空格
     * @param str
     * @return
     */
    public static String perFourAddSpace(String str){
        String result = "";
        if(TextUtils.isEmpty(str)){
            return  result;
        }
        if(str.length()<=4){
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            sb.append(str.charAt(i)).append(i%4==3?" ":"");
        }
        result = sb.toString();
        return result;
    }
}
