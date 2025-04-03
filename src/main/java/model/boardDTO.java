package model;

public class boardDTO {
    private int boardId;
    private String boardName;
    private int boardWriteId;
    private String boardWriteName;
    private int viewCount;
    private int likeCount;
    private String contentUUId;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getBoardWriteId() {
        return boardWriteId;
    }

    public void setBoardWriteId(int boardWriteId) {
        this.boardWriteId = boardWriteId;
    }

    public String getBoardWriteName() {
        return boardWriteName;
    }

    public void setBoardWriteName(String boardWriteName) {
        this.boardWriteName = boardWriteName;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getContentUUId() {
        return contentUUId;
    }

    public void setContentUUId(String contentUUId) {
        this.contentUUId = contentUUId;
    }
}
