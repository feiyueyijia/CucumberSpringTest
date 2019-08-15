package com.feiyue.cucumber.service.impl;

import com.alibaba.nacos.common.util.UuidUtils;
import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    private RestTemplate restTemplate;

    @Autowired
    public ProjectServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public int insert(Project param) {
        int result = 0;
        if (StringUtils.isNotEmpty(param.getName())) {
            Project project = new Project();
            project.setId(UuidUtils.generateUuid());
            project.setName(param.getName());
            project.setDescription(param.getDescription());
            project.setStatus("1");
            project.setLock("1");
            result = 1;
        }
        return result;
    }

}
