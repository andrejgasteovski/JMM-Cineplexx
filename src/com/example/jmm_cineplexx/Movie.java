package com.example.jmm_cineplexx;

public class Movie {

	private String title;
	private String image;
	private String duration;
	private String genre;
	
	public Movie(){
		title = "";
		image = "";
		duration = "";
		genre = "";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String rating) {
		this.duration = rating;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", image=" + image + ", duration="
				+ duration + ", genre=" + genre + "]";
	}
	
	
	
}
