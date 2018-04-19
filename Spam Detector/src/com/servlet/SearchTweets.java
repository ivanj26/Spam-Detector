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
	public static final String consumerKey = "N71R8sklH5dmwM90HP64pDdtK";
	public static final String consumerSecret = "AxqgL8bG6oT2ScSR06xS18y4kuEHo5HJmwiCKgIzYlOEBCRmeC";
	public static final String accessToken = "3603712758-ihWbDohJLacQGZELWQbooMNFB0aB9Ra4B7weXNf";
	public static final String accessTokenSecret = "Ji2QaxEOwymNqU30PAvtU1GGg8kXfs7NQIMfSHU4tTXP3";

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
                }
               session.setAttribute("usernames", userNames);
               session.setAttribute("posts", posts);
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
