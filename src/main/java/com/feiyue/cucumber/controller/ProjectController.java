package com.feiyue.cucumber.controller;

import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.service.ProjectService;
import com.feiyue.cucumber.util.BusinessException;
import com.feiyue.cucumber.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jisongZhou on 2019/8/15.
 **/
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/insert")
    @ResponseBody
    public InvokeResult insert(@RequestBody Project entity) throws Throwable {
        int result = projectService.insert(entity);
        return InvokeResult.writeResult(result, "20100", "10003", "10002");
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public InvokeResult update(@RequestBody Project entity) throws Throwable {
        boolean flag1 = projectService.permission(entity);
        boolean flag2 = projectService.isLocked(entity);
        if (!flag1) {
            throw new BusinessException("20111");
        }
        if (flag2) {
            throw new BusinessException("20112");
        }
        int result = projectService.update(entity);
        return InvokeResult.writeResult(result, "20110", "10003", "10002");
    }

    @PostMapping(value = "/lock")
    @ResponseBody
    public InvokeResult lock(@RequestBody Project entity) throws Throwable {
        boolean flag = projectService.permission(entity);
        if (!flag) {
            throw new BusinessException("20131");
        }
        int result = projectService.update(entity);
        return InvokeResult.writeResult(result, "20130", "10003", "10002");
    }

    @GetMapping(value = "/permission")
    @ResponseBody
    public InvokeResult permission(@RequestBody Project entity) throws Throwable {
        boolean result = projectService.permission(entity);
        return InvokeResult.readResult(result, "10001", "10003", "20141");
    }

    @GetMapping(value = "/selectOne")
    @ResponseBody
    public InvokeResult selectOne(@RequestBody Project entity) throws Throwable {
        Project result = projectService.selectOne(entity);
        return InvokeResult.readResult(result, "20140", "10003", "20141");
    }

}
