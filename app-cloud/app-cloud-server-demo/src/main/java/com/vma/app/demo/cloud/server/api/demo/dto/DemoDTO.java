package com.vma.app.demo.cloud.server.api.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author hhd
 * @since 2018-09-29
 */
@Data
public class DemoDTO {

    /**
     *
     */
    @ApiModelProperty
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

    private Date date;
}
