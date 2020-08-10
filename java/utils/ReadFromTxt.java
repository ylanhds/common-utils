package utils;

import java.io.*;

public class ReadFromTxt {

    public static void main(String[] args) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/home/fablen/IdeaProjects/common-utils/4-5月份随机选选取用户搜索返回数据.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str = "";
        String temp = "";
        try {
            while (null != (temp = br.readLine())) {
                str +=  temp+"," ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str );
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

