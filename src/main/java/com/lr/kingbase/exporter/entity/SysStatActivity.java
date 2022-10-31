package com.lr.kingbase.exporter.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@ToString
public class SysStatActivity {
    private Long datid;

    private String datname;

    private Integer pid;

    private Integer usesysid;

    private String username;

    private String appName;
    private String originAppName;

    private String clientIp;

    private String clientHost;

    private Integer clientPort;

    private Date bStart;

    private Date xStart;
    private Date qStart;
    private Date sChange;
    private String wait_event_type;

    private String state;
    private String query;


    public String getAppName() {
        if (StringUtils.isEmpty(originAppName)){
            return "None";
        }
        if (originAppName.startsWith("KStudio")){
            return "KStudio";
        }
        if (originAppName.startsWith("sys_ksh")){
            return "sys_ksh";
        }
        if (originAppName.startsWith("kingbase_")){
            return "Ksql";
        }
        if (originAppName.contains("JDBC")){
            return "JDBC";
        }
        return originAppName;
    }

    public String getClientIp() {
        if (StringUtils.isEmpty(clientIp)){
            return "None";
        }
        return clientIp;
    }

    public Integer getClientPort() {
        if (StringUtils.isEmpty(clientPort)){
            return 0;
        }
        return clientPort;
    }
}
