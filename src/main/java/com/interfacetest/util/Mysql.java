package com.interfacetest.util;

import java.sql.*;
import java.util.Properties;

/**
 * Created by han on 2017/3/3.
 */
public class Mysql {

    Properties pro;

    Connection conn;
    public Mysql(){
        pro = Common.getConfig("mysql.properties");

    }

    public ResultSet select(String sql){
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("user"), pro.getProperty("pass"));
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static void main(String args[]) throws SQLException {
        ResultSet rs = new Mysql().select("select * from project");
        while (rs.next()){
            rs.getString("name");
        }
    }



}
