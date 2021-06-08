package cn.groupofseven.questionaresystem.sevice;

import cn.groupofseven.questionaresystem.vo.ResultVO;
import cn.groupofseven.questionaresystem.vo.UserVO;

public interface UserService {
    ResultVO<UserVO> userSignUp(UserVO user);
    ResultVO<UserVO> userLogin(String phone, String password);
    UserVO getUser(Integer uid);
    void increaseFilling(Integer id);
    void increaseRelease(Integer id);
    void increaseDraft(Integer id);
    void decreaseDraft(Integer id);
}
