package pl.kofun.mavis.utils;

import java.util.LinkedList;
import java.util.List;

import pl.kofun.mavis.Post;
import pl.kofun.mavis.Project;

import java.lang.StringBuilder;

public class stringAdjuster {

	public static LinkedList<String> AdjustToBookList(LinkedList<String> listToAdjust)
	{
		LinkedList<String> ret = new LinkedList<>();
	
		StringBuilder lineForRet = new StringBuilder();
		
		while(listToAdjust.size() > 0)
		{
			lineForRet.append("<strong>");
			lineForRet.append(listToAdjust.removeFirst());
			lineForRet.append("</strong> -");
			
			if(listToAdjust.size()!=0)
			{
				lineForRet.append("\n\n");
			}
			
			ret.add(lineForRet.toString());
			lineForRet.setLength(0);
		}
		
		return ret;
	}
	
	public static String convertProjectToPost(Project projectToConvert, String tagUrl)
	{		
		StringBuilder ret = new StringBuilder();
		
		if(projectToConvert.getName() != null)
		{
			ret.append("<h3>");
			ret.append(projectToConvert.getName());
			ret.append("</h3>");
			
			ret.append("\n\n");
		}
		
		if(projectToConvert.getTechnologies() != null)
		{
			List<String> technologies = projectToConvert.getTechnologies();
			if(technologies.size() > 0)
			{
				ret.append("<strong>Technologies: </strong>");
				String prefix = "";
				for (String technology : technologies) {
					ret.append(prefix);
					prefix = ", ";	
					ret.append(String.format("<a href='%s%s'>",tagUrl,technology));
					ret.append(technology);
					ret.append("</a>");
				}
				ret.append("\n");
			}
		}
		
		if(projectToConvert.getRepository() != null)
		{
			ret.append("<strong>Repository: </strong>");
			ret.append(String.format("<a href='%s'>", projectToConvert.getRepository()));
			ret.append("on github");
			ret.append("</a>");
			ret.append("\n");
		}
		
		if(projectToConvert.getDescription() != null)
		{
			ret.append(projectToConvert.getDescription());
			ret.append("\n");
		}
		
		if(projectToConvert.getDevPosts() != null)
		{
			List<Post> devPosts = projectToConvert.getDevPosts();
			if(devPosts.size() > 0)
			{
				ret.append("<strong>Dev posts: </strong><ul>");
				for(Post post : devPosts)
				{
					ret.append("<li>");
					ret.append("<a href='");
					ret.append(post.getUrl());
					ret.append("'>");
					ret.append(post.getTitle());
					ret.append("</a></li>\n");
				}
			}
		}
		
		if(projectToConvert.getPostMortem() != null)
		{
			ret.append("<a href='");
			ret.append(projectToConvert.getPostMortem());
			ret.append("'>Post Mortem</a>");
		}
		ret.append("\n");
		
		return ret.toString();
	}
}
