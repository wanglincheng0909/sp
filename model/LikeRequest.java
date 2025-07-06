package fwq.model;

/**
 * 点赞请求
 */
public class LikeRequest {
    private Long videoId;
    private Long userId;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
} 