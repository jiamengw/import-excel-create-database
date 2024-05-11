package com.example.create.database.export;


import com.example.create.database.entity.ImportColumnEntity;
import com.example.create.database.entity.ImportTableEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * @author edith
 * @date 2021-07-19 18:54
 */
@Slf4j
@Component
public class CreateTable {
    @Resource
    DataSourceProperties properties;

    public void create(List<ImportTableEntity> tableEntityList) {
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName(properties.getDriverClassName());
            conn = DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());
            stat = conn.createStatement();
            for (ImportTableEntity tableEntity : tableEntityList) {
                StringBuilder sql = new StringBuilder("CREATE TABLE ");
                sql.append(tableEntity.getTableName()).append("( \n");
                List<ImportColumnEntity> columnEntities = tableEntity.getColumnEntities();
                for (ImportColumnEntity columnEntity : columnEntities) {
                    if (columnEntity.getName() == null || columnEntity.getName().isEmpty()) {
                        continue;
                    }
                    sql.append("`").append(columnEntity.getName()).append("` ").append(columnEntity.getType().toLowerCase());
                    if ("decimal".equalsIgnoreCase(columnEntity.getType())) {
                        sql.append("(").append(columnEntity.getLength()).append(",").append(columnEntity.getMinLen()).append(") ");
                    } else {
                        if (columnEntity.getLength() != 0) {
                            sql.append("(").append(columnEntity.getLength()).append(")");
                        }
                        sql.append(" ");
                    }
                    if ("varchar".equalsIgnoreCase(columnEntity.getType())) {
                        sql.append("CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ");
                    }
                    if ("否".equals(columnEntity.getNullFlag())) {
                        sql.append("NOT NULL ");
                    } else {
                        sql.append("DEFAULT NULL ");
                    }
                    if (columnEntity.getDefaultValue() != null) {
                        sql.append("DEFAULT '").append(columnEntity.getDefaultValue()).append("' ");
                    }
                    if ("id".equals(columnEntity.getName())) {
                        sql.append("AUTO_INCREMENT ");
                    }
                    sql.append("COMMENT '").append(columnEntity.getNote());
                    if (columnEntity.getRemark() != null) {
                        sql.append("(").append(columnEntity.getRemark()).append(")");
                    }
                    sql.append("',\n");
                }
                sql.append("PRIMARY KEY (id) USING BTREE ");
                sql.append(") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT ='");
                sql.append(tableEntity.getTableNote()).append("' ROW_FORMAT = Dynamic;");
                log.info("sql:{}",sql.toString());
                stat.executeUpdate("DROP TABLE IF EXISTS `" + tableEntity.getTableName() + "`;");
                log.info("table:{}", tableEntity.getTableName());
                stat.executeUpdate(sql.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stat != null) {
                    stat.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
