

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


## 指标预览

```
# HELP kingbase_session_state kingbase state
# TYPE kingbase_session_state gauge
kingbase_session_state{datid="12135",datname="test",username="system",app_name="KStudio",state="null",host="172.31.24.21",} 4.0
kingbase_session_state{datid="12135",datname="test",username="datakit",app_name="JDBC",state="active",host="172.31.24.21",} 1.0
kingbase_session_state{datid="12135",datname="test",username="datakit",app_name="JDBC",state="idle",host="172.31.24.21",} 19.0
kingbase_session_state{datid="12135",datname="test",username="system",app_name="None",state="null",host="172.31.24.21",} 2.0
# HELP kingbase_db_mode kingbase mode
# TYPE kingbase_db_mode gauge
kingbase_db_mode{host="172.31.24.21",} 1.0
# HELP kingbase_db_survival kingbase survival
# TYPE kingbase_db_survival gauge
kingbase_db_survival{host="172.31.24.21",} 1.0
# HELP kingbase_session_user_total kingbase session user total
# TYPE kingbase_session_user_total gauge
kingbase_session_user_total{username="system",host="172.31.24.21",} 6.0
kingbase_session_user_total{username="datakit",host="172.31.24.21",} 20.0
# HELP kingbase_session_state_total kingbase state total
# TYPE kingbase_session_state_total gauge
kingbase_session_state_total{state="active",host="172.31.24.21",} 1.0
kingbase_session_state_total{state="null",host="172.31.24.21",} 6.0
kingbase_session_state_total{state="idle",host="172.31.24.21",} 19.0
```

