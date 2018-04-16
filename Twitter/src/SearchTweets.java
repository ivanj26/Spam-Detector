import twitter4j.*;

import java.util.List;
import twitter4j.conf.ConfigurationBuilder;
/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */

public class SearchTweets {
	public static final String consumerKey = "N71R8sklH5dmwM90HP64pDdtK";
	public static final String consumerSecret = "AxqgL8bG6oT2ScSR06xS18y4kuEHo5HJmwiCKgIzYlOEBCRmeC";
	public static final String accessToken = "3603712758-ihWbDohJLacQGZELWQbooMNFB0aB9Ra4B7weXNf";
	public static final String accessTokenSecret = "Ji2QaxEOwymNqU30PAvtU1GGg8kXfs7NQIMfSHU4tTXP3";
    /**
     * Usage: java twitter4j.examples.search.SearchTweets [query]
     *
     * @param args search query
     */
    public static void main(String[] args) {
    	ConfigurationBuilder cb = new ConfigurationBuilder().setDebugEnabled(true)
    			.setOAuthConsumerKey(consumerKey)
    			.setOAuthConsumerSecret(consumerSecret)
    			.setOAuthAccessToken(accessToken)
    			.setOAuthAccessTokenSecret(accessTokenSecret)
    			.setTweetModeExtended(true);
        TwitterFactory factory = new TwitterFactory(cb.build());
        Twitter twitter = factory.getInstance();
        try {
            Query query = new Query("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssdddddddddddddddddddddddddddddddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}