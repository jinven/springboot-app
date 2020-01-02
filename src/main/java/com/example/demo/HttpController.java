package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HttpController {
	
	@RequestMapping("/http")
	public String index() {
        String message = "";
        try {
            String getUrl = "http://www.weather.com.cn/data/cityinfo/101280601.html";
            // String getUrl2 = "https://api.coindesk.com/v1/bpi/currentprice.json";
            String postUrl = "https://cn.bing.com/orgid/idtoken/nosignin";
            message += "GET: " + doGet(getUrl) + "<br>";
            String postData = "error=login_required&error_description=AADSTS50058%3A+A+silent+sign-in+request+was+sent+but+no+user+is+signed+in.+The+cookies+used+to+represent+the+user%27s+session+were+not+sent+in+the+request+to+Azure+AD.+This+can+happen+if+the+user+is+using+Internet+Explorer+or+Edge%2C+and+the+web+app+sending+the+silent+sign-in+request+is+in+different+IE+security+zone+than+the+Azure+AD+endpoint+%28login.microsoftonline.com%29.%0D%0ATrace+ID%3A+80d5a86f-f604-4970-9713-611012350b00%0D%0ACorrelation+ID%3A+df3a92f6-c4cd-4aca-a3ce-4d58202158ad%0D%0ATimestamp%3A+2019-12-10+03%3A13%3A25Z&error_uri=https%3A%2F%2Flogin.microsoftonline.com%2Ferror%3Fcode%3D50058";
            message += "POST: " + doPost(postUrl, postData) + "<br>";
            message += "http测试成功";
        } catch (Exception e) {
            message += "http测试失败";
            e.printStackTrace();
        }
		return message;
    }
    
    private String doGet(String addr){
        return doHttp(addr, "GET", null);
    }
    // private String doPost(String addr){
    //     return doHttp(addr, "POST", null);
    // }
    private String doPost(String addr, String data){
        return doHttp(addr, "POST", data);
    }
    private String doHttp(String addr, String method, String data){
        boolean isPost = method == "POST";
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        // 返回结果字符串
        String result = null;
        try {
            // 创建远程url连接对象
            URL url = new URL(addr);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod(isPost ? "POST" : "GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            if(isPost){
                // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
                connection.setDoOutput(true);
                // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
                connection.setDoInput(true);
                // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
                connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
                if(data!=null){
                    // 通过连接对象获取一个输出流
                    OutputStream os = connection.getOutputStream();
                    // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
                    os.write(data.getBytes());
                }
            } else {
                // 发送请求
                connection.connect();
            }
            int code = connection.getResponseCode();
            // 通过connection连接，获取输入流
            if (code == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}
