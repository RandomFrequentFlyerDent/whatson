package models;

import java.awt.Image;

public class Movie {
	// Maybe expand with image, cast and such
	private String url;
	private String title;
	
	public Movie(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}
}
