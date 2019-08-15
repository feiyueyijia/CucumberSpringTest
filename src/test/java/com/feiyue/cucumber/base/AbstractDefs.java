package com.feiyue.cucumber.base;

import com.feiyue.cucumber.CucumberApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by jisongZhou on 2019/8/7.
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CucumberApplication.class)
@WebAppConfiguration
@SpringBootTest
public class AbstractDefs {

}
