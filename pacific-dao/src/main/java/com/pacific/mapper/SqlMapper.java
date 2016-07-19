package com.pacific.mapper;

import com.pacific.domain.entity.Sql;

public interface SqlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sql record);

    int insertSelective(Sql record);

    Sql selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sql record);

    int updateByPrimaryKey(Sql record);
}