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
@WebServlet("/DoAlgorithm")
public class DoAlgorithm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAlgorithm() {
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
		if (algo == 1) { //kmp
			for (int i = 0; i < userNames.size(); i++) {
				KMPAlgorithm kmpAlgo = new KMPAlgorithm(posts.get(i), keyword);
				int matchAt = kmpAlgo.matchAt();
				out.println("<div class=\"tweetpost\">");
            	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
            	out.print("@" + userNames.get(i) + " tweets:</p>");
            	out.println("<hr>");
				if (matchAt > -1) { //ketemu
                	String highlightedText = null;
                	if (matchAt > 0) {
                		highlightedText = posts.get(i).substring(0, matchAt-1) + " <mark>" + posts.get(i).substring(matchAt, matchAt + keyword.length()) + "</mark>";
                		if (matchAt+keyword.length() != posts.get(i).length())
                			highlightedText += posts.get(i).substring(matchAt+keyword.length(), posts.get(i).length());
                	} else {
                		highlightedText = "<mark>" + posts.get(i).substring(matchAt, keyword.length()) + "</mark>" + posts.get(i).substring(keyword.length(), posts.get(i).length());
                	} 
                	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + highlightedText + "</pre>\n</div>");
				} else {
					out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + posts.get(i) + "</pre>\n</div>");
				}
			}
		} else if (algo == 2) {//bm
			for (int i = 0; i < userNames.size(); i++) {
				BMAlgorithm bmAlgo = new BMAlgorithm(posts.get(i), keyword);
				int matchAt = bmAlgo.matchAt();
				out.println("<div class=\"tweetpost\">");
            	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
            	out.print("@" + userNames.get(i) + " tweets:</p>");
            	out.println("<hr>");
				if (matchAt > -1) { //ketemu
                	String highlightedText = null;
                	if (matchAt > 0) {
                		highlightedText = posts.get(i).substring(0, matchAt-1) + " <mark>" + posts.get(i).substring(matchAt, matchAt + keyword.length()) + "</mark>";
                		if (matchAt+keyword.length() != posts.get(i).length())
                			highlightedText += posts.get(i).substring(matchAt+keyword.length(), posts.get(i).length());
                	} else {
                		highlightedText = "<mark>" + posts.get(i).substring(matchAt, keyword.length()) + "</mark>" + posts.get(i).substring(keyword.length(), posts.get(i).length());
                	} 
                	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + highlightedText + "</pre>\n</div>");
				} else {
					out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + posts.get(i) + "</pre>\n</div>");
				}
			}
		} else { //regex
			boolean match;
			Regex regexAlgo = new Regex();
			for (int i = 0; i < userNames.size(); i++) {
				regexAlgo.proccess(posts.get(i), keyword);
				match = regexAlgo.matches();
				out.println("<div class=\"tweetpost\">");
            	out.print("<p id=\"username\" style=\"font-size: 14; font-family: Ralewayregular; text-align: left\">");
            	out.print("@" + userNames.get(i) + " tweets:</p>");
            	out.println("<hr>");
				if (match == true) { //ketemu
					int matchF;
					int matchL;
					String highlightedText = null;
					for (int j=0; j<regexAlgo.getPatternSize(); j++)	{
						matchF = regexAlgo.getIdxStartAt(j);
						matchL = regexAlgo.getIdxFinishAt(j);
	                	if (matchF > 0) {
	                		highlightedText = posts.get(i).substring(0, matchF-1) + " <mark>" + posts.get(i).substring(matchF, matchL) + "</mark>";
	                		if (matchL != posts.get(i).length())
	                			highlightedText += posts.get(i).substring(matchL, posts.get(i).length());
	                	} else {
	                		highlightedText = "<mark>" + posts.get(i).substring(matchF, matchL) + "</mark>" + posts.get(i).substring(keyword.length(), posts.get(i).length());
	                	}
					}
                	out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + highlightedText + "</pre>\n</div>");
				} else {
					out.print("<pre id = \"post\" style=\"white-space: inherit;\">" + posts.get(i) + "</pre>\n</div>");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
