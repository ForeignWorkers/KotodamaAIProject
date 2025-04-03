package controller;

import model.boardDAO;
import model.boardDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detailPageCon.do")
public class detailPageCon extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        reqPro(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        boardDAO dao = new boardDAO();
        boardDTO dto = new boardDTO();
        dto = dao.selectOneBoard(Integer.parseInt(request.getParameter("boardId")));

        request.setAttribute("board", dto);

        RequestDispatcher view = request.getRequestDispatcher("detailPage.jsp");
        view.forward(request, response);
    }
}