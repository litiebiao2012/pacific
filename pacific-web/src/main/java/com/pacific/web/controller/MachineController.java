package com.pacific.web.controller;

import com.pacific.common.exception.ServiceException;
import com.pacific.common.web.result.AjaxResult;
import com.pacific.domain.entity.Application;
import com.pacific.domain.entity.Machine;
import com.pacific.domain.enums.StateEnums;
import com.pacific.domain.query.MachineQuery;
import com.pacific.mapper.MachineMapper;
import com.pacific.service.ApplicationService;
import com.pacific.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fe on 16/8/1.
 */
@Controller
@RequestMapping("/machine")
public class MachineController {

    @Resource
    private MachineMapper machineMapper;

    @Resource
    private MachineService machineService;

    @Resource
    private ApplicationService applicationService;

    @RequestMapping("/machineList.htm")
    public String tomMachineList() {
        return "machine/machineList";
    }

    @RequestMapping("/toMachineEdit.htm")
    public ModelAndView toMachineEdit(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("machine/machineEdit");
        if (id != null) {
            Machine machine = machineMapper.selectByPrimaryKey(id);
            modelAndView.addObject("id",id);
            modelAndView.addObject("machine",machine);
        } else {
            List<Application> applicationList = applicationService.queryApplicationByState(StateEnums.AVAILABLE.getCode());
            modelAndView.addObject("applicationList",applicationList);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/machineList.json")
    public AjaxResult machineList(MachineQuery machineQuery) {
        AjaxResult ajaxResult = new AjaxResult(machineService.queryMachineDto(machineQuery));
        return ajaxResult;

    }

    @ResponseBody
    @RequestMapping("/saveMachine.json")
    public AjaxResult saveMachine(Machine machine) {
        machineService.saveMachine(machine);
        return new AjaxResult();

    }
}
