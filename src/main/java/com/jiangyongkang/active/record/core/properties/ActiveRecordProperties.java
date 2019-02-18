package com.jiangyongkang.active.record.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * author: vincent
 * date: 2019-02-14 11:35
 * comment:
 * document: https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html
 * TODO: fix metadata group source type.
 */

@ConfigurationProperties(prefix = "spring.active.record")
public class ActiveRecordProperties {

    /**
     * Whether to display banner。
     */
    private boolean banner = true;

    /**
     * Database table global configuration.
     */
    private TableProperties tableName = new TableProperties();

    /**
     * Database primary key global configuration.
     */
    @NestedConfigurationProperty
    private PrimaryKeyProperties primaryKey = new PrimaryKeyProperties();

    public boolean isBanner() {
        return banner;
    }

    public void setBanner(boolean banner) {
        this.banner = banner;
    }

    public TableProperties tableName() {
        return tableName;
    }

    public void setTableName(TableProperties tableName) {
        this.tableName = tableName;
    }

    public PrimaryKeyProperties primaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKeyProperties primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "ActiveRecordProperties{" +
                "banner=" + banner +
                ", tableName=" + tableName +
                ", primaryKey=" + primaryKey +
                '}';
    }
}
