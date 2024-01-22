package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void insert(List<DishFlavor> flavors);

    /**
     * 根据id删除口味信息
     * @param id
     */
    @Delete("delete from dish_flavor where id = #{id}")
    void deleteById(Long id);
}
