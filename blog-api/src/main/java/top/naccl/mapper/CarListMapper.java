package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.naccl.entity.CarList;

import java.util.List;

@Mapper
@Repository
public interface CarListMapper {
    List<CarList> getAll();
}
