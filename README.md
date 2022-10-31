

当前exporter为非 人大金仓提供的 exporter，如果有改进之处，支持pr。



## 指标描述

| 指标                     | 描述                  | 类型    |
| ---------------------- |---------------------| ----- |
| kingbase_db_mode       | 数据库模式，1：oracle、2：pg | gauge |
| kingbase_session_state | 数据库会话信息             | gauge |
| kingbase_session_state_total | 数据库会话信息(状态统计)       | gauge |
| kingbase_session_user_total | 数据库会话信息(用户统计)       | gauge |
| kingbase_db_survival   | 数据库是否存活，1：存活、0：连接失败 | gauge |


## tag 介绍

kingbase_session_state tag 介绍

| tag名称    | 描述          |
| -------- | ----------- |
| datid    | 数据库实例id     |
| datname  | schame Name |
| username | 当前用户        |
| app_name | 应用接入类型      |
| state    | 当前状态类型      |
| host     | db host     |




