package pl.kofun.mavis.models;

public class Post {
	private String url;
	private String title;
	
	public Post(String url, String title)
	{
		this.setTitle(title);
		this.setUrl(url);
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
