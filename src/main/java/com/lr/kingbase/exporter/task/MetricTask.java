package com.lr.kingbase.exporter.task;

import com.lr.kingbase.exporter.dao.SysStatActivityMapper;
import com.lr.kingbase.exporter.entity.SysStatActivity;
import com.lr.kingbase.exporter.util.ExporterConstants;
import com.lr.kingbase.exporter.util.MetricDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lr.kingbase.exporter.util.MetricDefinition.*;

public class MetricTask implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(MetricTask.class);
    private ConfigurableApplicationContext applicationContext;
    public MetricTask(ConfigurableApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    @Override
    public void run() {
        Integer interval = getInterval();
        while (true){
            try {
                buildState();
                buildMode();
                buildSurvival(null);
            }catch (Exception e){
                logger.error("数据获取异常：",e);
                buildSurvival(e);
            }
            try {
                Thread.sleep(interval);
            }catch (Exception e){

            }
        }
    }

    private void buildState() {
        List<SysStatActivity> sysStatActivities = getSysStatActivityDao().selectState();
        Map<String, List<SysStatActivity>> collect = sysStatActivities.stream()
                .collect(Collectors.groupingBy(x -> x.getDatid()
                        + ExporterConstants.SPLIT + x.getDatname()
                        + ExporterConstants.SPLIT + x.getUsername()
                        + ExporterConstants.SPLIT + x.getAppName()
                        + ExporterConstants.SPLIT + x.getState()
                        + ExporterConstants.SPLIT + getHost()
                ));
        for (Map.Entry<String, List<SysStatActivity>> entry : collect.entrySet()) {
            String[] tags = entry.getKey().split(ExporterConstants.SPLIT);
            MetricDefinition.KINGBASE_STATE.labels(tags).set(entry.getValue().size());
        }

        Map<String, List<SysStatActivity>> collect1 = sysStatActivities.stream().collect(Collectors.groupingBy(x -> x.getState() + ExporterConstants.SPLIT + getHost()));
        for (Map.Entry<String, List<SysStatActivity>> entry : collect1.entrySet()) {
            KINGBASE_STATE_TOTAL.labels(entry.getKey().split(ExporterConstants.SPLIT)).set(entry.getValue().size());
        }

        Map<String, List<SysStatActivity>> users = sysStatActivities.stream().collect(Collectors.groupingBy(x -> x.getUsername() + ExporterConstants.SPLIT + getHost()));
        for (Map.Entry<String, List<SysStatActivity>> entry : users.entrySet()) {
            KINGBASE_STATE_USER_TOTAL.labels(entry.getKey().split(ExporterConstants.SPLIT)).set(entry.getValue().size());
        }

    }

    private void buildMode(){
        KINGBASE_MODE.labels(getHost()).set(ExporterConstants.Mode.get(getSysStatActivityDao().selectMode()).getType());
    }

    private void buildSurvival(Exception exception){
        if (exception!=null){
            KINGBASE_SURVIVAL.labels(getHost()).set(ExporterConstants.Survivable.UN_SURVIVAL);
            cleanMetrics();
        }else{
            KINGBASE_SURVIVAL.labels(getHost()).set(ExporterConstants.Survivable.SURVIVAL);
        }
    }

    private void cleanMetrics(){
        KINGBASE_MODE.clear();
        KINGBASE_STATE.clear();
        KINGBASE_STATE_TOTAL.clear();
        KINGBASE_STATE_USER_TOTAL.clear();
    }

    public SysStatActivityMapper getSysStatActivityDao() {
        return applicationContext.getBean(SysStatActivityMapper.class);
    }

    private Integer getInterval(){
        try {
            return Integer.parseInt(applicationContext.getEnvironment().getProperty("interval", "10"))*1000;
        }catch (Exception e){
            return 10000;
        }
    }
    private String getHost(){
        return applicationContext.getEnvironment().getProperty("db.host");
    }
}
