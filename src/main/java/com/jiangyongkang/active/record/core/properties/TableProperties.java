package com.jiangyongkang.active.record.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: vincent
 * date: 2019-02-18 00:02
 * comment:
 */

@ConfigurationProperties(prefix = "table-name")
public class TableProperties {

    /**
     * 全局表名前缀
     */
    private String prefix;

    /**
     * 全局表名后缀
     */
    private String suffix;

    public String prefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String suffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "TableProperties{" +
                "prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
