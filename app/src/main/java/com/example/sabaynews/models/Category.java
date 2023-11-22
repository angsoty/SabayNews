package com.example.sabaynews.models;

import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("Status")
	private String status;

	@SerializedName("nameKh")
	private String nameKh;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setNameKh(String nameKh){
		this.nameKh = nameKh;
	}

	public String getNameKh(){
		return nameKh;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"status = '" + status + '\'' + 
			",nameKh = '" + nameKh + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}