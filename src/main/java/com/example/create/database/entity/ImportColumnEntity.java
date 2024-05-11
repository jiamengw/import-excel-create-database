package com.example.create.database.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author edith
 * @date 2021-07-19 19:03
 */
@Data
public class ImportColumnEntity {
    /**
     * 字段名
     */
    @Excel(name = "字段名")
    private String name;
    /**
     * 中文说明
     */
    @Excel(name = "中文说明")
    private String note;
    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private String type;
    /**
     * 字段长度
     */
    private int length;
    @Excel(name = "字段长度")
    private String lengthStr;
    /**
     * 小数长度
     */
    @Excel(name = "小数位数")
    private int minLen;
    /**
     * 空值标识
     */
    @Excel(name = "是否允许空")
    private String nullFlag;

    /**
     * 空值标识
     */
    @Excel(name = "默认值")
    private String defaultValue;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
