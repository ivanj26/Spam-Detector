package com.servlet;

import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class SearchTweets
 */
@WebServlet("/SearchTweets")
public class SearchTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String consumerKey = "<YOUR_CONSUMER_KEY>";
	public static final String consumerSecret = "<YOUR_CONSUMER_SECRET_KEY>";
	public static final String accessToken = "<YOUR_ACCESS_TOKEN>";
	public static final String accessTokenSecret = "<YOUR_ACCESS_TOKEN_SECRET>";

    /**
     * Default constructor. 
     */
    public SearchTweets() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConfigurationBuilder cb = new ConfigurationBuilder().setDebugEnabled(true)
    			.setOAuthConsumerKey(consumerKey)
    			.setOAuthConsumerSecret(consumerSecret)
    			.setOAuthAccessToken(accessToken)
    			.setOAuthAccessTokenSecret(accessTokenSecret)
    			.setTweetModeExtended(true);
		HttpSession session=request.getSession(); 
		
		TwitterFactory factory = new TwitterFactory(cb.build());
        Twitter twitter = factory.getInstance();
        response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
        try {
        	Query query = new Query(request.getParameter("search"));
            QueryResult result;
            
            //List of username
            List<String> userNames = new ArrayList<String>();
            List<String> posts = new ArrayList<String>();
            int i = 0;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	userNames.add(tweet.getUser().getScreenName());
                	posts.add(tweet.getText());
                	out.println("<div class=\"tweetpost\">");
                	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
                	out.print("@" + tweet.getUser().getScreenName() + " tweets:</p>");
                	out.println("<hr>");
                	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + tweet.getText() + "</pre>\n</div>");
                    //out.print("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                	if (i == 25)
                		break;
                	i++;
                }
               session.setAttribute("usernames", userNames);
               session.setAttribute("posts", posts);
               if (i == 25)
            	   break;
            } while ((query = result.nextQuery()) != null);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
