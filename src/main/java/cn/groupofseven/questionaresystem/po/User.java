package cn.groupofseven.questionaresystem.po;

import cn.groupofseven.questionaresystem.vo.UserVO;
import lombok.NonNull;

import java.util.Date;

public class User {
    private Integer id;

    private String acNumber;

    private String uname;

    private Integer numOfFilling;

    private Integer numOfRelease;

    private Integer numOfDraft;

    private String password;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcNumber() {
        return acNumber;
    }

    public void setAcNumber(String acNumber) {
        this.acNumber = acNumber == null ? null : acNumber.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public Integer getNumOfFilling() {
        return numOfFilling;
    }

    public void setNumOfFilling(Integer numOfFilling) {
        this.numOfFilling = numOfFilling;
    }

    public Integer getNumOfRelease() {
        return numOfRelease;
    }

    public void setNumOfRelease(Integer numOfRelease) {
        this.numOfRelease = numOfRelease;
    }

    public Integer getNumOfDraft() {
        return numOfDraft;
    }

    public void setNumOfDraft(Integer numOfDraft) {
        this.numOfDraft = numOfDraft;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public User(){
    }
    public User(@NonNull UserVO userVO){
        id=userVO.getId();
        acNumber=userVO.getAcNumber();
        uname=userVO.getUname();
        numOfFilling=userVO.getNumOfFilling();
        numOfRelease=userVO.getNumOfRelease();
        numOfDraft=userVO.getNumOfDraft();
        password=userVO.getPassword();
        createTime=userVO.getCreateTime();
    }
}