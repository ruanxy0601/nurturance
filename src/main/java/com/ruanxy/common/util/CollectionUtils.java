package com.ruanxy.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合工具类，继承扩展org.springframework.util.CollectionUtils
 *
 * @author 阮翔宇
 * @version 2022-11-02
 */
@Slf4j
public abstract class CollectionUtils extends org.springframework.util.CollectionUtils {

    /**
     * 拆分集合
     *
     * @param sourceList 要拆分的集合
     * @param size       每个集合的元素个数
     * @return 返回拆分后的各个集合
     */
    public static <T> List<List<T>> split(List<T> sourceList, int size) {
        List<List<T>> resultList = new ArrayList<>();
        if (sourceList == null || size < 1) {
            return resultList;
        }
        int sourceListSize = sourceList.size();
        if (sourceListSize <= size) {
            //数据量不足指定的大小
            resultList.add(sourceList);
        } else {
            int pre = sourceListSize / size;
            int last = sourceListSize % size;
            //前面pre个集合，每个大小都是size个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < size; j++) {
                    itemList.add(sourceList.get(i * size + j));
                }
                resultList.add(itemList);
            }
            // last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(sourceList.get(pre * size + i));
                }
                resultList.add(itemList);
            }
        }
        return resultList;
    }

}
