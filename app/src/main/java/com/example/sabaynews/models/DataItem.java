package com.example.sabaynews.models;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getStatus(){
		return status;
	}
}