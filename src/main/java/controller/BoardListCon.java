package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServlet;
import model.membersDAO;
import model.membersDTO;

@WebServlet("/BoardListCon.do")
public class BoardListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//전체 게시글의 수를 담을 인트
		int postCount = 0;
		
		membersDAO mDAO = new membersDAO();
//		postCount = mDAO.getAllPostCount();
		
		//한 페이지에 받아 올 최대 포스트 갯수
		int pageSize = 10;
		
		//수정, 삭제시 받아오는 메시지
		String msg = (String)request.getAttribute("msg");
		
		//BoardList.jsp 페이징 부분
		String pageNum = request.getParameter("pageNum"); 
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int number = 0;
		int currentPage = Integer.parseInt("pageNum");
		int startRow = (currentPage - 1 ) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		number = postCount - (currentPage - 1) * pageSize;
		
//		Vector<membersDTO> v = mDAO.getAllPost(startRow, endRow);
		
		request.setAttribute("postCount", postCount);
		request.setAttribute("msg", msg);
//		request.setAttribute("v", v);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("number", number);
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardList.jsp");
		dis.forward(request, response);
		
	}

	
}
