package fwq.model;

/**
 * 点赞响应
 */
public class LikeResponse {
    private boolean liked; // 当前点赞状态：true-已点赞，false-未点赞
    private int likesCount; // 点赞数量

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
} 