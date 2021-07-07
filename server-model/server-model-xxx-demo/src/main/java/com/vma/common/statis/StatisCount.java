package com.vma.common.statis;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION
 *
 * @param <K>
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2021/6/25.
 */
@Data
public class StatisCount<K> {

    protected Integer count;

    protected K id;

    /**
     * 统计数据转map输出
     *
     * @param list
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T extends StatisCount<K>> Map<K, Integer> toCountMap(List<T> list) {
        Map<K, Integer> countMap = new HashMap<>(list != null ? list.size() : 0);
        if (list != null && list.size() > 0) {
            list.forEach(item -> countMap.put(item.getId(), item.getCount()));
        }
        return countMap;
    }

}
