package org.ifunq.tanzx.spring.view;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * ExcelView
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-24 12:09
 **/
public class ExcelView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet sheet = workbook.createSheet("list");
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "Spring Excel test");
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        cell = getCell(sheet, 1, 0);
        cell.setCellValue("日期：2008-10-23");
        getCell(sheet, 2, 0).setCellValue("测试1");
        getCell(sheet, 2, 1).setCellValue("测试2");
        HSSFRow sheetRow = sheet.createRow(3);
        for (short i = 0; i < 10; i++) {
            sheetRow.createCell(i).setCellValue(i * 10);
        }
        String filename = "1234.xls";//设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}