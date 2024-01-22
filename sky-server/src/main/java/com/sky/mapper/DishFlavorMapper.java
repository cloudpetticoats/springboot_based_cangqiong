package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void insert(List<DishFlavor> flavors);

    /**
     * 根据id删除口味信息
     * @param id
     */
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id查询口味信息
     * @param id
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> getById(Long id);
}
