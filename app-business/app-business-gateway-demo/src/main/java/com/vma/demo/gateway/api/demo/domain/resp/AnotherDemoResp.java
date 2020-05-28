package com.vma.demo.gateway.api.demo.domain.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@Data
public class AnotherDemoResp {

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * key
     */
    @ApiModelProperty(value = "key")
    private String otherKey;
    /**
     * v
     */
    @ApiModelProperty(value = "v")
    private String otherValue;
    /**
     * 分组数据
     */
    @ApiModelProperty(value = "分组数据")
    private String otherGroup;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}
