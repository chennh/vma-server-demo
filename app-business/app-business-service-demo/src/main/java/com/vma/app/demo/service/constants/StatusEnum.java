package com.vma.app.demo.service.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2021/5/6.
 */
@AllArgsConstructor
public enum StatusEnum {
    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    @Getter
    private Integer value;
    @Getter
    private String label;
}
