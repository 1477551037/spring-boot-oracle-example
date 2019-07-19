package guru.springframework.services;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConnectServiceTest {


    @Autowired
    private  ConnectService connectService;

    @Test
    public void tesst() throws IOException, SQLException {
        List<String> head = connectService.getHead("XYERP_JXJDJB");
        /*List<String> head1 = Arrays.asList(rcch.getColumnNames());
        List<List<String>> head = new ArrayList<>();
        for (String s : head1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            head.add(list);
        }*/
        List<List<Object>> data = connectService.getData(head, "XYERP_JXJDJB");

        ExcelUtil.myWrite(head,data);

    }


    @Test
    public void test2() throws IOException, SQLException {


        List<Object> list = ExcelUtil2.readLessThan1000Row("C:\\Users\\14775\\Desktop\\编码表.xlsx");

        OutputStream out = new FileOutputStream("C:\\Users\\14775\\Desktop\\TEST.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);


        for (int i = 0; i < list.size(); i++) {

            String tableName = ((ArrayList)list.get(i)).get(0).toString();
            System.out.println(tableName+"   ==="+(i+1));

            List<String> head = connectService.getHead(tableName);
            List<List<Object>> data = connectService.getData(head, tableName);

            ExcelUtil.myWriteSheet(writer,i+1,tableName,head,data);
        }


        writer.finish();
        out.close();

    }

}