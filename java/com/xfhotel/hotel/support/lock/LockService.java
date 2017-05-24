package com.xfhotel.hotel.support.lock;

import net.sf.json.JSONObject;

public interface LockService {
	/**
	 * 登录
	 * 
	 * @param version
	 * @param s_id
	 * @param account
	 * @param password
	 * @return
	 */
	public JSONObject login(String version, String s_id, String account, String password);

	/**
	 * 查询网关列表
	 * @param vaersion
	 * @param access_token
	 * @param s_id
	 * @param page_size
	 * @param current_page
	 * @param node_no
	 * @param house_code
	 * @return
	 */
	public JSONObject nodeList(String vaersion, String access_token, String s_id, int page_size, int current_page,
			String node_no, String house_code);

	/**
	 * 查询网关详情
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param node_no
	 * @return
	 */
	public JSONObject nodeView(String version, String access_token, String s_id, String node_no);

	/**
	 * 查询门锁列表
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param page_size
	 * @param current_page
	 * @param node_no
	 * @param lock_code
	 * @param house_code
	 * @param roon_code
	 * @return
	 */
	public JSONObject lockList(String version, String access_token, String s_id, int page_size, int current_page,
			String node_no, String lock_code, String house_code, String roon_code);

	/**
	 * 查询门锁详情
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @return
	 */
	public JSONObject lockDetails(String version, String access_token, String s_id, String lock_no);

	/**
	 * 查询门锁密码信息
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_no
	 * @param pwd_user_mobile
	 * @param status
	 * @return
	 */
	public JSONObject pwdList(String version, String access_token, String s_id, String lock_no, int pwd_no,
			String pwd_user_mobile, String status);

	/**
	 * 获取433锁动态密码
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @return
	 */
	public JSONObject pwdDynamicPwd(String version, String access_token, String s_id, String lock_no);

	/**
	 * 获取蓝牙锁一次性密码
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_user_name
	 * @param pwd_user_mobile
	 * @param pwd_user_idcard
	 * @param description
	 * @return
	 */
	public JSONObject pwdTempPwd(String version, String access_token, String s_id, String lock_no,
			String pwd_user_name, String pwd_user_mobile, String pwd_user_idcard, String description);

	/**
	 * 查询开锁记录
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param page_size
	 * @param current_page
	 * @param lock_no
	 * @param searsh_time_start
	 * @param searsh_time_end
	 * @return
	 */
	public JSONObject lockOpenLockHis(String version, String access_token, String s_id, int page_size,
			int current_page, String lock_no, Long searsh_time_start, Long searsh_time_end);

	/**
	 * 校验门锁拥有者权限
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param account
	 * @param password
	 * @param lock_no
	 * @return
	 */
	public JSONObject lockValidateOwner(String version, String access_token, String s_id, String account,
			String password, String lock_no);

	/**
	 * 查看功能密码
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @return
	 */
	public JSONObject pwdViewFunc(String version, String access_token, String s_id, String lock_no);

	/**
	 * 新增自定义密码
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_text
	 * @param valid_time_start
	 * @param valid_time_end
	 * @param pwd_user_name
	 * @param pwd_user_mobile
	 * @param pwd_user_idcard
	 * @param description
	 * @param extra
	 * @return
	 */
	public JSONObject pwdAdd(String version, String access_token, String s_id, String lock_no, String pwd_text,
			String valid_time_start, String valid_time_end, String pwd_user_name, String pwd_user_mobile,
			String pwd_user_idcard, String description, String extra);

	/**
	 * 修改自定义密码
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_text
	 * @param valid_time_start
	 * @param valid_time_end
	 * @param extra
	 * @return
	 */
	public JSONObject pwdUpdate(String version, String access_token, String s_id, String lock_no, String pwd_text,
			String valid_time_start, String valid_time_end, String extra,String pwd_no);

	/**
	 * 新增离线时效密码（离线密码）
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param valid_time_start
	 * @param valid_time_end
	 * @param pwd_user_name
	 * @param pwd_user_mobile
	 * @param pwd_user_idcard
	 * @param description
	 * @return
	 */
	public JSONObject pwdOfflineAdd(String version, String access_token, String s_id, String lock_no,
			String valid_time_start, String valid_time_end, String pwd_user_name, String pwd_user_mobile,
			String pwd_user_idcard, String description);

	/**
	 * 删除密码
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_no
	 * @param pwd_user_mobile
	 * @param extra
	 * @return
	 */
	public JSONObject pwdDelete(String version, String access_token, String s_id, String lock_no, String pwd_no,
			String pwd_user_mobile, String extra);

	/**
	 * 远程开锁
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_user_mobile
	 * @param pwd_user_name
	 * @return
	 */
	public JSONObject lockRemoteOpen(String version, String access_token, String s_id, String lock_no,
			String pwd_user_mobile, String pwd_user_name);

	/**
	 * 门锁授权
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param mobile
	 * @param name
	 * @param allow_auth
	 * @param auth_time_start
	 * @param auth_time_end
	 * @return
	 */
	public JSONObject lockAuth(String version, String access_token, String s_id, String lock_no, String mobile,
			String name, String allow_auth, String auth_time_start, String auth_time_end);

	/**
	 * 门锁接触授权
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param mobile
	 * @return
	 */
	public JSONObject lockCancelAuth(String version, String access_token, String s_id, String lock_no, String mobile);

	/**
	 * 修改功能密码
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param pwd_text
	 * @param valid_time_start
	 * @param valid_time_end
	 * @param extra
	 * @return
	 */
	public JSONObject pwdUpdateFun(String version, String access_token, String s_id, String lock_no, String pwd_text,
			String valid_time_start, String valid_time_end, String extra);

	/**
	 * 修改网关安装信息
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param node_no
	 * @param house_code
	 * @return
	 */
	public JSONObject nodeUpdateInstallInfo(String version, String access_token, String s_id, String node_no,
			String house_code);

	/**
	 * 修改门锁安装信息
	 * 
	 * @param version
	 * @param access_token
	 * @param s_id
	 * @param lock_no
	 * @param house_code
	 * @param room_code
	 * @return
	 */
	public JSONObject lockUpdateInstallInfo(String version, String access_token, String s_id, String lock_no,
			String house_code, String room_code);
}
