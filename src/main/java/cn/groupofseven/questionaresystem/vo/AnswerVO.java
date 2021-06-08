package cn.groupofseven.questionaresystem.vo;

import cn.groupofseven.questionaresystem.po.Answer;
import lombok.NonNull;

import java.util.Date;

public class AnswerVO {
    private Integer id;

    private Integer answerUserId;

    private Integer answerQnId;

    private Integer questionId;

    private String gapAnswer;

    private Integer singleAnswer;

    private Boolean multiOne;

    private Boolean multiTwo;

    private Boolean multiThree;

    private Boolean multiFour;

    private Boolean multiFive;

    private Boolean multiSix;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(Integer answerUserId) {
        this.answerUserId = answerUserId;
    }

    public Integer getAnswerQnId() {
        return answerQnId;
    }

    public void setAnswerQnId(Integer answerQnId) {
        this.answerQnId = answerQnId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getGapAnswer() {
        return gapAnswer;
    }

    public void setGapAnswer(String gapAnswer) {
        this.gapAnswer = gapAnswer == null ? null : gapAnswer.trim();
    }

    public Integer getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(Integer singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public Boolean getMultiOne() {
        return multiOne;
    }

    public void setMultiOne(Boolean multiOne) {
        this.multiOne = multiOne;
    }

    public Boolean getMultiTwo() {
        return multiTwo;
    }

    public void setMultiTwo(Boolean multiTwo) {
        this.multiTwo = multiTwo;
    }

    public Boolean getMultiThree() {
        return multiThree;
    }

    public void setMultiThree(Boolean multiThree) {
        this.multiThree = multiThree;
    }

    public Boolean getMultiFour() {
        return multiFour;
    }

    public void setMultiFour(Boolean multiFour) {
        this.multiFour = multiFour;
    }

    public Boolean getMultiFive() {
        return multiFive;
    }

    public void setMultiFive(Boolean multiFive) {
        this.multiFive = multiFive;
    }

    public Boolean getMultiSix() {
        return multiSix;
    }

    public void setMultiSix(Boolean multiSix) {
        this.multiSix = multiSix;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public AnswerVO(){
    }
    public AnswerVO(@NonNull Answer answer){
        id=answer.getId();
        answerUserId=answer.getAnswerUserId();
        answerQnId=answer.getAnswerQnId();
        questionId=answer.getQuestionId();
        gapAnswer=answer.getGapAnswer();
        singleAnswer=answer.getSingleAnswer();
        multiOne=answer.getMultiOne();
        multiTwo=answer.getMultiTwo();
        multiThree=answer.getMultiThree();
        multiFour=answer.getMultiFour();
        multiFive=answer.getMultiFive();
        multiSix=answer.getMultiSix();
        createTime=answer.getCreateTime();
    }

}