package com.gccloud.demo.server;

import com.gccloud.starter.common.constant.GlobalConst;
import com.gccloud.starter.core.mp.meta.EnableStarterMeta;
import com.gccloud.starter.core.utils.ConfigGenerateCli;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.List;

/**
 * @author qianxing
 * @date 2021/3/18 9:20 上午
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {GlobalConst.ScanPackage.BASE_COMPONENT, "com.gccloud.demo"})
@MapperScan(value = {GlobalConst.ScanPackage.BASE_DAO, "com.gccloud.demo.service.demo.dao"})
@EnableStarterMeta
@EnableScheduling
@EnableCaching
@EnableAsync
@Slf4j
public class ServerApplication {
    public static void main(String[] args) throws IOException {
        /**
         * 启动监听用户输入指令
         */
        List<String> fileNameList = Lists.newArrayList(GlobalConst.ConfigFile.SYS_CONFIG_FILE_NAME_LIST);
        ConfigGenerateCli.generate(args, fileNameList);
        SpringApplication.run(ServerApplication.class, args);
    }
}
