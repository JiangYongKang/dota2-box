package com.jiangyongkang.active.record.core.properties;

import com.jiangyongkang.active.record.core.enums.GenerationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: vincent
 * date: 2019-02-17 23:25
 * comment:
 */

@ConfigurationProperties(prefix = "primary-key")
public class PrimaryKeyProperties {

    /**
     * 全局主键字段
     */
    private String column;

    /**
     * 全局主键名前缀
     */
    private String prefix;

    /**
     * 全局主键名后缀
     */
    private String suffix;

    /**
     * 全局主键生成策略
     */
    private GenerationType strategy;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public GenerationType getStrategy() {
        return strategy;
    }

    public void setStrategy(GenerationType strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "PrimaryKeyProperties{" +
                "column='" + column + '\'' +
                ", prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                ", strategy=" + strategy +
                '}';
    }
}
