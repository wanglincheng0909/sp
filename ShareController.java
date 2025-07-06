package fwq;

import fwq.model.ApiResponse;
import fwq.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分享控制器
 * 处理分享相关的请求
 */
@RestController
@RequestMapping("/api/shares")
public class ShareController {

    @Autowired
    private ShareService shareService;

    /**
     * 分享视频
     * @param videoId 视频ID
     * @param userId 用户ID
     * @param shareType 分享类型：1-微信，2-朋友圈，3-QQ，4-微博等
     * @return 分享结果
     */
    @PostMapping
    public ApiResponse<Boolean> shareVideo(
            @RequestParam Long videoId,
            @RequestParam Long userId,
            @RequestParam int shareType) {
        boolean result = shareService.shareVideo(videoId, userId, shareType);
        return ApiResponse.success(result);
    }

    /**
     * 获取视频分享数
     * @param videoId 视频ID
     * @return 分享数
     */
    @GetMapping("/count")
    public ApiResponse<Integer> getSharesCount(@RequestParam Long videoId) {
        int count = shareService.getSharesCount(videoId);
        return ApiResponse.success(count);
    }
} 