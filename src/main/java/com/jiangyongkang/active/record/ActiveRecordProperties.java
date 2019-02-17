package com.jiangyongkang.active.record;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: vincent
 * date: 2019-02-14 11:35
 * comment:
 */

@ConfigurationProperties(prefix = "spring.active.record")
public class ActiveRecordProperties {

    /**
     * 是否开启 Banner 图
     */
    private boolean banner = true;

    /**
     * 全局表名前缀
     */
    private String tableNamePrefix;

    /**
     * 全局表名后缀
     */
    private String tableNameSuffix;

    /**
     * 全局主键字段
     */
    private String primaryKey;

    /**
     * 全局主键名前缀
     */
    private String primaryKeyPrefix;

    /**
     * 全局主键名后缀
     */
    private String primaryKeySuffix;

    public String getTableNameSuffix() {
        return tableNameSuffix;
    }

    public void setTableNameSuffix(String tableNameSuffix) {
        this.tableNameSuffix = tableNameSuffix;
    }

    public String getPrimaryKeyPrefix() {
        return primaryKeyPrefix;
    }

    public void setPrimaryKeyPrefix(String primaryKeyPrefix) {
        this.primaryKeyPrefix = primaryKeyPrefix;
    }

    public String getPrimaryKeySuffix() {
        return primaryKeySuffix;
    }

    public void setPrimaryKeySuffix(String primaryKeySuffix) {
        this.primaryKeySuffix = primaryKeySuffix;
    }

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

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "ActiveRecordProperties{" +
                "banner=" + banner +
                ", tableNamePrefix='" + tableNamePrefix + '\'' +
                ", tableNameSuffix='" + tableNameSuffix + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", primaryKeyPrefix='" + primaryKeyPrefix + '\'' +
                ", primaryKeySuffix='" + primaryKeySuffix + '\'' +
                '}';
    }
}
