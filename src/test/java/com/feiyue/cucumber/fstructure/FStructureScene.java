package com.feiyue.cucumber.fstructure;

import com.alibaba.nacos.common.util.UuidUtils;
import com.feiyue.cucumber.base.AbstractDefs;
import com.feiyue.cucumber.entity.Project;
import com.feiyue.cucumber.service.ProjectService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public class FStructureScene extends AbstractDefs {

    boolean menuPermission = false; //菜单权限
    boolean projectPermission = false;  //项目权限
    boolean createResult = false;   //创建结果

    //@Resource(name = "projectService")
    @Autowired
    private ProjectService service;

//    @Test
//    public void name() {
//        service.insert(null);
//        System.out.println();
//    }


    @Given("^登录用户拥有菜单权限$")
    public void menuPermission() {
//        if (userName.equals("admin")) {
//            menuPermission = true;
//        }
//        Assert.assertEquals(menuPermission, true);
    }

    @Given("^用户 \"([^\"]*)\" 拥有项目权限$")
    public void projectPermission(String userName) {
        if (userName.equals("admin")) {
            projectPermission = true;
        }
        Assert.assertEquals(projectPermission, true);
    }

    @Given("^动作执行成功$")
    public void success() throws Exception {
        if (createResult) {

        }
        //Assert.assertEquals(createResult, true);
    }

    @When("^点击新建项目按钮$")
    public void createProject() throws Exception {
        Thread.sleep(1000);
    }

    @When("^选择 \"([^\"]*)\" 点击锁定项目按钮$")
    public void lockProject(String projectName) throws Exception {

    }

    @When("^选择 \"([^\"]*)\" 点击修改项目按钮$")
    public void updateProject(String projectName) throws Exception {
        Thread.sleep(1000);
    }

    @And("^输入 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void insertProjectInfo(String projectName, String projectDescription) {
        Project project = new Project();
        project.setId(UuidUtils.generateUuid());
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setStatus("1");
        project.setLock("1");
        service.insert(project);
    }

    @And("^输入 \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void updateProjectInfo(String projectName, String projectDescription, String ProjectStatus) {

    }

    @And("^点击确定按钮$")
    public void confirm() {

    }

    @And("^查看项目 \"([^\"]*)\"$")
    public void findProject(String projectName) {

    }

    @Then("^打开功能结构管理菜单$")
    public void openFStructure() {

    }

    @Then("^返回新建项目执行结果 \"([^\"]*)\"$")
    public void verifyCreateProjectResult(String result) {
        String message = "项目创建成功";
        if (result.equals(message)) {
            createResult = true;
        }
        //Assert.assertEquals(message, result);
    }

    @Then("^返回查看项目执行结果 \"([^\"]*)\"$")
    public void verifyQueryProjectResult(String result) {
        String message = "项目查看成功";
        Assert.assertEquals(message, result);
    }

}
