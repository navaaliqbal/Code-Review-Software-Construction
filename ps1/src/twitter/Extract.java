package twitter;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {

    public static Timespan getTimespan(List<Tweet> tweets) {
        Instant start = tweets.get(0).getTimestamp();
        Instant end = start;
        
        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (timestamp.isBefore(start)) {
                start = timestamp;
            }
            if (timestamp.isAfter(end)) {
                end = timestamp;
            }
        }
        return new Timespan(start, end);
    }

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        Pattern mentionPattern = Pattern.compile("(?<!\\w)@(\\w+)(?!\\w)");
        
        for (Tweet tweet : tweets) {
            Matcher matcher = mentionPattern.matcher(tweet.getText());
            while (matcher.find()) {
                mentionedUsers.add(matcher.group(1).toLowerCase());
            }
        }
        return mentionedUsers;
    }
}
