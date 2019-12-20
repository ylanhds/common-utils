package utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class MD5Test {


    public static void main(String[] args) {
        String password = "dajoigjffdsf"; //2IeXvHbzXyMDlDvvdTaGnw==
        try {
            //确定计算方法 md5
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] digest = md.digest(password.getBytes("UTF-8"));
            System.out.println(Arrays.toString(digest));
//            String mdStr = new String(digest);
//            System.out.println(mdStr);
            // 1.8
            String s = Base64.getEncoder().encodeToString(digest);
            System.out.println(s);
            byte[] decode = Base64.getDecoder().decode(s);
            System.out.println(Arrays.toString(decode));
            //解码
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] bytes = decoder.decodeBuffer(s);

        } catch (Exception e) {

        }

    }
}
