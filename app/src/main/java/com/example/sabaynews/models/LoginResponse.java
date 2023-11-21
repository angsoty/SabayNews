package com.example.sabaynews.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

	@SerializedName("expiresIn")
	private int expiresIn;

	@SerializedName("accessToken")
	private String accessToken;

	@SerializedName("tokenType")
	private String tokenType;

	@SerializedName("refreshToken")
	private String refreshToken;

	public int getExpiresIn(){
		return expiresIn;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public String getTokenType(){
		return tokenType;
	}

	public String getRefreshToken(){
		return refreshToken;
	}
}