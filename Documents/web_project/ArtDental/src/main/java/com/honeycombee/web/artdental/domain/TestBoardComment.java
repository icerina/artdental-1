package com.honeycombee.web.artdental.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by gizmo4u on 15. 6. 1..
 */
public class TestBoardComment {

    @Id
    public String id;

    public String commentContent;
    public Member commenter;

    public Date createDate;

    public TestBoardComment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Member getCommenter() {
        return commenter;
    }

    public void setCommenter(Member commenter) {
        this.commenter = commenter;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
