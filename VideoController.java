package fwq;

import fwq.model.ApiResponse;
import fwq.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视频控制器
 * 处理视频相关的请求
 */
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 获取视频列表
     * @param page 页码
     * @param size 每页数量
     * @return 视频列表
     */
    @GetMapping
    public ApiResponse<List<Video>> getVideoList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Video> videos = videoService.getVideoList(page, size);
        return ApiResponse.success(videos);
    }

    /**
     * 获取视频详情
     * @param id 视频ID
     * @return 视频详情
     */
    @GetMapping("/{id}")
    public ApiResponse<Video> getVideoById(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        return ApiResponse.success(video);
    }

    /**
     * 获取推荐视频列表
     * @param userId 用户ID
     * @param size 数量
     * @return 推荐视频列表
     */
    @GetMapping("/recommend")
    public ApiResponse<List<Video>> getRecommendVideos(
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "10") int size) {
        List<Video> videos = videoService.getRecommendVideos(userId, size);
        return ApiResponse.success(videos);
    }

    /**
     * 获取用户发布的视频列表
     * @param userId 用户ID
     * @param page 页码
     * @param size 每页数量
     * @return 用户视频列表
     */
    @GetMapping("/user/{userId}")
    public ApiResponse<List<Video>> getUserVideos(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Video> videos = videoService.getUserVideos(userId, page, size);
        return ApiResponse.success(videos);
    }
} 