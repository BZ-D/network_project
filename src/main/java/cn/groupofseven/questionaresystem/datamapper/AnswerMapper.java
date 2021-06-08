package cn.groupofseven.questionaresystem.datamapper;

import cn.groupofseven.questionaresystem.po.Answer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}