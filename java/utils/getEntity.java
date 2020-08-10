package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Scanner;

public class getEntity {
    public static String get(String url) {
        //创建客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String entityr = "";
        //创建Get实例
        HttpGet httpGet = new HttpGet(url);

        //添加请求头的信息，模拟浏览器访问
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3573.0 Safari/537.36");

        try {
            //获得Response
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                //当响应状态码为200时，获得该网页源码并打印 
                entityr = EntityUtils.toString(response.getEntity(), "utf-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityr;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // next方式接收字符串   "https://api.emipm.com/playlists/287345/contents"
        System.out.println("请输入路径： ");
        String str1 = scan.next();
        scan.close();


        //获得响应的ajax，json格式的String
        String str = getEntity.get("https://api.emipm.com/playlists/"+str1+"/contents");
        JSONArray jsonArray = JSONArray.parseArray(str);
        System.out.println("数据总条数为" + jsonArray.size());
        for (Object o : jsonArray) {
            JSONObject o1 = (JSONObject) JSON.toJSON(o);
            System.out.println(o1.get("fixationId").toString());
        }


        //字符串序列化成集合

    }
}