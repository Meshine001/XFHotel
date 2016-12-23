package com.xfhotel.hotel.support;

public class StringSplitUtil {
	public static String buildStrGroup(String[] str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			String s = "";
			if ("".equals(str[i])) {
				s = "-1";
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

	public static void main(String[] args) {
		String[] ss = { "123", "1233", "1213", "1242" };
		System.out.println(buildStrGroup(ss));
	}
}
