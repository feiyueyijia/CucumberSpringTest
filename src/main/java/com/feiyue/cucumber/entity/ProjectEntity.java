package com.feiyue.cucumber.entity;

import com.feiyue.cucumber.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能结构管理项目对象Entity
 * Author auto
 * Date  2019-08-21
 */
@Table(name = "t_fstructure_project")
public class ProjectEntity extends BaseEntity {

    @Id
    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "id", length = 64)
    private String id;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "progress", length = 32)
    private String progress;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "lockin", length = 11)
    private String lockin;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "progress_bar", length = 11)
    private String progressBar;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "create_time", length = 19)
    private Date createTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "update_time", length = 19)
    private Date updateTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "keep_time", length = 11)
    private int keepTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "sort", length = 11)
    private int sort;

    @Column(name = "group_id", length = 64)
    private String groupId;


    public ProjectEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getLockin() {
        return lockin;
    }

    public void setLockin(String lockin) {
        this.lockin = lockin;
    }

    public String getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(String progressBar) {
        this.progressBar = progressBar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getKeepTime() {
        return keepTime;
    }

    public void setKeepTime(int keepTime) {
        this.keepTime = keepTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**************************************此下为非数据库字段属性**************************************/

    @Transient
    public String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
