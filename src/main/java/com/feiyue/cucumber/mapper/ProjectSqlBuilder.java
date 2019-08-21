package com.feiyue.cucumber.mapper;

import com.feiyue.cucumber.entity.ProjectEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 功能结构管理项目对象SqlBuilder
 * Author auto
 * Date  2019-08-21
 */
public class ProjectSqlBuilder {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件并列查询取交集
     *
     * @param   project    对象实体
     * @return  Sql语句
     */
    public String buildFindProjectByAndCondition(final ProjectEntity project) {
        return buildFindProjectByCondition(project, 0);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件亦或查询取并集
     *
     * @param   project    对象实体
     * @return  Sql语句
     */
    public String buildFindProjectByORCondition(final ProjectEntity project) {
        return buildFindProjectByCondition(project, 1);
    }

    private String buildFindProjectByCondition(final ProjectEntity project, final int type) {
        String orSql = "";
        if (type == 1) {
            orSql = " || '%'";
        } else {
            orSql = " '%'";
        }
        String finalOrSql = orSql;
        String sqlResult = new SQL() {{
            SELECT(
                    "id AS id," +
                    "name AS name," +
                    "description AS description," +
                    "progress AS progress," +
                    "lockin AS lockin," +
                    "progress_bar AS progressBar," +
                    "create_time AS createTime," +
                    "update_time AS updateTime," +
                    "keep_time AS keepTime," +
                    "sort AS sort," +
                    "group_id AS groupId"
                   );
            FROM("t_fstructure_project");
            if (project.getId() != null && !project.getId().equals("")) {
                WHERE("id like #{project.id}" + finalOrSql);
            }
            if (project.getName() != null && !project.getName().equals("")) {
                WHERE("name like #{project.name}" + finalOrSql);
            }
            if (project.getDescription() != null && !project.getDescription().equals("")) {
                WHERE("description like #{project.description}" + finalOrSql);
            }
            if (project.getProgress() != null && !project.getProgress().equals("")) {
                WHERE("progress like #{project.progress}" + finalOrSql);
            }
            if (project.getLockin() != null && !project.getLockin().equals("")) {
                WHERE("lockin like #{project.lockin}" + finalOrSql);
            }
            if (project.getProgressBar() != null && !project.getProgressBar().equals("")) {
                WHERE("progress_bar like #{project.progressBar}" + finalOrSql);
            }
            if (project.getCreateTime() != null) {
                WHERE("create_time like #{project.createTime}" + finalOrSql);
            }
            if (project.getUpdateTime() != null) {
                WHERE("update_time like #{project.updateTime}" + finalOrSql);
            }
//            if (project.getKeepTime() > 0) {
//                WHERE("keep_time like #{project.keepTime}" + finalOrSql);
//            }
//            if (project.getSort() > 0) {
//                WHERE("sort like #{project.sort}" + finalOrSql);
//            }
            if (project.getGroupId() != null && !project.getGroupId().equals("")) {
                WHERE("group_id like #{project.groupId}" + finalOrSql);
            }
        }}.toString();

        if (project.getOrders() != null && project.getOrders().size() > 0) {
            Map<String, String> orderMap = project.getOrders();
            int count = 0;
            sqlResult = sqlResult + " ORDER BY ";
            for (String order : orderMap.keySet()) {
                if (count != 0) {
                    order = ", " + order;
                }
                if (ProjectEntity.DESC.equals(orderMap.get(order))) {
                    sqlResult = sqlResult + order + " DESC";
                } else {
                    sqlResult = sqlResult + order + " ASC";
                }
                count++;
            }
        }
        return sqlResult;
    }

}
