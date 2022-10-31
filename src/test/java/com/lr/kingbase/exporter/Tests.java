package com.lr.kingbase.exporter;

import com.lr.kingbase.exporter.dao.SysStatActivityMapper;
import com.lr.kingbase.exporter.entity.SysStatActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Tests {
    @Autowired
    private SysStatActivityMapper dao;

    @Test
    public void test(){
        List<SysStatActivity> sysStatActivities = dao.selectState();
        System.out.println(sysStatActivities.size());
        for (SysStatActivity activity : sysStatActivities) {
            System.out.println(activity.toString());
            System.out.println("appName:\t"+activity.getAppName());
            System.out.println("getOriginAppName:\t"+activity.getOriginAppName());
        }
    }

}
