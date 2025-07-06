package fwq.model;

import java.util.List;

/**
 * 评论信息
 */
public class CommentInfo {
    private Long commentId; // 评论ID
    private Long videoId; // 视频ID
    private UserInfo userInfo; // 评论用户信息
    private String content; // 评论内容
    private String createTime; // 评论时间
    private int likesCount; // 点赞数量
    private boolean liked; // 当前用户是否点赞
    private int repliesCount; // 回复数量
    private List<CommentInfo> replies; // 回复列表（部分）

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }

    public List<CommentInfo> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentInfo> replies) {
        this.replies = replies;
    }
} 