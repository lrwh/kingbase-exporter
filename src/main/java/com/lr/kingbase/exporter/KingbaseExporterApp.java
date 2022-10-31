package com.lr.kingbase.exporter;


import com.lr.kingbase.exporter.task.MetricTask;
import io.prometheus.client.exporter.MetricsServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

// 指定要扫描的Mapper类的包的路径
@MapperScan("com.lr.**.dao")
@SpringBootApplication
public class KingbaseExporterApp {

    /**
     * Expose Prometheus metrics.
     */
    @Bean
    public ServletRegistrationBean<MetricsServlet> metricsServlet() {
        ServletRegistrationBean<MetricsServlet> bean = new ServletRegistrationBean<>(new MetricsServlet(), "/metrics");
        bean.setLoadOnStartup(1);
        return bean;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplication(KingbaseExporterApp.class).run(args);
        new Thread(new MetricTask(applicationContext)).start();
    }
}
