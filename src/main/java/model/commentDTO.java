package model;

import java.util.Date;

public class commentDTO {
    private int commentId;
    private int userId;
    private String userName;
    private int refBoardId;
    private String comment;
    private Date commentDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRefBoardId() {
        return refBoardId;
    }

    public void setRefBoardId(int refBoardId) {
        this.refBoardId = refBoardId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}