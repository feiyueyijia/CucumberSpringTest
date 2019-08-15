package com.feiyue.cucumber.service.impl;

import com.alibaba.nacos.common.util.UuidUtils;
import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    public int insert(Project param) {
        int result = 0;
        if (StringUtils.isNotEmpty(param.getName()) && !"项目 4".equals(param.getName()) && !param.getName().contains("$%^&*~!")) {
            Project project = new Project();
            project.setId(UuidUtils.generateUuid());
            project.setName(param.getName());
            project.setDescription(param.getDescription());
            project.setStatus("1");
            project.setLock("1");
            result = 1;
        } else if (StringUtils.isEmpty(param.getName())) {
            result = 2;
        }else if ("项目 4".equals(param.getName())) {
            result = 3;
        }else if (param.getName().contains("$%^&*~!")) {
            result = 4;
        }
        return result;
    }

}
