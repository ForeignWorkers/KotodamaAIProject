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
        commentDAO commentDAO = new commentDAO();
        boardDTO dto = new boardDTO();
        Vector<commentDTO> commentDTOS = new Vector<>();
        int findId =  Integer.parseInt(request.getParameter("boardId"));
        System.out.println("ssss" + findId);
        String isCountViewStr = request.getParameter("isCountView");
        boolean isCountView = Boolean.parseBoolean(isCountViewStr);
        System.out.println("boooll" + isCountView);

        dto = dao.selectOneBoard(findId, isCountView);
        int count = 0;
        int commentCount = commentDAO.getCommentCount(findId);
        int pageSize = 10;
        String pageNum = request.getParameter("pageNum");

        if(pageNum == null || pageNum.equals("0")){
            pageNum = "1";
        }

        int number = 0;
        int currentPage = Integer.parseInt(pageNum);
        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = startRow * pageSize;

        number = count - (currentPage - 1) * pageSize;

        commentDTOS = commentDAO.getCommentRange(findId, startRow, endRow);

        request.setAttribute("board", dto);
        request.setAttribute("comments", commentDTOS);
        request.setAttribute("commentCount", commentCount);

        request.setAttribute("count", count);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("number", number);

        RequestDispatcher view = request.getRequestDispatcher("detailPage.jsp");
        view.forward(request, response);
    }
}