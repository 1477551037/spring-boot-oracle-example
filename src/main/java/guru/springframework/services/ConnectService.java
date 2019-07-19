package guru.springframework.services;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
@Slf4j
public class ConnectService {

//    private ProductRepository productRepository;
//    private ProductFormToProduct productFormToProduct;


    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    public ProductServiceImpl(ProductRepository productRepository, ProductFormToProduct productFormToProduct) {
//        this.productRepository = productRepository;
//        this.productFormToProduct = productFormToProduct;
//    }



    public void test(){
        //log.info(jdbcTemplate.toString());
        System.out.println(jdbcTemplate);
    }

    public List<List<Object>> getData(List<String> head,String tableName){

        String sql = "select * from C##D_GOV_M1."+tableName;
        List<List<Object>> data = jdbcTemplate.query(sql, new RowMapper() {

            public List<List<Object>> mapRow(ResultSet rs, int rowNum) throws SQLException {

/*                bnUser.setUserId(rs.getLong("user_id"));
                bnUser.setUserPhone(rs.getLong("user_phone"));
                bnUser.setUserName(rs.getString("user_name"));
                bnUser.setHisMoney(rs.getDouble("his_money"));

                int columnCount = rs.getRow();   //获得列数
                while (rs.next()) {
                    Map<String,Object> rowData = new HashMap<String,Object>();
                    for (int i = 1; i <= columnCount; i++) {
                        rowData.put(rs.getCursorName(i), rs.getObject(i));
                    }
                    list.add(rowData);

                }*/
                    ArrayList row = new ArrayList();
                    for (String s : head) {
                        row.add(rs.getString(s));
                    }
                return row;
            }

        });

        return data;
    }


    public List<String> getHead(String tableName){

        String sql = "select * from C##D_GOV_M1."+tableName;
        RowCountCallbackHandler rcch = new RowCountCallbackHandler();
        jdbcTemplate.query(sql,rcch);
        List<String> head = Arrays.asList(rcch.getColumnNames());


        return head;
    }



}
