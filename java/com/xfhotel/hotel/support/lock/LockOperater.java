package com.xfhotel.hotel.support.lock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.log4j.chainsaw.Main;
import org.json.JSONObject;
import org.junit.experimental.theories.Theories;

import com.xfhotel.hotel.common.Constants;

public class LockOperater {
	
	public static String ACCESS_TOKEN = "";
	public static boolean verifyToken(String result){
		return result.equals(Constants.LOCK_MSG_TOKEN_OUT_OF_DATE) || result.equals(Constants.LOCK_MSG_TOKEN_NOT_EXIST) || result.equals(Constants.LOCK_MSG_TOKEN_INVALIDATE);
	}
	public static String baseUrl = Constants.LOCK_TEST_BASE_URL;
	
	public static String sendPost(String url, JSONObject param, int type) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            if(type!=0){
            	conn.setRequestProperty("access_token", LockOperater.ACCESS_TOKEN);
    			UUID uuid = UUID.randomUUID();
    			conn.setRequestProperty("s_id", uuid.toString());
    			conn.setRequestProperty("version", "1.1");
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

	/**
	 * 
	 * @param param:key="11"&value="11"
	 * @return
	 */
	public static String login(){
		String url = baseUrl + "/login";
		JSONObject param = new JSONObject();
		param.put("account", Constants.LOCK_ACCOUNT);
		String password = Constants.LOCK_PASSWORD;
		param.put("password", DES.encrypt(password.getBytes()));
		
		String response = sendPost(url, param, 0);
		
		JSONObject obj = new JSONObject(response);
		LockOperater.ACCESS_TOKEN = obj.getJSONObject("data").getString("access_token");
		return response;
	}
	
	/**
	 * 
	 * @param param
	 * lock_no
	 * valid_time_start
	 * valid_time_end
	 * pwd_user_mobile
	 * pwd_user_idcard
	 * @return
	 */
	public static String addPassword(JSONObject param){
		String url = baseUrl + "/pwd/add";
		String result = "";
		String response = "";
		do{
			if(LockOperater.verifyToken(result)){
				login();
			}
			param.put("access_token", LockOperater.ACCESS_TOKEN);
			UUID uuid = UUID.randomUUID();
			param.put("s_id", uuid);
			
			response = sendPost(url, param, 1);
			
			JSONObject obj = new JSONObject(response);
			result = obj.getString("rlt_code");
		}while(LockOperater.verifyToken(result));
		return response;
	}
	
	/**
	 * 
	 * @param param
	 * lock_no
	 * pwd_no
	 * pwd_text
	 * valid_time_start
	 * valid_time_end
	 * @return
	 */
	public static String modifyPassword(JSONObject param){
		String url = baseUrl + "/pwd/update";
		String result = "";
		String response = "";
		do{
			if(LockOperater.verifyToken(result)){
				login();
			}
			response = sendPost(url, param, 1);
			
			JSONObject obj = new JSONObject(response);
			result = obj.getString("rlt_code");
		}while(LockOperater.verifyToken(result));
		return response;
	}
	
	/**
	 * 
	 * @param param
	 * lock_no
	 * pwd_no
	 * pwd_user_mobile
	 * @return
	 */
	public static String deletePassword(JSONObject param){
		String url = baseUrl + "/pwd/delete";
		String result = "";
		String response = "";
		do{
			if(LockOperater.verifyToken(result)){
				login();
			}
			response = sendPost(url, param, 1);
			
			JSONObject obj = new JSONObject(response);
			result = obj.getString("rlt_code");
		}while(LockOperater.verifyToken(result));
		return response;
	}
}
