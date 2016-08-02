package com.pacific.service.impl;

import com.pacific.domain.dto.MachineDto;
import com.pacific.domain.entity.Machine;
import com.pacific.domain.query.MachineQuery;
import com.pacific.domain.query.Pagination;
import com.pacific.mapper.MachineMapper;
import com.pacific.service.MachineService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by fe on 16/8/2.
 */
public class MachineServiceImpl implements MachineService {

    @Resource
    private MachineMapper machineMapper;

    public Pagination<MachineDto> queryMachineDto(MachineQuery machineQuery) {
        Assert.notNull(machineQuery);
        List<MachineDto> machineDtoList = machineMapper.queryMachineListByParam(machineQuery);
        int total = machineMapper.queryMachineCountByParam(machineQuery);
        return new Pagination<MachineDto>(machineQuery,machineDtoList,total);
    }


    public void saveMachine(Machine machine) {
        Assert.notNull(machine);
        Assert.notNull(machine.getIp());
        Assert.notNull(machine.getHostName());
        Assert.notNull(machine.getApplicationCode());

        int total = machineMapper.queryMachineByIpParam(machine.getIp(),machine.getApplicationCode());
        if (total > 0) throw new RuntimeException("同一个应用ip地址不能重复");

        total = machineMapper.queryMachineByHostNameParam(machine.getIp(),machine.getApplicationCode());

        if (total > 0) throw new RuntimeException("同一个应用hostName不能重复");

        if (machine.getId() == null) {
            machine.setCreateTime(new Date());
            machine.setUpdateTime(new Date());
            machineMapper.insert(machine);
        } else {
            Machine updateMachine = new Machine();
            updateMachine.setId(machine.getId());
            updateMachine.setIp(machine.getIp());
            updateMachine.setHostName(machine.getHostName());
            updateMachine.setUpdateTime(new Date());
            machineMapper.updateByPrimaryKeySelective(machine);
        }
    }
}
