package fwq;

import fwq.model.Share;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 分享服务
 * 处理分享相关的业务逻辑
 */
@Service
public class ShareService {

    // 模拟数据库存储分享信息
    private final Map<String, Share> shareMap = new HashMap<>();
    // 模拟数据库存储视频分享数
    private final Map<Long, Integer> videoSharesCountMap = new HashMap<>();

    /**
     * 分享视频
     * @param videoId 视频ID
     * @param userId 用户ID
     * @param shareType 分享类型
     * @return 分享结果
     */
    public boolean shareVideo(Long videoId, Long userId, int shareType) {
        // 创建分享记录
        Share share = new Share();
        share.setVideoId(videoId);
        share.setUserId(userId);
        share.setShareType(shareType);
        share.setCreateTime(new Date());
        
        // 生成分享记录的key
        String shareKey = videoId + "_" + userId + "_" + System.currentTimeMillis();
        
        // 保存分享记录
        shareMap.put(shareKey, share);
        
        // 更新视频分享数
        int currentCount = videoSharesCountMap.getOrDefault(videoId, 0);
        videoSharesCountMap.put(videoId, currentCount + 1);
        
        return true;
    }

    /**
     * 获取视频分享数
     * @param videoId 视频ID
     * @return 分享数
     */
    public int getSharesCount(Long videoId) {
        return videoSharesCountMap.getOrDefault(videoId, 0);
    }
} 