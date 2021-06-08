package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.User;
import cn.groupofseven.questionaresystem.sevice.UserService;
import cn.groupofseven.questionaresystem.util.Constant;
import cn.groupofseven.questionaresystem.vo.ResultVO;
import cn.groupofseven.questionaresystem.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public ResultVO<UserVO> userSignUp(UserVO user) {
        String acnumber=user.getAcNumber();
        String uname=user.getUname();
        String password=user.getPassword();
        if(userMapper.selectByAcnumber(acnumber)==null){
            if(StringUtils.hasText(uname)&&StringUtils.hasText(password)){
                user.setNumOfFilling(0);
                user.setNumOfDraft(0);
                user.setNumOfRelease(0);
                user.setCreateTime(new Date());
                User u=new User(user);
                userMapper.insert(u);
                return new ResultVO<>(Constant.REQUEST_SUCCESS,"注册成功",new UserVO(u));
            }else {
                return new ResultVO<>(Constant.REQUEST_SUCCESS,"注册失败，用户名或密码未设定");
            }
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"注册失败，此账号已经被注册");
    }

    @Override
    public ResultVO<UserVO> userLogin(String acnumber, String password) {
        User user = userMapper.selectByAcnumber(acnumber);
        if(user == null){
            return new ResultVO<>(Constant.REQUEST_FAIL, "该账号不存在");
        }else{
            if(!user.getPassword().equals(password))
                return new ResultVO<>(Constant.REQUEST_FAIL, "账号或密码错误！");
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "账号登陆成功！", new UserVO(userMapper.selectByAcnumber(acnumber)));
    }

    @Override
    public UserVO getUser(Integer uid) {
        User userFromDB = userMapper.selectByPrimaryKey(uid);
        if(userFromDB == null){
            return new UserVO();
        }else{
            return new UserVO(userFromDB);
        }
    }

    @Override
    public void increaseFilling(Integer id) {
        userMapper.increaseFilling(id);
    }

    @Override
    public void increaseRelease(Integer id) {
        userMapper.increaseRelease(id);
    }

    @Override
    public void increaseDraft(Integer id) {
        userMapper.increaseDraft(id);
    }

    @Override
    public void decreaseDraft(Integer id) {
        userMapper.decreaseDraft(id);
    }
}
