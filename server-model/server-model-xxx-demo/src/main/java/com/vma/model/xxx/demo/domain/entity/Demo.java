package com.vma.model.xxx.demo.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Demo表实体
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@Data
@TableName("other_info")
public class Demo extends Model<Demo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = DemoTable.ID, type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * key
     */
    @TableField(DemoTable.OTHER_KEY)
    @ApiModelProperty(value = "key")
    private String otherKey;
    /**
     * value
     */
    @TableField(DemoTable.OTHER_VALUE)
    @ApiModelProperty(value = "value")
    private String otherValue;
    /**
     * 分组数据
     */
    @TableField(DemoTable.OTHER_GROUP)
    @ApiModelProperty(value = "分组数据")
    private String otherGroup;
    /**
     * 备注
     */
    @TableField(DemoTable.REMARKS)
    @ApiModelProperty(value = "备注")
    private String remarks;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
