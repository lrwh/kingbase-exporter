package com.lr.kingbase.exporter.dao;

import com.lr.kingbase.exporter.entity.SysStatActivity;

import java.util.List;
import java.util.Map;

public interface SysStatActivityMapper {

    List<SysStatActivity> selectState();

    String selectMode();
}
