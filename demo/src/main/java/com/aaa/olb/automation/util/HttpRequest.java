package com.aaa.olb.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpRequest {
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);

			URLConnection connection = realUrl.openConnection();

			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			connection.connect();

			Map<String, List<String>> map = connection.getHeaderFields();

			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("failed to send the GET: " + e);
			e.printStackTrace();
		}

		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static void sendPost(String url, String param) throws Exception {
		HttpPost httpPost = createConnectivity(url);
		executeReq(param, httpPost);
	}

	private static HttpPost createConnectivity(String restUrl) {
		HttpPost post = new HttpPost(restUrl);
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "application/json");
		return post;
	}

	private static void executeReq(String jsonData, HttpPost httpPost) throws Exception {
		try {
			executeHttpRequest(jsonData, httpPost);
		} catch (UnsupportedEncodingException e) {
			System.out.println("error while encoding api url : " + e);
			throw e;
		} catch (IOException e) {
			System.out.println("ioException occured while sending http request : " + e);
			throw e;
		} catch (Exception e) {
			System.out.println("exception occured while sending http request : " + e);
			throw e;
		} finally {
			httpPost.releaseConnection();
		}
	}

	private static void executeHttpRequest(String jsonData, HttpPost httpPost)
			throws UnsupportedEncodingException, IOException {
		HttpResponse response = null;
		String line = "";
		StringBuffer result = new StringBuffer();
		httpPost.setEntity(new StringEntity(jsonData));
		HttpClient client = HttpClientBuilder.create().build();
		response = client.execute(httpPost);
		//System.out.println("Post parameters : " + jsonData);
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		
		if (response.getStatusLine().getStatusCode() != 200) {
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			System.out.println(result.toString());
			System.exit(0);
		}
	}
}
