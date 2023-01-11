package com.ruanxy.common.util;


import org.apache.commons.lang3.StringUtils;

/**
 * ThreadLocal 工具类
 * @Author ruanxiangyu on 2022/11/02.
 */
public class ThreadLocalUtil {

    /**
     * 当前用户租户id
     */
    private final static ThreadLocal<String> tenantId = new ThreadLocal<>();

    /**
     * 当前用户id
     */
    private final static ThreadLocal<String> currentUserId = new ThreadLocal<>();

    /**
     * 当前用户姓名
     */
    private final static ThreadLocal<String> currentUserName = new ThreadLocal<>();

    /**
     * 当前用户角色英文名
     */
    private final static ThreadLocal<String> roleEnNames = new ThreadLocal<>();

    /**
     * 模块名称
     */
    private final static ThreadLocal<String> module = new ThreadLocal<>();

    /**
     * 是否启用数据范围（针对EntityWrapper方式）
     * 查询特定人员数据筛选范围，如果 user为空，则跳过 数据筛选范围，查询全部数据
     */
    private final static ThreadLocal<String> dataScopeUserId = new ThreadLocal<>();

    public static ThreadLocal<String> getThreadLocalTenantId() {
        return tenantId;
    }

    public static ThreadLocal<String> getThreadLocalCurrentUserId() {
        return currentUserId;
    }

    public static ThreadLocal<String> getThreadLocalCurrentUserName() {
        return currentUserName;
    }

    public static ThreadLocal<String> getThreadLocalRoleEnNames() {
        return roleEnNames;
    }

    public static ThreadLocal<String> getThreadLocalModule() {
        return module;
    }

    public static String getTenantId() {
        return StringUtils.isBlank(tenantId.get()) ? "" : tenantId.get();
    }

    public static String getCurrentUserId() {
        return StringUtils.isBlank(currentUserId.get()) ? "" : currentUserId.get();
    }

    public static String getCurrentUserName() {
        return StringUtils.isBlank(currentUserName.get()) ? "" : currentUserName.get();
    }

    public static String getRoleEnNames() {
        return StringUtils.isBlank(roleEnNames.get()) ? "" : roleEnNames.get();
    }

    public static String getModule() {
        return StringUtils.isBlank(module.get()) ? "" : module.get();
    }

    public static ThreadLocal<String> getThreadLocalDataScopeUserId() {
        return dataScopeUserId;
    }

    public static String getDataScopeUserId() {
        return StringUtils.isBlank(dataScopeUserId.get()) ? "" : dataScopeUserId.get();
    }

}

