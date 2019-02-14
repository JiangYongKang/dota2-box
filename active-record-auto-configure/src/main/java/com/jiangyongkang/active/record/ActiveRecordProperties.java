package com.jiangyongkang.active.record;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: vincent
 * date: 2019-02-14 11:35
 * comment:
 */

@ConfigurationProperties(prefix = "active-record")
public class ActiveRecordProperties {

    /**
     * 是否开启 Banner 图
     */
    private boolean banner = true;

    /**
     * 统一的表名前缀
     */
    private String tableNamePrefix;

    public boolean isBanner() {
        return banner;
    }

    public void setBanner(boolean banner) {
        this.banner = banner;
    }

    public String getTableNamePrefix() {
        return tableNamePrefix;
    }

    public void setTableNamePrefix(String tableNamePrefix) {
        this.tableNamePrefix = tableNamePrefix;
    }

    @Override
    public String toString() {
        return "ActiveRecordProperties{" +
                "banner=" + banner +
                ", tableNamePrefix='" + tableNamePrefix + '\'' +
                '}';
    }
}
