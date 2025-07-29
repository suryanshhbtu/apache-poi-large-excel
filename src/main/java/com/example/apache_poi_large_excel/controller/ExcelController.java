package com.example.apache_poi_large_excel.controller;

import com.example.apache_poi_large_excel.service.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private static final Logger log = LoggerFactory.getLogger(ExcelController.class);

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Operation(summary = "Download large SXSSF Excel file", description = "Generates and streams a large XLSX file using SXSSFWorkbook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excel file downloaded successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/download_SXSSF")
    public void downloadExcelSXSSF(HttpServletResponse response) {
        log.info("SXSSF Excel generation started");

        StopWatch stopWatch = new StopWatch("ExcelGeneration");
        stopWatch.start("generateLargeExcel");

        try {
            excelService.generateExcelWithSXSSF(response);
        } catch (Exception e) {
            log.error("Error occurred while generating Excel file", e);
        }

        stopWatch.stop();
        log.info("Excel SXSSF file generation completed in {} ms", stopWatch.getTotalTimeMillis());
    }


    @Operation(summary = "Download large XSSF Excel file", description = "Generates and streams a large XLSX file using XSSFWorkbook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excel file downloaded successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/download_XSSF")
    public void downloadExcelXSSF(HttpServletResponse response) {
        log.info("XSSF Excel generation started");

        StopWatch stopWatch = new StopWatch("ExcelGeneration");
        stopWatch.start("generateLargeExcel");

        try {
            excelService.generateExcelWithXSSF(response);
        } catch (Exception e) {
            log.error("Error occurred while generating Excel file", e);
        }

        stopWatch.stop();
        log.info("Excel XSSF file generation completed in {} ms", stopWatch.getTotalTimeMillis());
    }
}
