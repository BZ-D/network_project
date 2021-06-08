package cn.groupofseven.questionaresystem.datamapper;

import cn.groupofseven.questionaresystem.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByAcnumber(String acNumber);

    int increaseFilling(Integer id);
    int increaseRelease(Integer id);
    int increaseDraft(Integer id);
    int decreaseDraft(Integer id);
}