package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import model.boardDAO;
import model.boardDTO;
import model.membersDTO;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/creatingPageCon.do")
public class creatingPageCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String createdUUID = null;

		try {
			// 요청을 보낼 URL 설정
			URL url = new URL("https://63a0-180-80-107-4.ngrok-free.app/music");  // 🔹 여기에 요청할 서버 주소 입력! 이 주소랑 연결할 거얌! 선언
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //url.openConnection()은 URL과 연결할 수 있는 객체를 반환
			// HTTP 요청을 보내야 하니까, HttpURLConnection으로 형변환
			// HTTP 메서드 및 헤더 설정
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");//컨 - 타 = 내가 보내는 데이터가 어떤 형식인지 알려주는 정보 //json타입
			conn.setRequestProperty("Accept", "application/json"); //"내가 받고 싶은 데이터 형식"을 서버에게 알려주는 HTTP 헤더
			conn.setDoOutput(true); //setDoOutput(true)는 "내가 POST 방식으로 본문(body)에 데이터를 보낼 거야!

			// JSON Body 데이터 설정
			String jsonInputString = "{ \"prompt\": \"" + request.getParameter("Text") + "\" }"; //사용자가 보낸 Textt파라미터를 json데이터로 변환 ex){ "prompt": "안녕" }

			// OutputStream을 이용해 JSON 데이터 전송
			try (OutputStream os = conn.getOutputStream()) { //OutputStream 데이터를 어디론가 보내주는 통로 그 뒤는 데이터를 보내는 Out어쩌구를 열어줌
				byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8); //Json을 byte배열로 변환하는 것 Out어쩌구는 문자열을 받을 수 없고 바이트 단위로만 받을 수 잇음
				os.write(input, 0, input.length); //write는 데이터를 바깥으로 보내는 메서드  0은 처음부터 보낼 거라는 뜻 input.length 첨부터 끝까지 전부 서버로 보내종
			}

			// 응답 본문을 responseBody StringBuilder로 만들어줌
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
			StringBuilder responseBody = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				responseBody.append(line);
			}
			in.close();

			//응답한 본문을 필요한 키 값으로 파싱해서 값을 추출함
			JSONObject json = new JSONObject(responseBody.toString());
			createdUUID= json.getString("uuid");
			System.out.println("🆔 UUID (JSONObject): " + createdUUID);

			// 응답 코드 확인
			int responseCode = conn.getResponseCode(); //conn은 내가 만든 서버객체  getResponseCode는 서버한테 응답코드를 물어보는 함수
			System.out.println("Response Code: " + responseCode);

			// 연결 종료
			conn.disconnect();

			//DTO 만들어서 게시글 등록하는 로직
			boardDTO dto = new boardDTO();
			boardDAO dao = new boardDAO();

			// 기존 세션 확인 (세션이 없으면 null 반환)
			HttpSession session = request.getSession(false);
			membersDTO member = (membersDTO) session.getAttribute("userbean");

			dto.setBoardName(request.getParameter("Text"));
			dto.setBoardWriteId(member.getUserNum());
			dto.setBoardWriteName(member.getNikname());
			dto.setViewCount(0);
			dto.setLikeCount(0);
			dto.setContentUUId(createdUUID);

			dao.insertBoard(dto);

			int createdId = dao.getBoardIdUseUUID(createdUUID);

			response.sendRedirect("detailPageCon.do?boardId=" + createdId + "&isCountView=false");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}