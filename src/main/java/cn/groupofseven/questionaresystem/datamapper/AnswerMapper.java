package cn.groupofseven.questionaresystem.datamapper;

import cn.groupofseven.questionaresystem.po.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    //按照uid来查answer，已实现
    ArrayList<Answer> selectByUid(Integer uid);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}