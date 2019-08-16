package com.feiyue.cucumber.service;

import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.util.BusinessException;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public interface ProjectService {

    int insert(Project project) throws BusinessException;

    Project selectOne(Project project) throws BusinessException;

}
