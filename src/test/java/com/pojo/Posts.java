package com.pojo;

public class Posts {

	private String title;
	private Integer views;

	// parametrised constructor
	public Posts(String manju, Integer sunita) {
		super();
		this.title = manju;
		this.views = sunita;
	}

	// setters and getters
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

}
