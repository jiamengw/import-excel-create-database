package com.example.create.database.entity;

import java.util.List;

/**
 * @author edith
 * @date 2021-07-19 19:09
 */
public class ImportTableEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表备注
     */
    private String tableNote;

    /**
     * 表字段
     */
    List<ImportColumnEntity> columnEntities;

    public ImportTableEntity() {}

    public ImportTableEntity(String tableName, String tableNote, List<ImportColumnEntity> columnEntities) {
        this.tableName = tableName;
        this.tableNote = tableNote;
        this.columnEntities = columnEntities;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNote() {
        return tableNote;
    }

    public void setTableNote(String tableNote) {
        this.tableNote = tableNote;
    }

    public List<ImportColumnEntity> getColumnEntities() {
        return columnEntities;
    }

    public void setColumnEntities(List<ImportColumnEntity> columnEntities) {
        this.columnEntities = columnEntities;
    }
}
