package com.example.hdlitest.io.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author luyi
 * @date 2021/4/22 7:02 下午
 */
public class RegionFile {


    public static void main(String[] args) {
        String filePath = "/Users/lihuidong/Downloads/恒安/1.3/2020行政区划-国家统计局.xlsx";
//        String filePath = "/Users/lihuidong/Downloads/工作簿3.xlsx";
        readerExcel(filePath);
        System.out.println("------------------结束-----------------");

    }

    public static void readerExcel(String filePath){
        InputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            Workbook workbook = null;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls") || filePath.endsWith(".et")) {
                workbook = new HSSFWorkbook(fis);
            }
            fis.close();
            /* 读EXCEL文字内容 */
            // 获取第一个sheet表，也可使用sheet表名获取
            Sheet sheet = workbook.getSheetAt(2);
            // 获取行
            Iterator<Row> rows = sheet.rowIterator();
            Row row;
//            Cell cell;
            FileWriter fw = new FileWriter(new File("/Users/lihuidong/Downloads/ali_project/2020区划0519.sql"));
            BufferedWriter bw = new BufferedWriter(fw);
            while (rows.hasNext()) {
                row = rows.next();
                // 获取单元格
                Iterator<Cell> cells = row.cellIterator();
//                while (cells.hasNext()) {
                Cell cell = cells.next();
                    String code = POIUtil.getCellValue(cell);
                Cell cellName = cells.next();
                    String name = POIUtil.getCellValue(cellName);
                System.out.println(code + " " + name);
                handle(code,name,bw);
//                }
            }
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<String> nameList = Lists.newArrayList("市辖区","省直辖县级行政区划","县","自治区直辖县级行政区划");

    private static void handle(String code,String name,BufferedWriter bw) throws IOException {
        RegionSDO sdo = new RegionSDO();
        sdo.setName(name);
        sdo.setState(1);
        code = code.trim();
        Integer divLevel = getDivLevel(code, sdo);
        if (divLevel == 5){
            return;
        }

        if (nameList.contains(name)){
            sdo.setIsFullName(2);
        }else {
            sdo.setIsFullName(1);
        }


        String sql = "INSERT INTO `region` (`code`, `name`, `div_level`, `parent_code`, `state`, `is_full_name`, `version`, `tenant_id`) VALUES " + " (" + sdo.getCode() + ",'" +
                sdo.getName() + "'," +
                sdo.getDivLevel() + "," +
                sdo.getParentCode() + "," +
                sdo.getState() + "," +
                sdo.getIsFullName() + "," +
                1 + "," +
                "'tny');";
        bw.write(sql);
        bw.newLine();

    }

    private static Integer getDivLevel(String code,RegionSDO sdo){
        if (code.substring(2).equals("0000000000")){
            sdo.setDivLevel(1);
            sdo.setCode(code.substring(0,6));
            sdo.setParentCode(null);
            return 1;
        }else if (code.substring(4).equals("00000000")){
            sdo.setDivLevel(2);
            sdo.setCode(code.substring(0,6));
            StringBuilder ss = new StringBuilder(sdo.getCode());
            ss.replace(2,4,"00");
            sdo.setParentCode(ss.toString());
            return 2;
        }else if (code.substring(6).equals("000000")){
            sdo.setDivLevel(3);
            sdo.setCode(code.substring(0,6));
            StringBuilder ss = new StringBuilder(sdo.getCode());
            ss.replace(4,6,"00");
            sdo.setParentCode(ss.toString());
            return 3;
        }else if (code.substring(9).equals("000")){
            sdo.setDivLevel(4);
            sdo.setCode(code);
            sdo.setParentCode(code.substring(0,6));
            return 4;
        }else {
            sdo.setDivLevel(5);
            sdo.setCode(code);
            StringBuilder ss = new StringBuilder(sdo.getCode());
            ss.replace(9,12,"000");
            sdo.setParentCode(ss.toString());
            return 5;
        }
    }
}
