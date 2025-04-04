package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.membersDTO;


@WebServlet("/LoginCheckCon")
public class LoginCheckCon extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

				// 기존 세션 확인 (세션이 없으면 null 반환)
				HttpSession session = request.getSession(false); 

		        if (session != null && session.getAttribute("userbean") != null) { // 세션이 존재하고, userbean이 있으면
		        	membersDTO user = (membersDTO) session.getAttribute("userbean");

		            // 세션에 저장하기 (session.setAttribute)
		            session.setAttribute("loginStatus", "login");
		            session.setAttribute("userNikname", user.getNikname());
		        } else {
		            // 로그인이 안 된 경우
		        	if (session != null) {
		        		session.setAttribute("loginStatus", "not_login");
					}
		        }
		        
		        response.sendRedirect("MainPage.jsp"); // 리다이렉트로 이동
		        //request.getRequestDispatcher("MainPage.jsp").forward(request, response);
   
	}
}
	

