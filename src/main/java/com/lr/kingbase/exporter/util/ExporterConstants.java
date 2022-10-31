package com.lr.kingbase.exporter.util;

import java.util.Arrays;

public class ExporterConstants {

    public static final String SPLIT = "_____";

    public enum Mode{
        ORACLE("oracle",1),
        PG("pg",2);

        private String name;
        private int type;

        Mode(String name,int type){
            this.name = name;
            this.type = type;
        }

        public static Mode get(String name){
            Mode[] values = Mode.values();
            return Arrays.stream(values).filter(entity->entity.name.equals(name)).findFirst().get();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public interface Survivable{
        Integer SURVIVAL = 1;

        Integer UN_SURVIVAL = 0;
    }
}
