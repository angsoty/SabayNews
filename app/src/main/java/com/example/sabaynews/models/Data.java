package com.example.sabaynews.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("slides")
	private List<SlidesItem> slides;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public void setSlides(List<SlidesItem> slides){
		this.slides = slides;
	}

	public List<SlidesItem> getSlides(){
		return slides;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"slides = '" + slides + '\'' + 
			",categories = '" + categories + '\'' + 
			"}";
		}
}