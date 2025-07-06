package fwq;

import fwq.model.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 视频服务
 * 处理视频相关的业务逻辑
 */
@Service
public class VideoService {

    /**
     * 获取视频列表
     * @param page 页码
     * @param size 每页数量
     * @return 视频列表
     */
    public List<Video> getVideoList(int page, int size) {
        // 这里应该是从数据库中查询视频列表
        // 这里简单模拟返回数据
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Video video = new Video();
            video.setId((long) ((page - 1) * size + i + 1));
            video.setTitle("视频标题" + ((page - 1) * size + i + 1));
            video.setUrl("https://raw.githubusercontent.com/wanglincheng0909/sp/main/sp" + ((i % 5) + 1));
            video.setCoverUrl("https://example.com/cover" + ((page - 1) * size + i + 1) + ".jpg");
            video.setDescription("视频描述" + ((page - 1) * size + i + 1));
            video.setAuthorId(1L);
            video.setCreateTime(new Date());
            video.setUpdateTime(new Date());
            video.setLikesCount(100 + i);
            video.setCommentsCount(50 + i);
            video.setSharesCount(30 + i);
            video.setStatus(1);
            videos.add(video);
        }
        return videos;
    }

    /**
     * 获取视频详情
     * @param id 视频ID
     * @return 视频详情
     */
    public Video getVideoById(Long id) {
        // 这里应该是从数据库中查询视频详情
        // 这里简单模拟返回数据
        Video video = new Video();
        video.setId(id);
        video.setTitle("视频标题" + id);
        video.setUrl("https://raw.githubusercontent.com/wanglincheng0909/sp/main/sp" + (id.intValue() % 5 + 1));
        video.setCoverUrl("https://example.com/cover" + id + ".jpg");
        video.setDescription("视频描述" + id);
        video.setAuthorId(1L);
        video.setCreateTime(new Date());
        video.setUpdateTime(new Date());
        video.setLikesCount(100);
        video.setCommentsCount(50);
        video.setSharesCount(30);
        video.setStatus(1);
        return video;
    }

    /**
     * 获取推荐视频列表
     * @param userId 用户ID
     * @param size 数量
     * @return 推荐视频列表
     */
    public List<Video> getRecommendVideos(Long userId, int size) {
        // 这里应该是根据用户ID推荐视频
        // 这里简单模拟返回数据
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Video video = new Video();
            video.setId((long) (i + 1));
            video.setTitle("推荐视频标题" + (i + 1));
            video.setUrl("https://raw.githubusercontent.com/wanglincheng0909/sp/main/sp" + ((i % 5) + 1));
            video.setCoverUrl("https://example.com/cover" + (i + 1) + ".jpg");
            video.setDescription("推荐视频描述" + (i + 1));
            video.setAuthorId(1L);
            video.setCreateTime(new Date());
            video.setUpdateTime(new Date());
            video.setLikesCount(200 + i);
            video.setCommentsCount(100 + i);
            video.setSharesCount(50 + i);
            video.setStatus(1);
            videos.add(video);
        }
        return videos;
    }

    /**
     * 获取用户发布的视频列表
     * @param userId 用户ID
     * @param page 页码
     * @param size 每页数量
     * @return 用户视频列表
     */
    public List<Video> getUserVideos(Long userId, int page, int size) {
        // 这里应该是从数据库中查询用户发布的视频列表
        // 这里简单模拟返回数据
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Video video = new Video();
            video.setId((long) ((page - 1) * size + i + 1));
            video.setTitle("用户视频标题" + ((page - 1) * size + i + 1));
            video.setUrl("https://raw.githubusercontent.com/wanglincheng0909/sp/main/sp" + ((i % 5) + 1));
            video.setCoverUrl("https://example.com/cover" + ((page - 1) * size + i + 1) + ".jpg");
            video.setDescription("用户视频描述" + ((page - 1) * size + i + 1));
            video.setAuthorId(userId);
            video.setCreateTime(new Date());
            video.setUpdateTime(new Date());
            video.setLikesCount(50 + i);
            video.setCommentsCount(20 + i);
            video.setSharesCount(10 + i);
            video.setStatus(1);
            videos.add(video);
        }
        return videos;
    }
} 