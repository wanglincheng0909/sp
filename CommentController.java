package fwq;

import fwq.model.ApiResponse;
import fwq.model.CommentInfo;
import fwq.model.CommentListResponse;
import fwq.model.CommentRequest;
import fwq.model.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论控制器
 * 处理评论相关的请求
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发表评论
     * @param request 评论请求
     * @return 评论结果
     */
    @PostMapping
    public ApiResponse<CommentResponse> comment(@RequestBody CommentRequest request) {
        CommentResponse response = commentService.comment(request);
        return ApiResponse.success(response);
    }

    /**
     * 获取视频评论列表
     * @param videoId 视频ID
     * @param page 页码
     * @param size 每页数量
     * @return 评论列表
     */
    @GetMapping("/video/{videoId}")
    public ApiResponse<CommentListResponse> getVideoComments(
            @PathVariable Long videoId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        CommentListResponse response = commentService.getVideoComments(videoId, page, size);
        return ApiResponse.success(response);
    }

    /**
     * 获取评论回复列表
     * @param commentId 评论ID
     * @param page 页码
     * @param size 每页数量
     * @return 回复列表
     */
    @GetMapping("/{commentId}/replies")
    public ApiResponse<CommentListResponse> getCommentReplies(
            @PathVariable Long commentId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        CommentListResponse response = commentService.getCommentReplies(commentId, page, size);
        return ApiResponse.success(response);
    }

    /**
     * 删除评论
     * @param commentId 评论ID
     * @param userId 用户ID（用于验证是否是评论作者）
     * @return 删除结果
     */
    @DeleteMapping("/{commentId}")
    public ApiResponse<Boolean> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userId) {
        boolean result = commentService.deleteComment(commentId, userId);
        return ApiResponse.success(result);
    }
} 