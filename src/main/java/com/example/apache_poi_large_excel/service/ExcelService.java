package com.example.apache_poi_large_excel.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExcelService {

    private static final Logger log = LoggerFactory.getLogger(ExcelService.class);

    public void generateExcelWithSXSSF(HttpServletResponse response) {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            Sheet sheet = workbook.createSheet("Large Data");

            for (int rowIndex = 0; rowIndex < 500_000; rowIndex++) {
                Row row = sheet.createRow(rowIndex);
                for (int colIndex = 0; colIndex < 100; colIndex++) {
                    Cell cell = row.createCell(colIndex);
                    String colLetter = CellReference.convertNumToColString(colIndex); // A, B, C, ...
                    String cellValue = colLetter + (rowIndex + 1); // A1, B1, C1, ..., A10, B10
                    cell.setCellValue(cellValue);
                }

                if (rowIndex % 10_000 == 0) {
                    log.info("Flushing rows at row index: {}", rowIndex);
                    ((SXSSFSheet) sheet).flushRows(100); // flush last 100 rows
                }

                log.info("Completed row: {}", rowIndex);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=large-data.xlsx");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (IOException e) {
            log.error("Failed to write Excel file", e);
            throw new RuntimeException("Failed to write Excel file", e);
        }
    }

    public void generateExcelWithXSSF(HttpServletResponse response) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Regular Data");

            // Keep row count reasonable for XSSF to prevent OOM
            int maxRows = 500_000; // Adjust this based on RAM
            for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
                Row row = sheet.createRow(rowIndex);
                for (int colIndex = 0; colIndex < 100; colIndex++) {
                    Cell cell = row.createCell(colIndex);
                    String colLetter = CellReference.convertNumToColString(colIndex);
                    String cellValue = colLetter + (rowIndex + 1);
                    cell.setCellValue(cellValue);
                }

                if (rowIndex % 5000 == 0) {
                    log.info("Created row: {}", rowIndex);
                }
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=regular-data.xlsx");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (IOException e) {
            log.error("Failed to write Excel file with XSSFWorkbook", e);
            throw new RuntimeException("Failed to write Excel file", e);
        }
    }
}
