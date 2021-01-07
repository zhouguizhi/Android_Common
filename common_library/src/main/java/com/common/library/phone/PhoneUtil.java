package com.common.library.phone;
import android.text.TextUtils;
/**
 * @Description: PhoneUtil 类用于手机号相关
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午2:33
 * @Version: 1.0
 */
public class PhoneUtil {
    private PhoneUtil(){}

    /**
     * 用于手机号中间转为**** 功能
     * @param phone 手机号
     * @return
     */
    public static String toConverStarInCenter(String phone){
        String result = "";
        if(TextUtils.isEmpty(phone)){
            return result;
        }
        if(phone.length()<=8){
            return phone;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<phone.length();i++){
            sb.append(i>=3&&i<=6?"*":phone.charAt(i));
        }
        result = sb.toString();
        return result;
    }
    /**
     * 用于手机号中间转为**** 功能 而且每四个加空格" "
     * @param phone 手机号
     * @return
     */
    public static String toConverStarInCenterAndAddSpace(String phone){
        String result = "";
        if(TextUtils.isEmpty(phone)){
            return result;
        }
        if(phone.length()<=8){
            return phone;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<phone.length();i++){
            if(i>=3&&i<=6){
                sb.append(i==3?" ":"").append("*");
            }else {
                sb.append(i==7?" ":"").append(phone.charAt(i));
            }
        }
        result = sb.toString();
        return result;
    }
}
