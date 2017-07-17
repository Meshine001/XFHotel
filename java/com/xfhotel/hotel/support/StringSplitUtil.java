package com.xfhotel.hotel.support;

public class StringSplitUtil {
	public static String buildStrGroup(String[] str) {
		if(str == null)return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			String s = "";
			if ("".equals(str[i])) {
				s = "";
			} else {
				s = str[i];
			}
			if (i != str.length - 1) {

				sb.append(s).append("@");
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}


}
