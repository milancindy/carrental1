package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.naccl.entity.manager.pojo.TbRolePerm;
import top.naccl.entity.manager.pojo.TbRolePermExample;

import java.util.List;

@Mapper
@Repository
public interface TbRolePermMapper {
    long countByExample(TbRolePermExample example);

    int deleteByExample(TbRolePermExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRolePerm record);

    int insertSelective(TbRolePerm record);

    List<TbRolePerm> selectByExample(TbRolePermExample example);

    TbRolePerm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRolePerm record, @Param("example") TbRolePermExample example);

    int updateByExample(@Param("record") TbRolePerm record, @Param("example") TbRolePermExample example);

    int updateByPrimaryKeySelective(TbRolePerm record);

    int updateByPrimaryKey(TbRolePerm record);
}