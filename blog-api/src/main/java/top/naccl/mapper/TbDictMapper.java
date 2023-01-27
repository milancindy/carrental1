package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.naccl.entity.manager.pojo.TbDict;
import top.naccl.entity.manager.pojo.TbDictExample;

import java.util.List;

@Mapper
@Repository
public interface TbDictMapper {
    long countByExample(TbDictExample example);

    int deleteByExample(TbDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbDict record);

    int insertSelective(TbDict record);

    List<TbDict> selectByExample(TbDictExample example);

    TbDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbDict record, @Param("example") TbDictExample example);

    int updateByExample(@Param("record") TbDict record, @Param("example") TbDictExample example);

    int updateByPrimaryKeySelective(TbDict record);

    int updateByPrimaryKey(TbDict record);
}