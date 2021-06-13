package cn.groupofseven.questionaresystem.datamapper;

import cn.groupofseven.questionaresystem.po.Question;
import cn.groupofseven.questionaresystem.po.Questionare;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionareMapper {
    int deleteByPrimaryKey(Integer id);
    Question selectByQName(String name);

    int insert(Questionare record);

    int insertSelective(Questionare record);

    Questionare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questionare record);

    int updateByPrimaryKey(Questionare record);

    ArrayList<Questionare> selectDraftByUserId(Integer uid);
}