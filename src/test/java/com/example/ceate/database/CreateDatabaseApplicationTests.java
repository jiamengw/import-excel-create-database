package com.example.ceate.database;

import com.example.ceate.database.entity.ImportTableEntity;
import com.example.ceate.database.export.CreateTable;
import com.example.ceate.database.export.ImportExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            File file = new File("D:\\import\\test.xlsx");
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
                createTable.create(tableEntityList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
