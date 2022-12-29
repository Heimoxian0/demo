package com.gccloud.demo.service.config;

import com.gccloud.starter.common.constant.GlobalConst;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * 自定义swagger扫描配置
 * 如果你不按照如下配置，那么你写的swagger接口很可能是不展示出来的
 *
 * @author qianXing
 * @date 2020年11月19日09:37:53
 * hhhh
 */
@Configuration
public class SwaggerBeanConfig {

    private static final List<ResponseMessage> RESPONSE_MESSAGES = Lists.newArrayList(
            new ResponseMessageBuilder().code(GlobalConst.Response.Code.SUCCESS).message("成功").build(),
            new ResponseMessageBuilder().code(GlobalConst.Response.Code.NO_FOUNT).message("您访问的资源不存在").build(),
            new ResponseMessageBuilder().code(GlobalConst.Response.Code.NO_LOGIN).message("您没有登录或token已过期").build(),
            new ResponseMessageBuilder().code(GlobalConst.Response.Code.NO_ACCESS).message("您无权访问该资源").build(),
            new ResponseMessageBuilder().code(GlobalConst.Response.Code.SERVER_ERROR).message("系统内部异常").build()
    );

    /**
     * 注意这里的方法名不可以重复
     *
     * @return
     */
    @Bean
    public Docket todoDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                /**
                 * 这里展示的是你项目的名称，用于在swagger页面左上角下拉选择使用，名字别重复了，否则下拉框就只展示一个
                 */
                .groupName("templateProjectName")
                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, RESPONSE_MESSAGES)
                .globalResponseMessage(RequestMethod.POST, RESPONSE_MESSAGES)
                .globalResponseMessage(RequestMethod.PUT, RESPONSE_MESSAGES)
                .globalResponseMessage(RequestMethod.DELETE, RESPONSE_MESSAGES)
                .select()
                /**
                 *  这里是说哪些包下的swagger放在你上面的分组中，所以，这里填写的是你自己项目的包前缀
                 */
                .apis(RequestHandlerSelectors.basePackage("com.gccloud.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("标题")
                // 版本号
                .version("1.0.0")
                .build();
    }

}
