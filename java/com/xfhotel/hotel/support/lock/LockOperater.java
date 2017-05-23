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

import org.junit.experimental.theories.Theories;

import com.xfhotel.hotel.common.Constants;

import net.sf.json.JSONObject;

public class LockOperater implements LockService {

	public final static int POST_TYPE_NEED_HEADER = 1;
	public final static int POST_TYPE_NO_HEADER = 0;

	public static final String LOCK_TEST_BASE_URL = "http://test.ops.huohetech.com:80";
	public static final String LOCK_BASE_URL = "http://ops.huohetech.com";

	public static final String LOCK_DES_KEY = "5DD319E4";
	public static final String LOCK_ACCOUNT = "13072983237";
	public static final String LOCK_PASSWORD = "yiyun3237";

	public static final String LOCK_MSG_SUCCESS = "HH0000";
	public static final String LOCK_MSG_TOKEN_OUT_OF_DATE = "OPS04110";
	public static final String LOCK_MSG_TOKEN_INVALIDATE = "OPS04100";
	public static final String LOCK_MSG_TOKEN_NOT_EXIST = "OPS00003";

	public static String ACCESS_TOKEN = "";

	/**
	 * 验证TOKEN
	 */
	public static boolean verifyToken(String result) {
		return result.equals(LOCK_MSG_TOKEN_OUT_OF_DATE) || result.equals(LOCK_MSG_TOKEN_NOT_EXIST)
				|| result.equals(LOCK_MSG_TOKEN_INVALIDATE);
	}

	public static String baseUrl = LOCK_BASE_URL;

	private static LockOperater instance = null;

	public static LockOperater getInstance() {
		
		synchronized (LockOperater.class) {
			if (instance == null) {
				instance = new LockOperater();
			}

		}

		return instance;
	}

	@SuppressWarnings("static-access")
	public LockOperater() {

		JSONObject result = login("", "", LOCK_ACCOUNT, LOCK_PASSWORD);

		System.out.println("新建单例：" + result.getString("rlt_msg"));

		if (result.getString("rlt_code").equals(LOCK_MSG_SUCCESS)) {
			this.ACCESS_TOKEN = result.getJSONObject("data").getString("access_token");
		} else {

		}
	}

	public static JSONObject sendPost(String url, JSONObject param, int type) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json");
			if (type == POST_TYPE_NEED_HEADER) {
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
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return JSONObject.fromObject(result);
	}

	@Override
	public JSONObject login(String version, String s_id, String account, String password) {
		String url = baseUrl + "/login";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", DES.encrypt(password.getBytes()));
		JSONObject response = sendPost(url, param, POST_TYPE_NO_HEADER);
		return response;
	}

	@Override
	public JSONObject pwdAdd(String version, String access_token, String s_id, String lock_no, String pwd_text,
			String valid_time_start, String valid_time_end, String pwd_user_name, String pwd_user_mobile,
			String pwd_user_idcard, String description, String extra) {

		return null;
	}
	
	public JSONObject pwdAdd(String lock_no, String pwd_text,
			Long valid_time_start, Long valid_time_end, String pwd_user_name, String pwd_user_mobile,
			String pwd_user_idcard, String description, String extra) {
		return pwdAdd("", "", "", lock_no, pwd_text,""+ valid_time_start, ""+valid_time_end, pwd_user_name, pwd_user_mobile, pwd_user_idcard, description, extra);
	}
	@Override
	public JSONObject pwdUpdate(String version, String access_token, String s_id, String lock_no, String pwd_text,
			String valid_time_start, String valid_time_end, String extra) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public JSONObject pwdOfflineAdd(String version, String access_token, String s_id, String lock_no,
			String valid_time_start, String valid_time_end, String pwd_user_name, String pwd_user_mobile,
			String pwd_user_idcard, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject pwdDelete(String version, String access_token, String s_id, String lock_no, String pwd_no,
			String pwd_user_mobile, String extra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockRemoteOpen(String version, String access_token, String s_id, String lock_no,
			String pwd_user_mobile, String pwd_user_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockAuth(String version, String access_token, String s_id, String lock_no, String mobile,
			String name, String allow_auth, String auth_time_start, String auth_time_end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockCancelAuth(String version, String access_token, String s_id, String lock_no, String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject pwdUpdateFun(String version, String access_token, String s_id, String lock_no, String pwd_text,
			String valid_time_start, String valid_time_end, String extra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject nodeUpdateInstallInfo(String version, String access_token, String s_id, String node_no,
			String house_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockUpdateInstallInfo(String version, String access_token, String s_id, String lock_no,
			String house_code, String room_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject nodeList(String vaersion, String access_token, String s_id, int page_size, int current_page,
			String node_no, String house_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject nodeView(String version, String access_token, String s_id, String node_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockList(String version, String access_token, String s_id, int page_size, int current_page,
			String node_no, String lock_code, String house_code, String roon_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockDetails(String version, String access_token, String s_id, String lock_no) {
		String url = baseUrl + "/lock/view";
		JSONObject param = new JSONObject();
		param.put("lock_no", lock_no);
		JSONObject response = sendPost(url, param, POST_TYPE_NEED_HEADER);
		return response;
	}

	public JSONObject lockDetails(String lock_no) {
		return lockDetails("", "", "", lock_no);
	}

	@Override
	public JSONObject pwdList(String version, String access_token, String s_id, String lock_no, int pwd_no,
			String pwd_user_mobile, String status) {
		
		return null;
	}
	
	/**
	 * 获取密码列表
	 * @param lock_no
	 * @param pwd_user_mobile
	 * @return
	 */
	public JSONObject pwdList(String lock_no, 
			String pwd_user_mobile) {
		String url = baseUrl + "/pwd/list";
		JSONObject param = new JSONObject();
		param.put("lock_no", lock_no);
		param.put("pwd_user_mobile", pwd_user_mobile);
		JSONObject response = sendPost(url, param, POST_TYPE_NEED_HEADER);
		return response;
	}

	@Override
	public JSONObject pwdDynamicPwd(String version, String access_token, String s_id, String lock_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject pwdTempPwd(String version, String access_token, String s_id, String lock_no, String pwd_user_name,
			String pwd_user_mobile, String pwd_user_idcard, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockOpenLockHis(String version, String access_token, String s_id, int page_size, int current_page,
			String lock_no, Long searsh_time_start, Long searsh_time_end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject lockValidateOwner(String version, String access_token, String s_id, String account,
			String password, String lock_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject pwdViewFunc(String version, String access_token, String s_id, String lock_no) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
//		System.out.println(getInstance().lockDetails("11.1.116.166"));
	System.out.println(getInstance().pwdList("11.1.116.166", "18710579465"));
	}
}
