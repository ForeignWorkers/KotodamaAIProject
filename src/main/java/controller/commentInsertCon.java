package controller;

import model.boardDAO;
import model.boardDTO;
import model.commentDAO;
import model.commentDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet("/commentInsertCon.do")
public class commentInsertCon extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        reqPro(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        boardDAO dao = new boardDAO();
        Vector<commentDTO> commentDTOS = new Vector<>();

        String comment = request.getParameter("comment");
        String userName = request.getParameter("userName");
        int userNum = Integer.parseInt(request.getParameter("userNum"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        commentDAO commentDAO = new commentDAO();
        commentDAO.insertComment(comment,userNum,userName,boardId);

        commentDTOS = commentDAO.getComments(boardId);
        boardDTO dto = dao.selectOneBoard(boardId);

        request.setAttribute("board", dto);
        request.setAttribute("comments", commentDTOS);

        RequestDispatcher view = request.getRequestDispatcher("detailPageCon.do");
        view.forward(request, response);
    }
}