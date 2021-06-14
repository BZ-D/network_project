package cn.groupofseven.questionaresystem.datamapper;

import cn.groupofseven.questionaresystem.po.Question;
import cn.groupofseven.questionaresystem.po.Questionare;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface QuestionareMapper {
    int deleteByPrimaryKey(Integer id);

    Question selectByQName(String name);

    int insert(Questionare record);

    int insertSelective(Questionare record);

    Questionare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questionare record);

    int updateByPrimaryKey(Questionare record);

    //按照问卷标题（title）来选择questionare，在xml中我已经实现了
    List<Questionare> selectByTitle(String title);

    //按照用户id来选择questionare，在xml中我已经实现了
    List<Questionare> selectByUid(Integer uid);

    //根据用户id来选择草稿问卷，已实现
    ArrayList<Questionare> selectDraftByUserId(Integer uid);
}