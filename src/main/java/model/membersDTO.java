package model;

public class membersDTO {
    private int userNum;		// 유저 고유 넘버 pk
	private String Id;          // 회원 고유 id(이메일 형식)
    private String password;  // 비밀번호
    private String name;      // 회원 이름
    private String nikname;   // 닉네임 (UNIQUE)

	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNikname() {
		return nikname;
	}
	public void setNikname(String nikname) {
		this.nikname = nikname;
	}

}
