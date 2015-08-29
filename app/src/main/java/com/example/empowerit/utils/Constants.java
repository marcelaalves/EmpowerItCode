package com.example.empowerit.utils;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Constants {
	
	public static final String emailWobTest = "wob@empowerit.com";
	public static final String emailCoorpTest = "coorp@empowerit.com";
	public static final String password = "password";
	public static final String clientId = "kMRaR35PmKwZRqtEfznNkQUaiitKr0Ij";
	public static final String clientSecret = "rX8DZ5CfrFrMFhtmwaUWxMptC3UiqWvd";
	public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	public static final JsonFactory JSON_FACTORY = new JacksonFactory();


}
