/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        // Use Java Streams to filter tweets by author
        return tweets.stream()
                     .filter(tweet -> tweet.getAuthor().equalsIgnoreCase(username))
                     .collect(Collectors.toList());
    }

    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        // Use Java Streams to filter tweets within the timespan
        return tweets.stream()
                     .filter(tweet -> !tweet.getTimestamp().isBefore(timespan.getStart()) &&
                                      !tweet.getTimestamp().isAfter(timespan.getEnd()))
                     .collect(Collectors.toList());
    }
   
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        // Use Java Streams to filter tweets that contain any of the words (case-insensitive)
        return tweets.stream()
                     .filter(tweet -> words.stream()
                                           .anyMatch(word -> tweet.getText().toLowerCase().contains(word.toLowerCase())))
                     .collect(Collectors.toList());
    }

}
