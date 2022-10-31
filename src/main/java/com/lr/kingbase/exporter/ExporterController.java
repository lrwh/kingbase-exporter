package com.lr.kingbase.exporter;

import com.lr.kingbase.exporter.dao.SysStatActivityMapper;
import com.lr.kingbase.exporter.entity.SysStatActivity;
import com.lr.kingbase.exporter.util.ExporterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ExporterController {

    @Autowired
    private SysStatActivityMapper dao;

    @GetMapping("/")
    public String test(){
        List<SysStatActivity> sysStatActivities = dao.selectState();
        System.out.println(sysStatActivities.size());
        for (SysStatActivity activity : sysStatActivities) {
            System.out.println(activity.toString());
            System.out.println("appName:\t"+activity.getAppName());
            System.out.println("getOriginAppName:\t"+activity.getOriginAppName());
        }
        System.out.println("-----------------------");
        Map<String, List<SysStatActivity>> collect = sysStatActivities.stream().collect(Collectors.groupingBy(x -> {
//            "datid","datname","username","appName","state"
            return x.getDatid()
                    + ExporterConstants.SPLIT+x.getDatname()
                    + ExporterConstants.SPLIT+x.getUsername()
                    + ExporterConstants.SPLIT+x.getAppName()
                    + ExporterConstants.SPLIT+x.getState()
                    ;
        }));
        for (Map.Entry<String, List<SysStatActivity>> entry : collect.entrySet()) {
            System.out.println(entry.getKey()+"\t"+entry.getValue().size());
        }
        return "success";
    }
}
