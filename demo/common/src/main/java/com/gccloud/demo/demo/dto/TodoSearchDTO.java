package com.gccloud.demo.demo.dto;

import com.gccloud.starter.common.dto.SearchDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qianxing
 * @version 1.0
 * @date 2022/1/6 9:48
 */
@Data
public class TodoSearchDTO extends SearchDTO {

    @ApiModelProperty(notes = "状态,0是未完成，1是已完成")
    private Integer status;

}
