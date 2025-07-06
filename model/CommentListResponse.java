package fwq.model;

import java.util.List;

/**
 * 评论列表响应
 */
public class CommentListResponse {
    private List<CommentInfo> comments; // 评论列表
    private int total; // 评论总数
    private int currentPage; // 当前页码
    private int totalPages; // 总页数

    public List<CommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<CommentInfo> comments) {
        this.comments = comments;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
} 