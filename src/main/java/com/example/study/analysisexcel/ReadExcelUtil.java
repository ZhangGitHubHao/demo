package com.example.study.analysisexcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel数据工具类(将Excel加载到内存)
 *
 * @author zhangh
 * @date 2019/10/21
 */
public class ReadExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadExcelUtil.class);
    /**
     * 存放属性集
     */
    private Map<Integer, String[]> fieldsMap = new HashMap<>();
    /**
     * 存放解析后的对象List
     */
    private List<Object> objectsList = new ArrayList<>();
    /**
     * 反射运行时对象
     */
    private Object object = null;
    /**
     * Excel文件路径
     */
    private String path = null;

    /**
     * 获取解析后的对象集
     * @return
     */
    public List<Object> getObjectsList() {
        return this.objectsList;
    }

    public ReadExcelUtil(Object object, String path) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        this.object = object;
        this.path = path;
        readExcel();
    }

    /**
     * 添加Object到List中
     *
     * @param object
     * @return
     */
    public boolean addListObject(Object object) {
        boolean isSucceed = this.objectsList.add(object);
        return isSucceed;
    }

    /**
     * 读取excel,判断是xls结尾(2010之前)；还是xlsx结尾(2010以后)的Excel
     *
     * @return
     * @throws IOException
     */
    public boolean readExcel() throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (StringUtils.isEmpty(path)) {
            return false;
        } else {
            // 截取后缀名，判断是xls还是xlsx
            String postfix = path.substring(path.lastIndexOf(".") + 1);
            if (!StringUtils.isEmpty(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX_xls.equals(postfix)) {
                    return readXls();
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX_xlsx.equals(postfix)) {
                    return readXlsx();
                }
            } else {
                LOGGER.error("文件后缀名有误！");
                throw new ResolveFileException("文件后缀名有误！" + "[" + path + "]");
            }
        }
        return false;
    }

    /**
     * 读取xls(2010)之后的Excel
     *
     * @return
     * @throws IOException
     */
    public boolean readXlsx() throws IOException {
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // 遍历sheet页
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            String[] fields = null;
            if (xssfSheet == null) {
                continue;
            }
            int shuxin = 0;
            // 循环行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                if (rowNum==0){
                    this.object = null;

                }
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    shuxin++;
                    continue;
                }
                int cloumns = xssfRow.getLastCellNum();
                int i = 0;
                //获取第一行的所有属性
                if (rowNum == shuxin) {
                    fields = getFields(xssfRow, cloumns);
                    System.out.println(Arrays.toString(fields));
                    continue;
                }
                //遍历数据,反射set值
                while (i < cloumns) {
                    System.out.println("读取value的值:"+i);

                    XSSFCell xssfCell = xssfRow.getCell(i);
                    System.out.println("读取value的值:"+getValue(xssfCell));

                    if (xssfCell==null){
                        i++;
                        continue;
                    }
                    xssfCell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = getValue(xssfCell);
                    System.out.println("读取value的值:"+getValue(xssfCell));
                    try {
                        ReflectionInitValue.setValue(object, fields[i], value);
                    } catch (Exception e) {
                        throw new ResolveFileException(e.getMessage());
                    }
                    i++;
                }
                //通过反射执行clone复制对象
                Object result = ReflectionInitValue.invokeClone(object, "clone");
                this.addListObject(result);
                // System.out.println(object.toString());
            }
        }
        return true;
    }

    /**
     * 读取xls(2010)之前的Excel
     *
     * @return
     * @throws IOException
     */
    public boolean readXls() throws IOException, ResolveFileException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // 遍历sheet页
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            String[] fields = null;
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//                object = null;
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int cloumns = hssfRow.getLastCellNum();
                int i = 0;
                //获取第一行的所有属性
                if (rowNum == 0) {
                    //获取属性字段
                    fields = getFields(hssfRow, cloumns);
                    fieldsMap.put(numSheet, fields);
                    continue;
                }
                //遍历数据,反射set值
                while (i < cloumns) {
                    HSSFCell field = hssfRow.getCell(i);
                    String value = getValue(field);
                    try {
                        ReflectionInitValue.setValue(object, fields[i], value);
                    } catch (Exception e) {
                        throw new ResolveFileException(e.getMessage());
                    }
                    i++;
                }
                //通过反射执行clone复制对象
                Object result = ReflectionInitValue.invokeClone(object, "clone");
                this.addListObject(result);
            }
        }
        return true;
    }

    /**
     * xlsx -根据数据类型，获取单元格的值
     *
     * @param xssfRow
     * @return
     */
    @SuppressWarnings({"static-access"})
    private static String getValue(XSSFCell xssfRow) {
        String value = null;
        try {
            if (xssfRow.getCellTypeEnum() == CellType.BOOLEAN) {
                // 返回布尔类型的值
                value = String.valueOf(xssfRow.getBooleanCellValue()).replace(" ", "");
            } else if (xssfRow.getCellTypeEnum() == CellType.NUMERIC) {
                // 返回数值类型的值
                value = String.valueOf(xssfRow.getNumericCellValue()).replace(" ", "");
            } else {
                // 返回字符串类型的值
                value = String.valueOf(xssfRow.getStringCellValue()).replace(" ", "");
            }
        } catch (Exception e) {
            //单元格为空，不处理
            value = null;
            LOGGER.error("单元格为空！");
        }
        return value;
    }

    /**
     * xls-根据数据类型，获取单元格的值
     *
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({"static-access"})
    private static String getValue(HSSFCell hssfCell) {
        String value = null;
        try {
            if (hssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
                // 返回布尔类型的值
                value = String.valueOf(hssfCell.getBooleanCellValue()).replaceAll(" ", "");
            } else if (hssfCell.getCellTypeEnum() == CellType.NUMERIC) {
                // 返回数值类型的值
                value = String.valueOf(hssfCell.getNumericCellValue()).replaceAll(" ", "");
            } else {
                // 返回字符串类型的值
                value = String.valueOf(hssfCell.getStringCellValue()).replaceAll(" ", "");
            }
        } catch (Exception e) {
            //单元格为空，不处理
            value = null;
            LOGGER.error("单元格为空！");
        }
        return value;
    }

    /**
     * xls Excel文件类型获取属性（2010之前）
     *
     * @param cloumns
     * @return String[]
     */
    private static String[] getFields(HSSFRow hssfRow, int cloumns) {
        String[] fields = new String[cloumns];
        int i = 0;
        try {
            while (i < cloumns) {
                HSSFCell field = hssfRow.getCell(i);
                if (field == null){
                    i++;
                    continue;
                }
                String value = getValue(field);
                fields[i] = value.trim();
                i++;
            }
        } catch (Exception e) {
            throw new ResolveFileException("获取属性集失败！");
        }
        return fields;
    }

    /**
     * xlsx Excel文件类型获取属性（2010之后）
     *
     * @param cloumns
     * @return String[]
     */
    private static String[] getFields(XSSFRow xssfRow, int cloumns) {
        String[] fields = new String[cloumns];
        int i = 0;
        try {
            while (i < cloumns) {
                XSSFCell field = xssfRow.getCell(i);
                if (field == null){
                    i++;
                    continue;
                }
                String value = getValue(field);
                fields[i] = value.trim();
                i++;
            }
        } catch (Exception e) {
            throw new ResolveFileException("获取属性集失败！");
        }
        return fields;
    }

}
