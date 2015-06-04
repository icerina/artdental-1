package com.honeycombee.web.artdental.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gizmo4u on 15. 6. 1..
 */
public class TestBoard {

    @Id
    public String id;

    @NotEmpty(message = "Please enter title.")
    public String title;

    @NotEmpty(message = "Please enter content.")
    public String content;

    public boolean checked = false;

    public Date createDate;
    public Date updateDate;

    public Member writer;
    public List<TestBoardComment> comments;

    public TestBoard() {
        this.comments = new ArrayList<TestBoardComment>();
    }

    public TestBoard(String title, String content, boolean checked, Member writer, Date createDate, Date updateDate) {
        this.title = title;
        this.content = content;
        this.checked = checked;
        this.writer = writer;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.comments = new ArrayList<TestBoardComment>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<TestBoardComment> getComments() {
        return comments;
    }

    public void setComments(List<TestBoardComment> comments) {
        this.comments = comments;
    }

    public boolean hasId(){
        if(this.id == null || this.id.isEmpty()) return false;
        return true;
    }

    public void removeCommentById(String id){
        for(TestBoardComment comment : this.comments){
            if(comment.getId().equals(id)){
                this.comments.remove(comment);
                break;
            }
        }
    }
}
