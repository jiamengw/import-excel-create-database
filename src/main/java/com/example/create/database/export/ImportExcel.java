package com.example.create.database.export;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiCellUtil;
import com.example.create.database.entity.ImportColumnEntity;
import com.example.create.database.entity.ImportTableEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author edith
 * @date 2021-07-19 19:01
 */
@Slf4j
@Component
public class ImportExcel {
    public List<ImportTableEntity> importExcel(ByteArrayOutputStream outputStream) throws Exception {
        List<ImportTableEntity> tableEntityList = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(outputStream.toByteArray()));
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            Row tempRow = sheet.getRow(0);
            String cellValue = PoiCellUtil.getCellValue(tempRow.getCell(0)).replaceAll("\\uFEFF", "");
            log.info("cellValue:{}", cellValue);
            if (cellValue.isEmpty()){
                continue;
            }
            ImportTableEntity tableEntity = new ImportTableEntity();
            String[] split = cellValue.split("--");
            tableEntity.setTableName(split[0]);
            tableEntity.setTableNote(split[1]);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setStartSheetIndex(i);
            params.setSheetNum(1);
            List<ImportColumnEntity> list = ExcelImportUtil.importExcel(new ByteArrayInputStream(outputStream.toByteArray()), ImportColumnEntity.class, params);
            list.forEach(e->{
                e.setName(e.getName().replaceAll("\\uFEFF", ""));
                if (StringUtils.hasLength(e.getLengthStr()) ){
                    if (e.getLengthStr().contains(",")){
                        String[] split1 = e.getLengthStr().split(",");
                        e.setLength(Integer.parseInt(split1[0]));
                        e.setMinLen(Integer.parseInt(split1[1]));
                    }else {
                        e.setLength(Integer.parseInt(e.getLengthStr()));
                    }
                }
                if (StringUtils.hasLength(e.getRemark()) && e.getRemark().contains("默认为")){
                    e.setDefaultValue(e.getRemark().split("默认为")[1]);
                }
            });
            list = list.stream().filter(e -> e.getType() != null && !e.getType().isEmpty()).collect(Collectors.toList());
            log.info("长度：{}", list.size());
            tableEntity.setColumnEntities(list);
            tableEntityList.add(tableEntity);
        }
        return tableEntityList;
    }
}
