package fwq;

import fwq.model.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 评论服务
 * 处理评论相关的业务逻辑
 */
@Service
public class CommentService {

    // 模拟数据库存储评论信息
    private final Map<Long, Comment> commentMap = new HashMap<>();
    // 模拟数据库存储视频评论列表
    private final Map<Long, List<Long>> videoCommentsMap = new HashMap<>();
    // 模拟数据库存储评论回复列表
    private final Map<Long, List<Long>> commentRepliesMap = new HashMap<>();
    // 模拟数据库存储用户信息
    private final Map<Long, UserInfo> userInfoMap = new HashMap<>();
    
    // 评论ID生成器
    private Long commentIdGenerator = 1L;
    
    // 日期格式化
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 发表评论
     * @param request 评论请求
     * @return 评论结果
     */
    public CommentResponse comment(CommentRequest request) {
        // 生成评论ID
        Long commentId = commentIdGenerator++;
        
        // 创建评论记录
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setVideoId(request.getVideoId());
        comment.setUserId(request.getUserId());
        comment.setContent(request.getContent());
        comment.setParentId(request.getParentId() != null ? request.getParentId() : 0L);
        comment.setCreateTime(new Date());
        comment.setLikesCount(0);
        comment.setStatus(1);
        
        // 保存评论记录
        commentMap.put(commentId, comment);
        
        // 更新视频评论列表或评论回复列表
        if (comment.getParentId() == 0L) {
            // 一级评论，添加到视频评论列表
            List<Long> videoComments = videoCommentsMap.getOrDefault(comment.getVideoId(), new ArrayList<>());
            videoComments.add(commentId);
            videoCommentsMap.put(comment.getVideoId(), videoComments);
        } else {
            // 回复评论，添加到评论回复列表
            List<Long> commentReplies = commentRepliesMap.getOrDefault(comment.getParentId(), new ArrayList<>());
            commentReplies.add(commentId);
            commentRepliesMap.put(comment.getParentId(), commentReplies);
        }
        
        // 构建返回结果
        CommentResponse response = new CommentResponse();
        response.setCommentId(commentId);
        response.setUserInfo(getUserInfo(comment.getUserId()));
        response.setContent(comment.getContent());
        response.setCreateTime(dateFormat.format(comment.getCreateTime()));
        response.setLikesCount(comment.getLikesCount());
        response.setLiked(false);
        response.setRepliesCount(0);
        
        return response;
    }

    /**
     * 获取视频评论列表
     * @param videoId 视频ID
     * @param page 页码
     * @param size 每页数量
     * @return 评论列表
     */
    public CommentListResponse getVideoComments(Long videoId, int page, int size) {
        List<Long> commentIds = videoCommentsMap.getOrDefault(videoId, new ArrayList<>());
        return getCommentListResponse(commentIds, page, size);
    }

    /**
     * 获取评论回复列表
     * @param commentId 评论ID
     * @param page 页码
     * @param size 每页数量
     * @return 回复列表
     */
    public CommentListResponse getCommentReplies(Long commentId, int page, int size) {
        List<Long> replyIds = commentRepliesMap.getOrDefault(commentId, new ArrayList<>());
        return getCommentListResponse(replyIds, page, size);
    }

    /**
     * 删除评论
     * @param commentId 评论ID
     * @param userId 用户ID
     * @return 删除结果
     */
    public boolean deleteComment(Long commentId, Long userId) {
        Comment comment = commentMap.get(commentId);
        if (comment == null) {
            return false;
        }
        
        // 验证是否是评论作者
        if (!comment.getUserId().equals(userId)) {
            return false;
        }
        
        // 标记评论为删除状态
        comment.setStatus(0);
        commentMap.put(commentId, comment);
        
        return true;
    }
    
    /**
     * 构建评论列表响应
     * @param commentIds 评论ID列表
     * @param page 页码
     * @param size 每页数量
     * @return 评论列表响应
     */
    private CommentListResponse getCommentListResponse(List<Long> commentIds, int page, int size) {
        // 计算分页信息
        int total = commentIds.size();
        int totalPages = (total + size - 1) / size;
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, total);
        
        // 获取当前页的评论ID列表
        List<Long> pageCommentIds = fromIndex < total ? commentIds.subList(fromIndex, toIndex) : new ArrayList<>();
        
        // 构建评论信息列表
        List<CommentInfo> comments = new ArrayList<>();
        for (Long commentId : pageCommentIds) {
            Comment comment = commentMap.get(commentId);
            if (comment != null && comment.getStatus() == 1) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.setCommentId(comment.getId());
                commentInfo.setVideoId(comment.getVideoId());
                commentInfo.setUserInfo(getUserInfo(comment.getUserId()));
                commentInfo.setContent(comment.getContent());
                commentInfo.setCreateTime(dateFormat.format(comment.getCreateTime()));
                commentInfo.setLikesCount(comment.getLikesCount());
                commentInfo.setLiked(false); // 默认未点赞
                
                // 获取回复数量
                List<Long> replies = commentRepliesMap.getOrDefault(commentId, new ArrayList<>());
                commentInfo.setRepliesCount(replies.size());
                
                // 获取部分回复
                if (!replies.isEmpty()) {
                    List<CommentInfo> replyInfos = new ArrayList<>();
                    for (int i = 0; i < Math.min(2, replies.size()); i++) {
                        Comment replyComment = commentMap.get(replies.get(i));
                        if (replyComment != null && replyComment.getStatus() == 1) {
                            CommentInfo replyInfo = new CommentInfo();
                            replyInfo.setCommentId(replyComment.getId());
                            replyInfo.setVideoId(replyComment.getVideoId());
                            replyInfo.setUserInfo(getUserInfo(replyComment.getUserId()));
                            replyInfo.setContent(replyComment.getContent());
                            replyInfo.setCreateTime(dateFormat.format(replyComment.getCreateTime()));
                            replyInfo.setLikesCount(replyComment.getLikesCount());
                            replyInfo.setLiked(false);
                            replyInfo.setRepliesCount(0);
                            replyInfos.add(replyInfo);
                        }
                    }
                    commentInfo.setReplies(replyInfos);
                }
                
                comments.add(commentInfo);
            }
        }
        
        // 构建返回结果
        CommentListResponse response = new CommentListResponse();
        response.setComments(comments);
        response.setTotal(total);
        response.setCurrentPage(page);
        response.setTotalPages(totalPages);
        
        return response;
    }
    
    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    private UserInfo getUserInfo(Long userId) {
        // 从缓存中获取用户信息
        UserInfo userInfo = userInfoMap.get(userId);
        if (userInfo == null) {
            // 模拟从数据库获取用户信息
            userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setNickname("用户" + userId);
            userInfo.setAvatar("https://example.com/avatar" + userId + ".jpg");
            userInfo.setSignature("这是用户" + userId + "的个性签名");
            
            // 缓存用户信息
            userInfoMap.put(userId, userInfo);
        }
        return userInfo;
    }
} 