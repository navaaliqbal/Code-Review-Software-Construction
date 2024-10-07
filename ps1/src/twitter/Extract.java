package twitter;

import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {
	/* Variant 1*/
	 public static Timespan getTimespan(List<Tweet> tweets) {
	        if (tweets.isEmpty()) {
	            throw new IllegalArgumentException("List of tweets cannot be empty.");
	        }

	        Instant earliest = tweets.stream()
	                                  .map(Tweet::getTimestamp)
	                                  .min(Instant::compareTo)
	                                  .orElseThrow(() -> new IllegalArgumentException("Unexpected error."));
	                                  
	        Instant latest = tweets.stream()
	                                .map(Tweet::getTimestamp)
	                                .max(Instant::compareTo)
	                                .orElseThrow(() -> new IllegalArgumentException("Unexpected error."));

	        return new Timespan(earliest, latest);
	    }

	    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
	        Set<String> mentionedUsers = new HashSet<>();
	        Pattern pattern = Pattern.compile("(?<=\\s|^)@[a-zA-Z0-9_]+(?=\\s|$)");

	        for (Tweet tweet : tweets) {
	            Matcher matcher = pattern.matcher(tweet.getText());
	            while (matcher.find()) {
	                String username = matcher.group().substring(1).toLowerCase();
	                mentionedUsers.add(username);
	            }
	        }
	        return mentionedUsers;
	    }
	
/* Variant 2
public class Extract {

    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            throw new IllegalArgumentException("List of tweets cannot be empty.");
        }

        TreeSet<Instant> timestamps = new TreeSet<>();
        for (Tweet tweet : tweets) {
            timestamps.add(tweet.getTimestamp());
        }
        
        return new Timespan(timestamps.first(), timestamps.last());
    }

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        Pattern pattern = Pattern.compile("(?<=\\s|^)@[a-zA-Z0-9_]+(?=\\s|$)");

        for (Tweet tweet : tweets) {
            Matcher matcher = pattern.matcher(tweet.getText());
            while (matcher.find()) {
                String username = matcher.group().substring(1).toLowerCase();
                mentionedUsers.add(username);
            }
        }
        return mentionedUsers;
    }
}


*/
/*Variant 3
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            throw new IllegalArgumentException("List of tweets cannot be empty.");
        }

        Instant earliest = Instant.MAX;
        Instant latest = Instant.MIN;

        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (timestamp.isBefore(earliest)) {
                earliest = timestamp;
            }
            if (timestamp.isAfter(latest)) {
                latest = timestamp;
            }
        }

        return new Timespan(earliest, latest);
    }

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        Pattern pattern = Pattern.compile("(?<=\\s|^)@[a-zA-Z0-9_]+(?=\\s|$)");

        for (Tweet tweet : tweets) {
            Matcher matcher = pattern.matcher(tweet.getText());
            while (matcher.find()) {
                String username = matcher.group().substring(1).toLowerCase();
                mentionedUsers.add(username);
            }
        }
        return mentionedUsers;
    }*/
}






