package controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.membersDAO;
import model.membersDTO;


@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//register.jsp의 input name값을 가져옴
		String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    String passwordConfirm = request.getParameter("passwordConfirm");
	    String name = request.getParameter("name");
	    String nikname = request.getParameter("nikname");
	    
	    //membersDAO 객체 생성
	    membersDAO dao = new membersDAO();
	    
	    // id 중복 확인
	    if (dao.isIdExist(id)) {
	    	setRequestAttributes(request,id,password,passwordConfirm,name,nikname);
	        request.setAttribute("errorMessage", "이미 존재하는 아이디입니다.");
	        request.getRequestDispatcher("register.jsp").forward(request, response);
	        return;
	    }
	    
	    // 닉네임 중복 확인
        if (dao.isNiknameExist(nikname)) {
        	setRequestAttributes(request,id,password,passwordConfirm,name,nikname);
            request.setAttribute("errorMessage", "이미 존재하는 닉네임입니다.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
	    
	    //회원가입 유효성 검사
	    String errorMessage = regeisterInfo(id, password, passwordConfirm, nikname);

        //에러메세지가 들어오면 jsp에 전달
        if (errorMessage != null) {
        	//화면 초기화 방지
        	setRequestAttributes(request,id,password,passwordConfirm,name,nikname);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // 최종적으로 걸러지지 않은 값은 통과
        // 회원 정보 저장
        membersDTO user = new membersDTO();
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setNikname(nikname);

        dao.insertUser(user);  // 데이터베이스에 저장  
        //회원가입 성공시 sucess url 파라미터로 전달
        response.sendRedirect("login.jsp?message=success");
    }
	
	private String regeisterInfo(String id, String password, String passwordConfirm, String nikname) {
		//id, password 정규식 저장
		String idPattern = "^.+@.+$";
		String passwordPattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\\\":{}|<>]).+$";
		
		// id 이메일 형식 확인
        if (!Pattern.matches(idPattern, id)) {
            return "아이디는 이메일 형식이어야 합니다.";
        }

        // 비밀번호 검증
        if (!Pattern.matches(passwordPattern, password)) {
            return "비밀번호는 영어, 숫자, 특수문자가 모두 포함되어야 합니다.";
        }
        
        // 비밀번호 일치 확인
        if (!password.equals(passwordConfirm)) {
            return "비밀번호 확인이 일치하지 않습니다.";
        }
        
        return null;
    }
	
	//회원가입 실패 시 해당메소드 실행하여 화면 초기화 방지
	private void setRequestAttributes(HttpServletRequest request, String id, String password, String passwordConfirm, String name, String nikname) {
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("passwordConfirm", passwordConfirm);
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		request.setAttribute("nikname", nikname);
	}
}
