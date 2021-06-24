package cn.groupofseven.questionaresystem.controller;

import cn.groupofseven.questionaresystem.sevice.UserService;
import cn.groupofseven.questionaresystem.vo.ResultVO;
import cn.groupofseven.questionaresystem.vo.UserFormVO;
import cn.groupofseven.questionaresystem.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/signup")
    public ResultVO<UserVO> signup(@RequestBody UserVO user){
        return userService.userSignUp(user);
    }
    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserFormVO userForm){
        return userService.userLogin(userForm.getAcNumber(),userForm.getPassword());
    }
    //api中暂未给出方法，但需要该方法传递用户所有信息
    @GetMapping("/{uid}")
    public UserVO getUser(@PathVariable Integer uid){
        System.out.println("hee");
        return userService.getUser(uid);
    }
}
