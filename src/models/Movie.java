package models;

public class Movie implements Comparable<Movie> {
	// Maybe expand with image, cast and such
	private String url;
	private String title;

	public Movie(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}
	
	public String getUrl() {
		return this.url;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", title, url);
	}

	@Override
	public int compareTo(Movie o) {
		return this.url.compareTo(o.url);
	}

}
