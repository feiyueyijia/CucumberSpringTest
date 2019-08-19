package com.feiyue.cucumber.service.impl;

import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.service.ProjectService;
import com.feiyue.cucumber.util.BusinessException;
import com.feiyue.cucumber.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    @Transactional
    public int insert(Project param) throws BusinessException {
        this.validateProject(param);
        Project project = new Project();
        project.setId("1234567");
        project.setName(param.getName());
        project.setDescription(param.getDescription());
        project.setStatus("1");
        project.setLock("1");
        int result = 1;
        return result;
    }

    @Transactional
    public int update(Project param) throws BusinessException {
        Project project = new Project();
        project.setId("1234567");
        project.setName(param.getName());
        project.setDescription(param.getDescription());
        project.setStatus("1");
        project.setLock("1");
        int result = 1;
        return result;
    }

    private void validateProject(Project project) throws BusinessException {
        this.validateProjectNameDuplicate(project);
        this.validateProjectNameEmpty(project);
        this.validateProjectNameLegal(project);
    }

    private void validateProjectNameDuplicate(Project project) throws BusinessException {
        if ("项目 4".equals(project.getName())) {
            throw new BusinessException("20101");
        }
    }

    private void validateProjectNameEmpty(Project project) throws BusinessException {
        if (StringUtils.isEmpty(project.getName())) {
            throw new BusinessException("20102");
        }
    }

    private void validateProjectNameLegal(Project project) throws BusinessException {
        if (project.getName().contains("$%^&*~!")) {
            throw new BusinessException("20103");
        }
    }

    public boolean permission(Project project) throws BusinessException {
        if (!"管理员".equals(project.getUserName())) {
            return false;
        }
        return true;
    }

    public boolean isLocked(Project project) throws BusinessException {
//        if ("UNLOCKED".equals(project.getLock())) {
//            return false;
//        }
        if ("已锁定".equals(project.getStatus())) {
            return true;
        }
        return false;
    }

    public Project selectOne(Project project) throws BusinessException {
        this.validateProject1(project);
        return new Project();
    }


    private void validateProject1(Project project) throws BusinessException {
        if (StringUtils.isEmpty(project.getName())) {
            throw new BusinessException("20141");
        } else if (project.getName().contains("$%^&*~!")) {
            throw new BusinessException("20141");
        }
    }

}
