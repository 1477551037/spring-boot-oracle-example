package guru.springframework.services;




import static guru.springframework.services.DataUtil.*;
import static java.lang.System.out;



import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ExcelUtil {

    public void writeV2007() throws IOException {
        OutputStream out = new FileOutputStream("/Users/jipengfei/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
//        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
//        sheet2.setTableStyle(createTableStyle());
//        //writer.write1(null, sheet2);
//        writer.write(createTestListJavaMode(), sheet2);
//        //需要合并单元格
//        writer.merge(5,20,1,1);
//
//        //写第三个sheet包含多个table情况
//        Sheet sheet3 = new Sheet(3, 0);
//        sheet3.setSheetName("第三个sheet");
//        Table table1 = new Table(1);
//        table1.setHead(createTestListStringHead());
//        writer.write1(createTestListObject(), sheet3, table1);
//
//        //写sheet2  模型上打有表头的注解
//        Table table2 = new Table(2);
//        table2.setTableStyle(createTableStyle());
//        table2.setClazz(WriteModel.class);
//        writer.write(createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();

    }

    public static void myWrite(List<String> head, List<List<Object>> data) throws IOException, SQLException {
        OutputStream out = new FileOutputStream("C:\\Users\\14775\\Desktop\\TEST.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        Sheet sheet1 = new Sheet(1, 3);

        List<List<String>> head1 = new ArrayList<>();
        //sheet1.setSheetName();
        for (String s : head) {
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            head1.add(list);
        }
        sheet1.setHead(new ArrayList(head1));

        //sheet1.setHead(createTestListStringHead());
        writer.write1(data, sheet1);
        writer.finish();
        out.close();
    }

    public static void myWriteSheet(ExcelWriter writer ,int sheetNo,String sheetName,List<String> head, List<List<Object>> data) throws IOException, SQLException {
        Sheet sheet = new Sheet(sheetNo, 3);
        List<List<String>> head1 = new ArrayList<>();
        sheet.setSheetName(sheetName);
        for (String s : head) {
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            head1.add(list);
        }
        sheet.setHead(new ArrayList(head1));
        writer.write1(data, sheet);
    }
    public static void myWrite2(List<String> head, List<List<Object>> data) throws IOException, SQLException {

        ExcelUtil2.writeBySimple("C:\\Users\\14775\\Desktop\\TEST2.xlsx",data,head);
    }
}
