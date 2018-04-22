package com.servlet;

import com.stringmatcher.algorithm.KMPAlgorithm;
import com.stringmatcher.algorithm.Regex;
import com.stringmatcher.algorithm.BMAlgorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoAlgorithm
 */
@WebServlet("/DoAlgorithm2")
public class DoAlgorithm2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAlgorithm2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		HttpSession session = request.getSession(false);
		
		@SuppressWarnings("unchecked")
		List<String> userNames = (ArrayList<String>) session.getAttribute("usernames");
		@SuppressWarnings("unchecked")
		List<String> posts = (ArrayList<String>) session.getAttribute("posts");
		String keyword = request.getParameter("keyword");
		
		int algo = Integer.parseInt(request.getParameter("algo"));
		PrintWriter out = response.getWriter();
		ArrayList<Integer> filtered = new ArrayList<Integer>();
		if (algo == 1) { //kmp
    		out.print("<div id=\"Spam\" class=\"tabcontent\">");
			for (int i = 0; i < userNames.size(); i++) {
				KMPAlgorithm kmpAlgo = new KMPAlgorithm(posts.get(i), keyword);
				int matchAt = kmpAlgo.matchAt();
            	String highlightedText = "";
            	if (matchAt > -1) { //ketemu
    				out.println("<div class=\"tweetpost\">");
                	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
                	out.print("@" + userNames.get(i) + " tweets:</p>");
                	out.println("<hr>");
                	if (matchAt > 0) {
                		highlightedText = posts.get(i).substring(0, matchAt-1) + " <mark>" + posts.get(i).substring(matchAt, matchAt + keyword.length()) + "</mark>";
                		if (matchAt+keyword.length() != posts.get(i).length())
                			highlightedText += posts.get(i).substring(matchAt+keyword.length(), posts.get(i).length());
                	} else {
                		highlightedText = "<mark>" + posts.get(i).substring(matchAt, keyword.length()) + "</mark>" + posts.get(i).substring(keyword.length(), posts.get(i).length());
                	} 
                	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + highlightedText + "</pre>\n</div>");
				} else {
					filtered.add(i);
				}
			}
			out.println("</div>");
		} else if (algo == 2) {//bm
    		out.print("<div id=\"Spam\" class=\"tabcontent\">");
			for (int i = 0; i < userNames.size(); i++) {
				BMAlgorithm bmAlgo = new BMAlgorithm(posts.get(i), keyword);
				int matchAt = bmAlgo.matchAt();
            	String highlightedText = "";
            	if (matchAt > -1) { //ketemu
    				out.println("<div class=\"tweetpost\">");
                	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
                	out.print("@" + userNames.get(i) + " tweets:</p>");
                	out.println("<hr>");
                	if (matchAt > 0) {
                		highlightedText = posts.get(i).substring(0, matchAt-1) + " <mark>" + posts.get(i).substring(matchAt, matchAt + keyword.length()) + "</mark>";
                		if (matchAt+keyword.length() != posts.get(i).length())
                			highlightedText += posts.get(i).substring(matchAt+keyword.length(), posts.get(i).length());
                	} else {
                		highlightedText = "<mark>" + posts.get(i).substring(matchAt, keyword.length()) + "</mark>" + posts.get(i).substring(keyword.length(), posts.get(i).length());
                	} 
                	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + highlightedText + "</pre>\n</div>");
				} else {
					filtered.add(i);
				}
			}
			out.println("</div>");
		} else { //regex
			Regex r = new Regex(posts.get(0), keyword);
			out.println("<div class=\"tweetpost\">");
			out.print("<h3>Regex:</h3>");
        	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + r.getRegex() + "</pre>\n</div>");
    		out.print("<div id=\"Spam\" class=\"tabcontent\">");
			for (int i = 0; i < userNames.size(); i++) {
				Regex regexAlgo = new Regex(posts.get(i), keyword);
				boolean match = regexAlgo.matches();
				String highlightedText = "";
				if (match == true) { //ketemu
					int matchF;
					int matchL;
					int before;
					out.println("<div class=\"tweetpost\">");
		        	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
		        	out.print("@" + userNames.get(i) + " tweets:</p>");
		        	out.println("<hr>");
					for (int j=0; j<regexAlgo.getPatternSize(); j++)	{
						before = 0;
						matchF = regexAlgo.getIdxStartAt(j);
						matchL = regexAlgo.getIdxFinishAt(j);
	                	if (matchF > 0) {
	                		highlightedText += posts.get(i).substring(before, matchF-1) + " <mark>" + posts.get(i).substring(matchF, matchL) + "</mark>";
	                	} else {
	                		highlightedText += "<mark>" + posts.get(i).substring(matchF, matchL) + "</mark>";
	                	}
	                	before = matchL+1;
					}
					matchL = regexAlgo.getIdxFinishAt(regexAlgo.getPatternSize()-1);
	            	if (matchL != posts.get(i).length())	{
	        			highlightedText += posts.get(i).substring(matchL, posts.get(i).length());
	            	}
	            	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + highlightedText + "</pre>\n</div>");
				} else	{
					filtered.add(i);
				}
			}
			out.println("</div>");
		}
		out.print("<div id=\"Filtered\" class=\"tabcontent\">");
		for (int i = 0; i < filtered.size(); i++) {
			out.println("<div class=\"tweetpost\">");
        	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
        	out.print("@" + userNames.get(filtered.get(i)) + " tweets:</p>");
        	out.println("<hr>");
			out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + posts.get(filtered.get(i)) + "</pre>\n</div>");
		}
		out.print("</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
