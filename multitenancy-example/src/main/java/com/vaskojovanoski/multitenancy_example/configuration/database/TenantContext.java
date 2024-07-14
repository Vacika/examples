package com.vaskojovanoski.multitenancy_example.configuration.database;

public class TenantContext {
    private static final ThreadLocal<String> tenantId = new ThreadLocal<>();

    public static String getTenantId() {
        return tenantId.get();
    }
    public static void setTenantId(String tenant) {
        tenantId.set(tenant);
    }
    public static void clear() {
        tenantId.remove();
    }
}