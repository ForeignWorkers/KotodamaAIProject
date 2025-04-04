package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.membersDAO;
import model.membersDTO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
    	
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        membersDAO dao = new membersDAO();
        
        // 로그인 검증
        if (dao.checkLogin(id, password)) {  // 아이디와 비밀번호가 맞으면
        	
        	//로그인 성공 시 해당 내용 실행
        	//userNum 조회
        	int userNum = dao.getUserNumById(id);
        	
        	// 유저 정보 가져오기
        	membersDTO user = dao.getUserNum(userNum);
        	
        	// 세션 가져오기
        	HttpSession session = request.getSession();
        	// 유저 정보 세션에 저장
        	session.setAttribute("userbean", user);
        	// 세션 유지시간 설정 1800초
        	session.setMaxInactiveInterval(1800);
        	
        	// 로그인 성공 후 LoginCheckCon으로
        	request.getRequestDispatcher("LoginCheckCon").forward(request, response);  
        } else {
            request.setAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}