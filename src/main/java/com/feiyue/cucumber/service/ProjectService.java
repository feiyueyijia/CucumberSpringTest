package com.feiyue.cucumber.service;

import com.feiyue.cucumber.entity.ProjectEntity;
import com.feiyue.cucumber.util.BusinessException;

import java.util.List;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public interface ProjectService {

    int insert(ProjectEntity project) throws BusinessException;

    int update(ProjectEntity project) throws BusinessException;

    int delete(ProjectEntity project) throws BusinessException;

    boolean permission(ProjectEntity project) throws BusinessException;

    boolean isLocked(ProjectEntity project) throws BusinessException;

    ProjectEntity selectOne(ProjectEntity project) throws BusinessException;

    List<ProjectEntity> findList(ProjectEntity project) throws BusinessException;

}
