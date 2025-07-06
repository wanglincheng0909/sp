package fwq;

import fwq.model.ApiResponse;
import fwq.model.LikeRequest;
import fwq.model.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞控制器
 * 处理点赞相关的请求
 */
@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * 点赞/取消点赞
     * @param request 点赞请求
     * @return 点赞结果
     */
    @PostMapping
    public ApiResponse<LikeResponse> like(@RequestBody LikeRequest request) {
        LikeResponse response = likeService.like(request);
        return ApiResponse.success(response);
    }

    /**
     * 检查是否已点赞
     * @param videoId 视频ID
     * @param userId 用户ID
     * @return 是否已点赞
     */
    @GetMapping("/check")
    public ApiResponse<Boolean> checkLike(
            @RequestParam Long videoId,
            @RequestParam Long userId) {
        boolean liked = likeService.checkLike(videoId, userId);
        return ApiResponse.success(liked);
    }

    /**
     * 获取视频点赞数
     * @param videoId 视频ID
     * @return 点赞数
     */
    @GetMapping("/count")
    public ApiResponse<Integer> getLikesCount(@RequestParam Long videoId) {
        int count = likeService.getLikesCount(videoId);
        return ApiResponse.success(count);
    }
} 