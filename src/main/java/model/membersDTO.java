package model;

public class membersDTO {
    private String Id;          // 회원 고유 ID
    private String password;  // 비밀번호
    private String name;      // 회원 이름
    private String nikname;   // 닉네임 (UNIQUE)
    private int postID;	//게시글 각각의 id
    private String postSubject; //게시글 이름.
    private int viewcount;
    private int likecount;
    
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
	
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	public String getPostSubject() {
		return postSubject;
	}
	
	public void setPostSubject(String postSubject) {
		this.postSubject = postSubject;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	
	
}
