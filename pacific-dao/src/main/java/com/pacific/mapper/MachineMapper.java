package com.pacific.mapper;

import com.pacific.domain.dto.MachineDto;
import com.pacific.domain.entity.Machine;
import com.pacific.domain.query.MachineQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);

    List<Machine> selectAllMachineByApplicationCode(String applicationCode);

    List<MachineDto> queryMachineListByParam(MachineQuery machineQuery);

    int queryMachineCountByParam(MachineQuery machineQuery);

    int queryMachineByIpParam(@Param("ip") String ip,
                              @Param("applicationCode") String applicationCode);

    int queryMachineByHostNameParam(@Param("hostName") String hostName,
                                    @Param("applicationCode") String applicationCode);
}