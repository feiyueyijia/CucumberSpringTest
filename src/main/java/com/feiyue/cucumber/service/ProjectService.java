package com.feiyue.cucumber.service;

import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.util.BusinessException;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public interface ProjectService {

    int insert(Project project) throws BusinessException;

    int update(Project project) throws BusinessException;

    boolean permission(Project project) throws BusinessException;

    boolean isLocked(Project project) throws BusinessException;

    Project selectOne(Project project) throws BusinessException;

}
