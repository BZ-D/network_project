package cn.groupofseven.questionaresystem.po;

import cn.groupofseven.questionaresystem.vo.QuestionVO;
import lombok.NonNull;

import java.util.Date;

public class Question {
    private Integer id;

    private Integer qnId;

    private String questionTitle;

    private String type;

    private Boolean isMust;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String optionE;

    private String optionF;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQnId() {
        return qnId;
    }

    public void setQnId(Integer qnId) {
        this.qnId = qnId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle == null ? null : questionTitle.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Boolean getIsMust() {
        return isMust;
    }

    public void setIsMust(Boolean isMust) {
        this.isMust = isMust;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA == null ? null : optionA.trim();
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB == null ? null : optionB.trim();
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC == null ? null : optionC.trim();
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD == null ? null : optionD.trim();
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE == null ? null : optionE.trim();
    }

    public String getOptionF() {
        return optionF;
    }

    public void setOptionF(String optionF) {
        this.optionF = optionF == null ? null : optionF.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Question(){
    }
    public Question(@NonNull QuestionVO questionVO){
        id = questionVO.getId();
        qnId=questionVO.getQnId();
        questionTitle=questionVO.getQuestionTitle();
        type=questionVO.getType();
        isMust=questionVO.getIsMust();
        optionA=questionVO.getOptionA();
        optionB=questionVO.getOptionB();
        optionC=questionVO.getOptionC();
        optionD=questionVO.getOptionD();
        optionE=questionVO.getOptionE();
        optionF=questionVO.getOptionF();
        createTime=questionVO.getCreateTime();
    }
}