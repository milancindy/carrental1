package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.naccl.entity.manager.pojo.TbBase;
import top.naccl.entity.manager.pojo.TbBaseExample;

import java.util.List;

@Mapper
@Repository
public interface TbBaseMapper {
    long countByExample(TbBaseExample example);

    int deleteByExample(TbBaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBase record);

    int insertSelective(TbBase record);

    List<TbBase> selectByExample(TbBaseExample example);

    TbBase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBase record, @Param("example") TbBaseExample example);

    int updateByExample(@Param("record") TbBase record, @Param("example") TbBaseExample example);

    int updateByPrimaryKeySelective(TbBase record);

    int updateByPrimaryKey(TbBase record);
}