package com.servlet;

import com.stringmatcher.algorithm.*;
import java.io.IOException;
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
		if (algo == 1) { //kmp
			for (int i = 0; i < userNames.size(); i++) {
				KMPAlgorithm kmpAlgo = new KMPAlgorithm(posts.get(i), keyword);
				int matchAt = kmpAlgo.matchAt();
				if (matchAt > -1) { //ketemu
					
				}
			}
		} else if (algo == 2) {//bm
		
		} else { //regex
			
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
