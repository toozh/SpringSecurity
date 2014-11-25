package com.zt.util;

import java.security.MessageDigest;


/**
 * 加密工具
 * @author 1202211
 *
 */
public class EncryptUtil {  

    private static final String UTF8 = "utf-8";  
  
	private static String MD5WithSalt(String password,String salt){
    	 MessageDigest md=null;
         String str=null;
         try {
			md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest((password+salt).getBytes(UTF8));
			str=byte2HexStr(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
         return str;
    }
    
    /** 
     * 字节数组转化为大写16进制字符串 
     * @param b 
     * @return 
     */  
    private static String byte2HexStr(byte[] b) {  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < b.length; i++) {  
            String s = Integer.toHexString(b[i] & 0xFF);  
            if (s.length() == 1) {  
                sb.append("0");  
            }  
            sb.append(s);  
        }  
        return sb.toString();  
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println(EncryptUtil.MD5WithSalt("123456","{test}"));
    	String test = EncryptUtil.MD5WithSalt("123456","{admin}");
    	String admin = "b594510740d2ac4261c1b2fe87850d08";
    	System.out.println(test.equals(admin));
	}
    
}  
