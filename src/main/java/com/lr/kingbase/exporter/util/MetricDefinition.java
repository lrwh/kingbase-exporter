package com.lr.kingbase.exporter.util;

import io.prometheus.client.Gauge;

/**
 * 指标定义
 * @author liurui
 * @date 2022/10/26 15:40
 */
public class MetricDefinition {

    public static final Gauge KINGBASE_STATE = Gauge.build()
            .name("kingbase_session_state")
            .help("kingbase state")
            .labelNames("datid","datname","username","app_name","state","host")
            .register();

    public static final Gauge KINGBASE_STATE_TOTAL = Gauge.build()
            .name("kingbase_session_state_total")
            .help("kingbase state total")
            .labelNames("state","host")
            .register();
    public static final Gauge KINGBASE_STATE_USER_TOTAL = Gauge.build()
            .name("kingbase_session_user_total")
            .help("kingbase session user total")
            .labelNames("username","host")
            .register();

    public static final Gauge KINGBASE_MODE = Gauge.build()
            .name("kingbase_db_mode")
            .help("kingbase mode")
            .labelNames("host")
            .register();
    public static final Gauge KINGBASE_SURVIVAL = Gauge.build()
            .name("kingbase_db_survival")
            .help("kingbase survival")
            .labelNames("host")
            .register();
}
