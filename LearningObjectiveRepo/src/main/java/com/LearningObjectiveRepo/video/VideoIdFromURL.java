package com.LearningObjectiveRepo.video;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Fetching source and source Id from video Url
public class VideoIdFromURL {

	public static Map<String, String> getUrlId(String url) {

		String source = url.split("/")[2];

		if (source.equals("www.youtube.com") || source.equals("youtu.be"))

		{
			source = "youtube";
			String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";
			String sourceId = sourceId(pattern, url);     
			Map<String, String> map = new HashMap<String, String>();
			map.put(source, sourceId);
			return map;                          //returning source and sourceId in the form of map

		}

		return null;

	}

	//Fetching sourceId from url
	public static String sourceId(String pattern, String url) {
		Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = compiledPattern.matcher(url);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

}
