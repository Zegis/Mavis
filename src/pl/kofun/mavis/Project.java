package pl.kofun.mavis;

import java.util.List;

public class Project {
	private String name;
	private String description;
	private List<String> technologies;
	private String repository;
	private List<Post> devPosts;
	private String postMortem;
	
	public Project(String name)
	{
		this.setName(name);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<String> technologies) {
		this.technologies = technologies;
	}
	
	public void addTechnology(String technology)
	{
		this.technologies.add(technology);
	}
	
	public void removeTechnology(String technology)
	{
		this.technologies.remove(technology);
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getPostMortem() {
		return postMortem;
	}

	public void setPostMortem(String postMortem) {
		this.postMortem = postMortem;
	}

	public List<Post> getDevPosts() {
		return devPosts;
	}

	public void setDevPosts(List<Post> devPosts) {
		this.devPosts = devPosts;
	}
	
	public void addDevPost(Post devPost)
	{
		this.devPosts.add(devPost);
	}
	
	public void removeDevPost(Post devPost)
	{
		this.devPosts.remove(devPost);
	}
}
