package com.pacific.mapper;

import com.pacific.domain.entity.Machine;

import java.util.List;

public interface MachineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);

    List<Machine> selectAllMachineByApplicationCode(String applicationCode);
}