package com.pacific.service;

import com.pacific.domain.dto.MachineDto;
import com.pacific.domain.entity.Machine;
import com.pacific.domain.query.MachineQuery;
import com.pacific.domain.query.Pagination;

/**
 * Created by fe on 16/8/2.
 */
public interface MachineService {

    public Pagination<MachineDto> queryMachineDto(MachineQuery machineQuery);

    public void saveMachine(Machine machine);
}
