package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.naccl.entity.manager.pojo.TbPanelContent;
import top.naccl.entity.manager.pojo.TbPanelContentExample;

import java.util.List;

@Mapper
@Repository
public interface TbPanelContentMapper {
    long countByExample(TbPanelContentExample example);

    int deleteByExample(TbPanelContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbPanelContent record);

    int insertSelective(TbPanelContent record);

    List<TbPanelContent> selectByExample(TbPanelContentExample example);

    TbPanelContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbPanelContent record, @Param("example") TbPanelContentExample example);

    int updateByExample(@Param("record") TbPanelContent record, @Param("example") TbPanelContentExample example);

    int updateByPrimaryKeySelective(TbPanelContent record);

    int updateByPrimaryKey(TbPanelContent record);
}