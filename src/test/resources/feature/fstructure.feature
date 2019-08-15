Feature: 功能结构管理
  #功能结构管理模块主要作用......

  Background: 打开功能结构管理菜单
    #若要进行相关操作，需要先进入菜单

    Given 登录用户拥有菜单权限
    Then  打开功能结构管理菜单

  @FStructureTest
  Scenario Outline: 新建项目
    When  点击新建项目按钮
    And   输入 "<项目名称>" 和 "<项目描述>"
    And   点击确定按钮
    Then  返回新建项目执行结果 "<新建结果>"
    Given 动作执行成功
    And   查看项目 "<项目名称>"
    Then  返回查看项目执行结果 "<查看结果>"

    Examples: 新建项目执行成功
      | 项目名称 | 项目描述 | 新建结果      | 查看结果     |
      | 项目 1  | 描述 1   | 项目创建成功 | 项目查看成功 |
      | 项目 2  | 描述 2   | 项目创建成功 | 项目查看成功 |
      | 项目 3  | 描述 3   | 项目创建成功 | 项目查看成功 |

    Examples: 新建项目执行失败
      | 项目名称    | 项目描述 | 新建结果                        | 查看结果     |
      | 项目 1     | 描述 1  | 项目创建失败，项目名已存在       | 项目查看成功 |
      |            | 描述 4  | 项目创建失败，项目名不能为空     | 项目不存在   |
      | $%^&*~!345 | 描述 4  | 项目创建失败，项目名存在非法参数 | 项目不存在   |

#  @FStructureTest
#  Scenario Outline: 锁定项目
#    Given 用户 "<user>" 拥有菜单权限
#    Then  打开功能结构管理菜单
#    Given 用户 "<user>" 拥有项目权限
#    When  选择 "<project_name>" 点击锁定项目按钮
#    Then  返回执行结果 "<result>"
#
#    Examples:
#      | user  | project_name | result                         |
#      | admin |   project1   | 项目锁定成功                    |
#      | user1 |   project1   | 项目锁定失败，该用户没有项目权限 |
#
#  @FStructureTest
#  Scenario Outline: 修改项目
#    Given 用户 "<user>" 拥有菜单权限
#    Then  打开功能结构管理菜单
#    Given 用户 "<user>" 拥有项目权限
#    When  选择 "<project_name_select>" 点击修改项目按钮
#    And   输入 "<project_name_input>" and "<project_description>" and "<project_status>"
#    And   点击确定按钮
#    Then  返回执行结果 "<result1>"
#    When  动作执行成功
#    And   查看项目 "<project_name_input>"
#    Then  返回执行结果 "<result2>"
#
#    Examples:
#      | user  | project_name_select | project_name_input | project_description | project_status | result1                        | result2                |
#      | admin | project2            | project2           | project2_info       | status1        | 项目修改成功                    | 项目查看成功            |
#      | admin | project3            | project3           | project3_info       | status2        | 项目修改成功                    | 项目查看成功            |
#      | user1 | project2            | project2           | project2_info       | status3        | 项目修改失败，该用户没有项目权限 | 项目查看成功            |
#      | admin | project2            | $%^&*~!345         | project2_info       | status4        | 项目修改失败，项目名含非法参数   | 项目查看失败，项目不存在 |
#      | admin | project2            | project1           | project2_info       | status5        | 项目修改失败，项目名已存在       | 项目查看失败            |
#      | admin | project1            | project1           | project1_info       | status6        | 项目修改失败，锁定项目无法修改   | 项目查看失败            |
