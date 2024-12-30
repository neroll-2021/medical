package com.neroll.mapper;

import com.neroll.pojo.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper {
    List<Material> findMaterialByPage(@Param("offset") Integer offset, @Param("count") Integer count, @Param("keyword") String keyword);

    Integer getMaterialCount();

    Integer addMaterial(Material material);

    Integer updateMaterial(Material material);

}
