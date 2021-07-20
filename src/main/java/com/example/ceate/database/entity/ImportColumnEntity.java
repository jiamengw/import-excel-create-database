package com.example.ceate.database.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author edith
 * @date 2021-07-19 19:03
 */
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
    @Excel(name = "字段长度")
    private int length;
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

    public ImportColumnEntity() {
    }

    public ImportColumnEntity(String name, String note, String type, int length, int minLen, String nullFlag, String defaultValue, String remark) {
        this.name = name;
        this.note = note;
        this.type = type;
        this.length = length;
        this.minLen = minLen;
        this.nullFlag = nullFlag;
        this.defaultValue = defaultValue;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMinLen() {
        return minLen;
    }

    public void setMinLen(int minLen) {
        this.minLen = minLen;
    }

    public String getNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(String nullFlag) {
        this.nullFlag = nullFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
