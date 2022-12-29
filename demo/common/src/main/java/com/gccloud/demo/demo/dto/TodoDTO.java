package com.gccloud.demo.demo.dto;

import com.gccloud.starter.common.validator.group.Insert;
import com.gccloud.starter.common.validator.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * DTO：叫做数据传输对象，用于接口数据接口 或 方法的参数传递 而使用
 * 为什么要这样？直接用Entity不省事吗？
 * ----1、以前我们在Controller都是直接使用 Entity 来接收，然后直接保存，这样做使得用户可以通过接口传参直接改变DB的一些值，存在安全问题
 * ----2、一个Entity对应多个DTO，用于针对不同场景使用，比如：新增、修改，有些东西不能修改的，那么在DTO中就不用设置该属性了
 * ----3、如果DTO 和 Entity 存在着很多重复的属性，那么你可以将他们提出到 Common 父类中
 *
 * @Author qianxing
 * @Date 2020年11月19日09:06:14
 * @Version 1.0.0
 */
@Data
public class TodoDTO {

    @ApiModelProperty(notes = "主键")
    @NotBlank(message = "ID不能为空", groups = {Update.class})
    private String id;

    @ApiModelProperty(notes = "待办事项名称")
    @NotBlank(message = "待办事项不能为空", groups = {Insert.class, Update.class})
    private String name;

    /**
     * 待办事项状态
     * 如果你的项目中用到了常量，请一定要注明如下信息(方便其他同事可以直接点击跳转)
     * 否则简直是浪费其他人的时间去查找你的常量有哪些可选值
     * 而且还会招来别人口头的问候
     */
    @ApiModelProperty(notes = "状态,0是未完成，1是已完成")
    private Integer status;

    @ApiModelProperty(notes = "备注")
    @Length(max = 200, message = "备注长度不能超过200", groups = {Insert.class, Update.class})
    private String remark;
}
