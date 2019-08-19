package com.feiyue.cucumber.fstructure;

import com.alibaba.fastjson.JSONObject;
import com.feiyue.cucumber.base.APIBaseTest;
import com.feiyue.cucumber.entity.Project;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

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
    Project project = new Project();


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
        project = new Project();
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
        project.setLock("1");

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

    }

    @And("^输入 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void insertProjectInfo(String projectName, String projectDescription) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project = new Project();
        project.setId("1234567");
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setStatus("1");
        project.setLock("1");

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
        project.setStatus(ProjectStatus);

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

        Project project = new Project();
        project.setName(projectName);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/selectOne";

        //模拟页面请求
        JSONObject result = getRequest(url, content);
        queryResultMessage = result.getString("message");
    }

    @Then("^打开功能结构管理菜单$")
    public void openFStructure() {

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

}
