package com.feiyue.cucumber.controller;

import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.service.ProjectService;
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
    public InvokeResult insert(@RequestBody Project entity) throws Exception {
        int result = projectService.insert(entity);
        return InvokeResult.writeResult(result, "20100", "10003", "10002");
    }

    @GetMapping(value = "/selectOne")
    @ResponseBody
    public InvokeResult selectOne(@RequestBody Project entity) throws Exception {
        Project result = projectService.selectOne(entity);
        return InvokeResult.readResult(result, "20140", "10003", "20141");
    }

}
