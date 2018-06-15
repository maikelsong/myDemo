package com.charset;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MysqlLatin1 {

    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        ResultSet rs = null;
        new String(rs.getString("zone_name").getBytes("iso-8859-1"), "gbk");
    }


}
