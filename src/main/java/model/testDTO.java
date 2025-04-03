package model;

public class testDTO {
    private String Id;          // 회원 고유 ID
    private String password;  // 비밀번호
    private String name;      // 회원 이름
    private String nikname;   // 닉네임 (UNIQUE)
    
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
