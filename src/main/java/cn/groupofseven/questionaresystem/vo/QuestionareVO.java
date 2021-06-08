package cn.groupofseven.questionaresystem.vo;

import cn.groupofseven.questionaresystem.po.Questionare;
import lombok.NonNull;

import java.util.Date;

public class QuestionareVO {
    private Integer id;

    private Integer createUserId;

    private String titleOfQn;

    private Boolean isDraft;

    private Integer numOfQuestions;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getTitleOfQn() {
        return titleOfQn;
    }

    public void setTitleOfQn(String titleOfQn) {
        this.titleOfQn = titleOfQn == null ? null : titleOfQn.trim();
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public Integer getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(Integer numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public QuestionareVO(){}
    public QuestionareVO(@NonNull Questionare questionare){
        id=questionare.getId();
        createUserId=questionare.getCreateUserId();
        titleOfQn=questionare.getTitleOfQn();
        isDraft=questionare.getIsDraft();
        numOfQuestions=questionare.getNumOfQuestions();
        createTime=questionare.getCreateTime();
    }

}