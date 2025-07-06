package fwq;

import fwq.model.Like;
import fwq.model.LikeRequest;
import fwq.model.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 点赞服务
 * 处理点赞相关的业务逻辑
 */
@Service
public class LikeService {

    // 模拟数据库存储点赞信息
    private final Map<String, Like> likeMap = new HashMap<>();
    // 模拟数据库存储视频点赞数
    private final Map<Long, Integer> videoLikesCountMap = new HashMap<>();

    /**
     * 点赞/取消点赞
     * @param request 点赞请求
     * @return 点赞结果
     */
    public LikeResponse like(LikeRequest request) {
        Long videoId = request.getVideoId();
        Long userId = request.getUserId();
        
        // 生成点赞记录的key
        String likeKey = videoId + "_" + userId;
        
        // 检查是否已点赞
        boolean isLiked = likeMap.containsKey(likeKey) && likeMap.get(likeKey).getStatus() == 1;
        
        // 创建点赞记录
        Like like = new Like();
        like.setVideoId(videoId);
        like.setUserId(userId);
        like.setCreateTime(new Date());
        
        // 如果已点赞，则取消点赞；否则点赞
        if (isLiked) {
            like.setStatus(0); // 取消点赞
            // 更新视频点赞数
            int currentCount = videoLikesCountMap.getOrDefault(videoId, 0);
            videoLikesCountMap.put(videoId, Math.max(0, currentCount - 1));
        } else {
            like.setStatus(1); // 点赞
            // 更新视频点赞数
            int currentCount = videoLikesCountMap.getOrDefault(videoId, 0);
            videoLikesCountMap.put(videoId, currentCount + 1);
        }
        
        // 保存点赞记录
        likeMap.put(likeKey, like);
        
        // 构建返回结果
        LikeResponse response = new LikeResponse();
        response.setLiked(!isLiked); // 返回最新的点赞状态
        response.setLikesCount(videoLikesCountMap.getOrDefault(videoId, 0));
        
        return response;
    }

    /**
     * 检查是否已点赞
     * @param videoId 视频ID
     * @param userId 用户ID
     * @return 是否已点赞
     */
    public boolean checkLike(Long videoId, Long userId) {
        String likeKey = videoId + "_" + userId;
        return likeMap.containsKey(likeKey) && likeMap.get(likeKey).getStatus() == 1;
    }

    /**
     * 获取视频点赞数
     * @param videoId 视频ID
     * @return 点赞数
     */
    public int getLikesCount(Long videoId) {
        return videoLikesCountMap.getOrDefault(videoId, 0);
    }
} 