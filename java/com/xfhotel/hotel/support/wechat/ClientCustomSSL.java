/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.xfhotel.hotel.support.wechat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 带有证书的请求
 * 
 * @author Ming
 *
 */
@SuppressWarnings("deprecation")
public class ClientCustomSSL {
	
	public static KeyStore keyStore = null;
	public static CloseableHttpClient httpclient = null;
	
	static{
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * post
	 * @param url
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String str)
			throws Exception {
		// 处理请求地址
				URI uri = new URI(url);
				HttpPost post = new HttpPost(uri);

				post.setEntity(new StringEntity(str,"utf-8"));
				// 执行请求
				HttpResponse response = httpclient.execute(post);

				if (response.getStatusLine().getStatusCode() == 200) {
					// 处理请求结果
					StringBuffer buffer = new StringBuffer();
					InputStream in = null;
					try {
						in = response.getEntity().getContent();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(in));
						String line = null;
						while ((line = reader.readLine()) != null) {
							buffer.append(line);
						}

					} finally {
						// 关闭流
						if (in != null)
							in.close();
					}

					return buffer.toString();
				} else {
					return null;
				}
		
	}
	
	private static void init() throws Exception{
		if(keyStore == null){
			keyStore = KeyStore.getInstance("PKCS12");
			String filePath = ClientCustomSSL.class.getClassLoader().getResource("apiclient_cert.p12").getPath();
			FileInputStream instream = new FileInputStream(new File(filePath));
			try {
				keyStore.load(instream, Config.MCHID.toCharArray());
			} finally {
				instream.close();
			}
		}
		
		if(httpclient ==  null){
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Config.MCHID.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		}
	}
	
	
	

	public final static void main(String[] args) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File("C:/Users/Ming/apiclient_cert.p12"));
		try {
			keyStore.load(instream, Config.MCHID.toCharArray());
		} finally {
			instream.close();
		}

		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Config.MCHID.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {

			HttpGet httpget = new HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");

			System.out.println("executing request" + httpget.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						System.out.println(text);
					}

				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

}
