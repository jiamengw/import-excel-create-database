package com.example.create.database;

import com.example.create.database.entity.ImportTableEntity;
import com.example.create.database.export.CreateTable;
import com.example.create.database.export.ImportExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Slf4j
@SpringBootTest
class CreateDatabaseApplicationTests {

    @Autowired
    private CreateTable createTable;
    @Autowired
    private ImportExcel importExcel;
    @Test
    void createTable() {
        try {
            File file = new ClassPathResource("excel/template.xlsx").getFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();

            List<ImportTableEntity> tableEntityList = importExcel.importExcel(outputStream);
            log.info(tableEntityList.toString());
            if (!tableEntityList.isEmpty()){
//                createTable.create(tableEntityList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
