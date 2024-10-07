package twitter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Filter {
    /*  Variant 1
     public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
	    List<Tweet> result = new ArrayList<>();
	    for (Tweet tweet : tweets) {
	        if (tweet.getAuthor().equalsIgnoreCase(username)) {
	            result.add(tweet);
	        }
	    }
	    return result;
	}

	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
	    List<Tweet> result = new ArrayList<>();
	    for (Tweet tweet : tweets) {
	        if (!tweet.getTimestamp().isBefore(timespan.getStart()) && !tweet.getTimestamp().isAfter(timespan.getEnd())) {
	            result.add(tweet);
	        }
	    }
	    return result;
	}

	public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
	    List<Tweet> result = new ArrayList<>();
	    List<String> lowerCaseWords = words.stream()
	                                       .map(String::toLowerCase)
	                                       .collect(Collectors.toList());
	    for (Tweet tweet : tweets) {
	        String tweetText = tweet.getText().toLowerCase();
	        for (String word : lowerCaseWords) {
	            if (tweetText.contains(word)) {
	                result.add(tweet);
	                break; // No need to check other words if one is found
	            }
	        }
	    }
	    return result;
	}
     */
 
	/*Variant 2
	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
	    return tweets.stream()
	                 .filter(tweet -> tweet.getAuthor().equalsIgnoreCase(username))
	                 .collect(Collectors.toSet()) // Use a Set to avoid duplicates
	                 .stream().collect(Collectors.toList());
	}

	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
	    return tweets.stream()
	                 .filter(tweet -> !tweet.getTimestamp().isBefore(timespan.getStart()) 
	                               && !tweet.getTimestamp().isAfter(timespan.getEnd()))
	                 .collect(Collectors.toSet())
	                 .stream().collect(Collectors.toList());
	}

	public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
	    List<String> lowerCaseWords = words.stream()
	                                       .map(String::toLowerCase)
	                                       .collect(Collectors.toSet()) // Use a Set to avoid duplicates
	                                       .stream().collect(Collectors.toList());
	    return tweets.stream()
	                 .filter(tweet -> {
	                     String tweetText = tweet.getText().toLowerCase();
	                     return lowerCaseWords.stream().anyMatch(tweetText::contains);
	                 })
	                 .collect(Collectors.toList());
	}
	**/
	/* Variant 3*/
	
	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (tweet.getAuthor().equalsIgnoreCase(username)) {
                result.add(tweet);
            }
        }
        return result;
    }

    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (!tweet.getTimestamp().isBefore(timespan.getStart()) 
                && !tweet.getTimestamp().isAfter(timespan.getEnd())) {
                result.add(tweet);
            }
        }
        return result;
    }

   
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            String tweetText = tweet.getText().toLowerCase();
            for (String word : words) {
                if (tweetText.contains(word.toLowerCase())) {
                    result.add(tweet);
                    break; // Avoid adding the same tweet multiple times
                }
            }
        }
        return result;
    }


}
