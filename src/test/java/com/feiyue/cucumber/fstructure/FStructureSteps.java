package com.feiyue.cucumber.fstructure;

import com.alibaba.fastjson.JSONObject;
import com.feiyue.cucumber.base.APIBaseTest;
import com.feiyue.cucumber.entity.ProjectEntity;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public class FStructureSteps extends APIBaseTest {

    boolean menuPermission = false; //菜单权限
    boolean projectPermission = false;  //项目权限
    String createResultMessage = "";   //创建结果
    String updateResultMessage = "";   //修改结果
    String lockResultMessage = "";  //锁定结果
    String queryResultMessage = "";   //查看结果
    String loadListResultMessage = "";  //加载列表结果
    String deleteResultMessage = "";    //删除结果
    ProjectEntity project = new ProjectEntity();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Given("^登录用户拥有菜单权限$")
    public void menuPermission() {
//        if (userName.equals("admin")) {
//            menuPermission = true;
//        }
//        Assert.assertEquals(menuPermission, true);
    }

    @Given("^用户 \"([^\"]*)\" 拥有项目权限$")
    public void projectPermission(String userName) {
        project = new ProjectEntity();
        project.setUserName(userName);
    }

    @Given("^动作执行成功$")
    public void success() throws Exception {
        //Assert.assertEquals(createResult, true);
    }

    @When("^点击新建项目按钮$")
    public void createProject() throws Exception {

    }

    @When("^选择 \"([^\"]*)\" 点击锁定项目按钮$")
    public void lockProject(String projectName) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project.setName(projectName);
        project.setLockin("已锁定");

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/lock";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        lockResultMessage = result.getString("message");
    }

    @When("^选择 \"([^\"]*)\" 点击修改项目按钮$")
    public void updateProject(String projectName) throws Exception {
        project.setName(projectName);
    }

    @When("^选择 \"([^\"]*)\" 点击删除项目按钮$")
    public void deleteProject(String projectName) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project.setName(projectName);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/delete";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        deleteResultMessage = result.getString("message");
    }

    @And("^输入 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void insertProjectInfo(String projectName, String projectDescription) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project = new ProjectEntity();
        project.setId(uuid());
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setProgressBar("0%");
        project.setProgress("未开始");
        project.setLockin("未锁定");
        project.setCreateTime(new Date());
        project.setUpdateTime(new Date());

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/insert";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        createResultMessage = result.getString("message");
    }

    @And("^修改 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void updateProjectInfo(String projectDescription, String ProjectStatus) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project.setDescription(projectDescription);
        project.setProgress(ProjectStatus);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/update";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        updateResultMessage = result.getString("message");
    }

    @And("^点击确定按钮$")
    public void confirm() {

    }

    @And("^查看项目 \"([^\"]*)\"$")
    public void findProject(String projectName) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        ProjectEntity project = new ProjectEntity();
        project.setName(projectName);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/selectOne";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        queryResultMessage = result.getString("message");
    }

    @And("^打开功能结构管理菜单$")
    public void openFStructure() throws Exception {
        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        ProjectEntity project = new ProjectEntity();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/findList";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        loadListResultMessage = result.getString("message");
    }

    @Then("^返回新建项目执行结果 \"([^\"]*)\"$")
    public void verifyCreateProjectResult(String result) {
        Assert.assertEquals(createResultMessage, result);
    }

    @Then("^返回查看项目执行结果 \"([^\"]*)\"$")
    public void verifyQueryProjectResult(String result) {
        Assert.assertEquals(queryResultMessage, result);
    }

    @Then("^返回锁定项目执行结果 \"([^\"]*)\"$")
    public void verifyLockProjectResult(String result) {
        Assert.assertEquals(lockResultMessage, result);
    }

    @Then("^返回修改项目执行结果 \"([^\"]*)\"$")
    public void verifyUpdateProjectResult(String result) {
        Assert.assertEquals(updateResultMessage, result);
    }

    @Then("^返回删除项目执行结果 \"([^\"]*)\"$")
    public void verifyDeleteProjectResult(String result) {
        Assert.assertEquals(deleteResultMessage, result);
    }

    @Then("^返回获取项目列表执行结果:$")
    public void loadProjectListResult(String result) {
        Assert.assertEquals(loadListResultMessage, result);
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
