package com.interfacetest.util;

/**
 * Created by han on 2017/4/10.
 */
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class JdbcUtils {
    //数据库用户名
    private static String USERNAME = null;
    //数据库密码
    private static String PASSWORD = null;
    //数据库地址
    private static String URL = null;
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public JdbcUtils(){
        Properties pro = Common.getConfig("mysql.properties");
        URL = pro.getProperty("url");
        USERNAME = pro.getProperty("user");
        PASSWORD = pro.getProperty("pass");
    }

    public static void setUSERNAME(String USERNAME) {
        JdbcUtils.USERNAME = USERNAME;
    }

    public static void setPASSWORD(String PASSWORD) {
        JdbcUtils.PASSWORD = PASSWORD;
    }

    public static void setURL(String URL) {
        JdbcUtils.URL = URL;
    }

    /**
     * 获得数据库的连接
     * @return
     */
    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 增加、删除、改
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<Object>params)throws SQLException{
        boolean flag = false;
        int result = -1;
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        if(params != null && !params.isEmpty()){
            for(int i=0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 查询单条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException{
        Map<String, Object> map = new HashMap<String, Object>();
        int index  = 1;
        pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i=0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();//返回查询结果
        while(resultSet.next()){
            map = add(map);
        }
        return map;
    }

    /**
     * 给map里增加一行查询结果  转成map
     * @param map
     * @return
     * @throws SQLException
     */
    private Map<String, Object> add(Map<String, Object> map) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();

        for(int i=0; i<cols_len; i++ ){
            String cols_name = metaData.getColumnName(i+1);
            Object cols_value = resultSet.getObject(cols_name);
            if(cols_value == null){
                cols_value = "";
            }
            map.put(cols_name, cols_value);
        }
        return map;
    }

    /**
     * 把查询结果转成对象
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> T getObject(Class<T> cls ) throws Exception {
        T resultObject = null;
        ResultSetMetaData metaData  = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        //通过反射机制创建一个实例
        resultObject = cls.newInstance();
        for(int i = 0; i<cols_len; i++){
            String cols_name = metaData.getColumnName(i+1);
            Object cols_value = resultSet.getObject(cols_name);
            if(cols_value == null){
                cols_value = "";
            }
            Field field = cls.getDeclaredField(cols_name);
            field.setAccessible(true); //打开javabean的访问权限
            field.set(resultObject, cols_value);
        }
        return resultObject;
    }

    /**查询多条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        while(resultSet.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            map = add(map);
            list.add(map);
        }

        return list;
    }

    /**通过反射机制查询单条记录
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> T findSimpleRefResult(String sql, List<Object> params,
                                     Class<T> cls )throws Exception{
        T resultObject = null;
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData  = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while(resultSet.next()){
            resultObject = getObject(cls);
        }
        return resultObject;

    }

    /**通过反射机制查询多条记录
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> List<T> findMoreRefResult(String sql, List<Object> params,
                                         Class<T> cls )throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        while(resultSet.next()){
            list.add(getObject(cls));
        }
        return list;
    }

    /**
     * 释放数据库连接
     */
    public void releaseConn(){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();

        /*******************增*********************/
        /*      String sql = "insert into userinfo (username, pswd) values (?, ?), (?, ?), (?, ?)";
        List<Object> params = new ArrayList<Object>();
        params.add("小明");
        params.add("123xiaoming");
        params.add("张三");
        params.add("zhangsan");
        params.add("李四");
        params.add("lisi000");
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


        /*******************删*********************/
        //删除名字为张三的记录
        /*      String sql = "delete from userinfo where username = ?";
        List<Object> params = new ArrayList<Object>();
        params.add("小明");
        boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);*/

        /*******************改*********************/
        //将名字为李四的密码改了
        /*      String sql = "update userinfo set pswd = ? where username = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add("lisi88888");
        params.add("李四");
        boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
        System.out.println(flag);*/

        /*******************查*********************/
        //不利用反射查询多个记录
        /*      String sql2 = "select * from userinfo ";
        List<Map<String, Object>> list = jdbcUtils.findModeResult(sql2, null);
        System.out.println(list);*/

        //利用反射查询 单条记录
        String sql = "select * from userinfo where username = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add("李四");
//        UserInfo userInfo;
        try {
//            userInfo = jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
//            System.out.print(userInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
