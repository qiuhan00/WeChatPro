package com.cfang.WeChat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

	/**
     * MD5加密
     * @param plainText
     * @return 
     */
    public static String getMd5(String plainText){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            
            int i;
            StringBuffer sb=new StringBuffer();
            for(int offset=0;offset<b.length;offset++){
                i=b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("generate MD5 String error! "+e.getMessage());
        }
        return null;
    }
}
