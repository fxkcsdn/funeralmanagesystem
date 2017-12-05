package com.FuneralManage.Dao;

import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
import java.math.BigDecimal;
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;

import com.FuneralManage.Utility.*;
  
public class BaseDAO {  
    private Connection conn = null;  
    private PreparedStatement ps = null;  
    private ResultSet rs = null;  
    
    private DataSource dataSource;
    private ConnectionHolder connectionHolder=new ConnectionHolder();

    public BaseDAO(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    /** 
     * 查询符合条件的记录数 
     *  
     * @param sql 
     *            要执行的sql语句 
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 符合条件的记录数 
     */  
    public long  getCount(String sql, Object... args) {  
        conn = getConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            if (rs.next()) {  
                return rs.getLong(1);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {
            close(conn,ps,rs);
        }  
        return 0L;  
    }  
    /** 
     * 查询符合条件一个字段值；
     *  
     * @param sql 
     *            要执行的sql语句例如select name from table where dealerNumber=? and number=?
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 符合条件的记录数 
     */  
    public String getOneColumn(String sql, Object... args) {  
        conn = getConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            if (rs.next()) {  
                return rs.getString(1);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {
            close(conn,ps,rs);
        }  
        return ""; 
    } 
  
    /** 
     * 查询实体对象的，并封装到Json中
     * 
     * @param sql 
     *            要执行的sql语句
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     *            
     * @return 要查询的类的集合，返回Json
     */  
    public String getForJson(String sql, Object... args) {  
        conn = getConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
         // json数组  
            JSONArray array = new JSONArray();  
             
            // 获取列数  
            ResultSetMetaData metaData = rs.getMetaData();  
            int columnCount = metaData.getColumnCount();  
             
            // 遍历ResultSet中的每条数据  
             while (rs.next()) {  
                 JSONObject jsonObj = new JSONObject();  
                  
                 // 遍历每一列  
                 for (int i = 1; i <= columnCount; i++) {  
                     String columnName =metaData.getColumnLabel(i);  
                     String value = rs.getString(columnName);
                     if(value==null)
                    	 jsonObj.put(columnName, "");
                     else
                    	 jsonObj.put(columnName, value); 
                 }   
                 array.put(jsonObj);   
             }  
             System.out.println(columnCount);
            return array.toString(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            close(conn, ps, rs);
        }  
        return null;  
    } 
    
    
    
    /** 
     * 查询实体对象，并将查询封装到一个List中
     * 
     * @param sql 
     *            要执行的sql语句
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     *            
     * @return 要查询的类的集合，返回List,无结果时返回null 
     */  
    public List<Map<String, Object>> getListForMap(String sql, Object... args) {  
        conn = getConnection(dataSource);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数 
            while (rs.next()) {
              Map<String,Object> rowData = new HashMap<String,Object>();
              for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
              }
              list.add(rowData);
            }
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            close(conn, ps, rs);
        }  
        return null;  
    }
    
    /** 
     * 查询实体对象的 
     *  
     * @param <T> 
     *            要查询的对象的集合 
     * @param sql 
     *            要执行的sql语句 
     * @param clazz 
     *            要查询的对象的类型 
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 要查询的实体，无结果时返回null 
     */  
    public <T> T getForEntity(String sql, Class<T> clazz, Object... args) {  
        conn = getConnection(dataSource);
        T t=null;
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            Field[] fs = clazz.getDeclaredFields();  
            String[] colNames = new String[fs.length];  
            String[] rTypes = new String[fs.length];  
            Method[] methods = clazz.getMethods();  
            while (rs.next()) {  
                for (int i = 0; i < fs.length; i++) {  
                    Field f = fs[i];  
                    String colName = f.getName().substring(0, 1).toUpperCase()  
                            + f.getName().substring(1);  
                    colNames[i] = colName;  
                    String rType = f.getType().getSimpleName();  
                    rTypes[i] = rType;  
                }  
  
                t = (T) clazz.newInstance();  
                for (int i = 0; i < colNames.length; i++) {  
                    String colName = colNames[i];  
                    String methodName = "set" + colName;  
                    // 查找并调用对应的setter方法赋  
                    for (Method m : methods) {  
                        if (methodName.equals((m.getName()))) {  
                            // 如果抛了参数不匹配异常，检查JavaBean中该属性类型，并添加else分支进行处理  
                            if ("int".equals(rTypes[i])  
                                    || "Integer".equals(rTypes[i])) {  
                                m.invoke(t, rs.getInt(colName));  
                            }  else if ("Timestamp".equals(rTypes[i])) {  
                                m.invoke(t, rs.getTimestamp(colName));  
                            } else {  
                                m.invoke(t, rs.getObject(colName));  
                            }  
                            break;  
                        }  
                    }  
                }    
            }
            return t;  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	close(conn, ps, rs);  
        }  
        return null;  
    }
    
    
    
    /** 
     * 查询实体对象的，并封装到一个集合 
     *  
     * @param <T> 
     *            要查询的对象的集合 
     * @param sql 
     *            要执行的sql语句 
     * @param clazz 
     *            要查询的对象的类型 
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 要查询的类的集合，无结果时返回null 
     */  
    public <T> List<T> getListForEntity(String sql, Class<T> clazz, Object... args) {  
        conn = getConnection(dataSource);  
        List list = new ArrayList();  
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            Field[] fs = clazz.getDeclaredFields();  
            String[] colNames = new String[fs.length];  
            String[] rTypes = new String[fs.length];  
            Method[] methods = clazz.getMethods();  
            while (rs.next()) {  
                for (int i = 0; i < fs.length; i++) {  
                    Field f = fs[i];  
                    String colName = f.getName().substring(0, 1).toUpperCase()  
                            + f.getName().substring(1);  
                    colNames[i] = colName;  
                    String rType = f.getType().getSimpleName();  
                    rTypes[i] = rType;  
                }  
  
                Object object = (T) clazz.newInstance();  
                for (int i = 0; i < colNames.length; i++) {  
                    String colName = colNames[i];  
                    String methodName = "set" + colName;  
                    // 查找并调用对应的setter方法赋  
                    for (Method m : methods) {  
                        if (methodName.equals((m.getName()))) {  
                            // 如果抛了参数不匹配异常，检查JavaBean中该属性类型，并添加else分支进行处理  
                            if ("int".equals(rTypes[i])  
                                    || "Integer".equals(rTypes[i])) {  
                                m.invoke(object, rs.getInt(colName));  
                            } else if ("Date".equals(rTypes[i])) {  
                                m.invoke(object, rs.getDate(colName));  
                            } else if ("Timestamp".equals(rTypes[i])) {  
                                m.invoke(object, rs.getTimestamp(colName));  
                            } else {  
                                m.invoke(object, rs.getObject(colName));  
                            }  
                            break;  
                        }  
                    }  
                }  
                list.add(object);  
            }  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	close(conn, ps, rs);  
        }  
        return null;  
    }
    /** 
     * 以对象的形式保存或更新一个实体 
     *  
     * @param sql 
     *            要执行的sql语句 
     * @param object 
     *            要保存或更新的实体对象 
     * @param args 
     *            不需要赋值的列标组成的数组，例如sql语句 
     *            "insert into tbl_user values(seq_user.nextval,?,?,?)"应为1 
     * @return 操作结果，1 成功，0 失败 
     */  
    public int saveEntity(String sql, Object object, int... args) {  
        conn = getConnection(dataSource);  
        try {  
            ps = conn.prepareStatement(sql);  
            Class c = object.getClass();  
            Field[] fields = object.getClass().getDeclaredFields();  
            int temp = 1;// 正赋值的？的下标，最大下标为args的长度  
            int colIndex = 1;// SQL语句中的当前字段下标  
            int t = 0;// args数组的下标  
            for (int j = 0; j < fields.length; j++) {  
                Field field = fields[j];// 得到某个声明属性  
                String methodName = "get"  
                        + field.getName().substring(0, 1).toUpperCase()  
                        + field.getName().substring(1);  
                Method method = c.getMethod(methodName);// 得到了当前类中的一个method  
                String rType = field.getType().getSimpleName().toString();  
                if (t < args.length && colIndex == args[t]) {  
                    t++;  
                } else if ("int".equals(rType) || "INTEGER".equals(rType)) {  
                    ps.setInt(temp++, (Integer) method.invoke(object));  
                } else {  
                    ps.setObject(temp++, method.invoke(object));  
                }  
                colIndex++;// 更新索引下标  
            }  
            return ps.executeUpdate();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            close(conn, ps, null);  
        }  
        return 0;  
    }  
  
    /** 
     * 执行可变参数的SQL语句，进行保存、删除或更新操作 
     *  
     * @param sql 
     *            要执行的sql语句，?的赋值顺序必须与args数组的顺序相同 
     * @param args 
     *            要赋值的参数列表 
     * @return 操作结果，正数 成功，0 失败 
     */  
    public int saveOrUpdateOrDelete(String sql, Object... args) {  
        conn = getConnection(dataSource);  
        try {  
            ps = conn.prepareStatement(sql);  
            for (int j = 0; j < args.length; j++) {  
                ps.setObject(j + 1, args[j]);  
            }  
            return ps.executeUpdate();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            close(conn, ps, null);  
        }  
        return 0;  
    }
    
    /***********************************执行事务的方法*******************************/
    /** 
     * 查询符合条件的记录数 （执行事务）
     *  
     * @param sql 
     *            要执行的sql语句 
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 符合条件的记录数 
     */  
    public long getCountTran(String sql, Object... args) {  
        conn = getSingleThreadConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            if (rs.next()) {  
                return rs.getLong(1);  
            }
            else
            {
            	return 0L;
            }
        } catch (SQLException e) {  
        	e.printStackTrace();
        	throw new RuntimeException("Couldn't close connection[" + conn + "].", e);  
        }  
    }  
    /** 
     * 查询符合条件一个字段值；(事务)
     *  
     * @param sql 
     *            要执行的sql语句例如select name from table where dealerNumber=? and number=?
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 符合条件的记录数 
     */  
    public String getOneColumnTran(String sql, Object... args) {  
    	conn = getSingleThreadConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            if (rs.next()) {  
                return rs.getString(1);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
            throw new RuntimeException("Couldn't close connection[" + conn + "].", e); 
        }
        return ""; 
    } 
  
    /** 
     * 查询实体对象的，并封装到Json中（执行事务）
     * 
     * @param sql 
     *            要执行的sql语句
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     *            
     * @return 要查询的类的集合，返回Json
     */  
    public String getForJsonTran(String sql, Object... args) {  
        conn = getSingleThreadConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
         // json数组  
            JSONArray array = new JSONArray();  
             
            // 获取列数  
            ResultSetMetaData metaData = rs.getMetaData();  
            int columnCount = metaData.getColumnCount();  
             
            // 遍历ResultSet中的每条数据  
             while (rs.next()) {  
                 JSONObject jsonObj = new JSONObject();  
                  
                 // 遍历每一列  
                 for (int i = 1; i <= columnCount; i++) {  
                     String columnName =metaData.getColumnLabel(i);
                     //String columnType=metaData.getColumnTypeName(i);
                    // System.out.println("此字段类型："+columnType);
                     String value = rs.getString(columnName); 
                     if(value==null)
                    	 jsonObj.put(columnName, "");
                     else
                    	 jsonObj.put(columnName, value);  
                 }   
                 array.put(jsonObj);   
             }  
             
            return array.toString(); 
        } catch (Exception e) {  
        	e.printStackTrace();
        	throw new RuntimeException("Couldn't close connection[" + conn + "].", e);  
        } 
    }  
    
    
    /** 
     * 查询实体对象，并将查询封装到一个List中（执行事务）
     * 
     * @param sql 
     *            要执行的sql语句
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     *            
     * @return 要查询的类的集合，返回List,无结果时返回null 
     */  
    public List<Map<String, Object>> getListForMapTran(String sql, Object... args) {  
        conn = getSingleThreadConnection(dataSource);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数 
            while (rs.next()) {
              Map<String,Object> rowData = new HashMap<String,Object>();
              for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
              }
              list.add(rowData);
            }
            return list;  
        } catch (Exception e) { 
        	e.printStackTrace();
        	throw new RuntimeException("Couldn't close connection[" + conn + "].", e);
        }
 
    }
    
    /** 
     * 查询实体对象的 
     *  
     * @param <T> 
     *            要查询的对象的集合 
     * @param sql 
     *            要执行的sql语句 
     * @param clazz 
     *            要查询的对象的类型 
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 要查询的实体，无结果时返回null 
     */  
    public <T> T getForEntityTran(String sql, Class<T> clazz, Object... args) {  
    	conn = getSingleThreadConnection(dataSource);
    	T t=null;
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            Field[] fs = clazz.getDeclaredFields();  
            String[] colNames = new String[fs.length];  
            String[] rTypes = new String[fs.length];  
            Method[] methods = clazz.getMethods();  
            while (rs.next()) {  
                for (int i = 0; i < fs.length; i++) {  
                    Field f = fs[i];  
                    String colName = f.getName().substring(0, 1).toUpperCase()  
                            + f.getName().substring(1);  
                    colNames[i] = colName;  
                    String rType = f.getType().getSimpleName();  
                    rTypes[i] = rType;  
                }  
  
                t = (T) clazz.newInstance();  
                for (int i = 0; i < colNames.length; i++) {  
                    String colName = colNames[i];  
                    String methodName = "set" + colName;  
                    // 查找并调用对应的setter方法赋  
                    for (Method m : methods) {  
                        if (methodName.equals((m.getName()))) {  
                            // 如果抛了参数不匹配异常，检查JavaBean中该属性类型，并添加else分支进行处理  
                            if ("int".equals(rTypes[i])  
                                    || "Integer".equals(rTypes[i])) {  
                                m.invoke(t, rs.getInt(colName));  
                            } else if ("Date".equals(rTypes[i])) {  
                                m.invoke(t, rs.getDate(colName));  
                            } else if ("Timestamp".equals(rTypes[i])) {  
                                m.invoke(t, rs.getTimestamp(colName));  
                            } else {  
                                m.invoke(t, rs.getObject(colName));  
                            }  
                            break;  
                        }  
                    }  
                }    
            }  
            return t;  
        } catch (Exception e) {  
        	e.printStackTrace();
        	throw new RuntimeException("Couldn't close connection[" + conn + "].", e);
        }  
        
    }
    
    /** 
     * 查询实体对象的，并封装到一个集合 （执行事务）
     *  
     * @param <T> 
     *            要查询的对象的集合 
     * @param sql 
     *            要执行的sql语句 
     * @param clazz 
     *            要查询的对象的类型 
     * @param args 
     *            给sql语句中的？赋值的参数列表 
     * @return 要查询的类的集合，无结果时返回null 
     */  
    public <T> List<T> getListForEntityTran(String sql, Class<T> clazz, Object... args) {  
        conn = getSingleThreadConnection(dataSource);  
        List list = new ArrayList();  
        try {  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++) {  
                ps.setObject(i + 1, args[i]);  
            }  
            rs = ps.executeQuery();  
            Field[] fs = clazz.getDeclaredFields();  
            String[] colNames = new String[fs.length];  
            String[] rTypes = new String[fs.length];  
            Method[] methods = clazz.getMethods();  
            while (rs.next()) {  
                for (int i = 0; i < fs.length; i++) {  
                    Field f = fs[i];  
                    String colName = f.getName().substring(0, 1).toUpperCase()  
                            + f.getName().substring(1);  
                    colNames[i] = colName;  
                    String rType = f.getType().getSimpleName();  
                    rTypes[i] = rType;  
                }  
  
                Object object = (T) clazz.newInstance();  
                for (int i = 0; i < colNames.length; i++) {  
                    String colName = colNames[i];  
                    String methodName = "set" + colName;  
                    // 查找并调用对应的setter方法赋  
                    for (Method m : methods) {  
                        if (methodName.equals((m.getName()))) {  
                            // 如果抛了参数不匹配异常，检查JavaBean中该属性类型，并添加else分支进行处理  
                            if ("int".equals(rTypes[i])  
                                    || "Integer".equals(rTypes[i])) {  
                                m.invoke(object, rs.getInt(colName));  
                            } else if ("Date".equals(rTypes[i])) {  
                                m.invoke(object, rs.getDate(colName));  
                            } else if ("Timestamp".equals(rTypes[i])) {  
                                m.invoke(object, rs.getTimestamp(colName));  
                            } else {  
                                m.invoke(object, rs.getObject(colName));  
                            }  
                            break;  
                        }  
                    }  
                }  
                list.add(object);  
            }  
            return list;  
        } catch (Exception e) {  
        	e.printStackTrace();
        	throw new RuntimeException("Couldn't close connection[" + conn + "].", e); 
        } 
         
    }
    /** 
     * 以对象的形式保存或更新一个实体 （执行事务）
     *  
     * @param sql 
     *            要执行的sql语句 
     * @param object 
     *            要保存或更新的实体对象 
     * @param args 
     *            不需要赋值的列标组成的数组，例如sql语句 
     *            "insert into tbl_user values(seq_user.nextval,?,?,?)"应为1 
     * @return 操作结果，1 成功，0 失败 
     */  
    public int saveEntityTran(String sql, Object object, int... args) {  
        conn = getSingleThreadConnection(dataSource);  
        try {  
            ps = conn.prepareStatement(sql);  
            Class c = object.getClass();  
            Field[] fields = object.getClass().getDeclaredFields();  
            int temp = 1;// 正赋值的？的下标，最大下标为args的长度  
            int colIndex = 1;// SQL语句中的当前字段下标  
            int t = 0;// args数组的下标  
            for (int j = 0; j < fields.length; j++) {  
                Field field = fields[j];// 得到某个声明属性  
                String methodName = "get"  
                        + field.getName().substring(0, 1).toUpperCase()  
                        + field.getName().substring(1);  
                Method method = c.getMethod(methodName);// 得到了当前类中的一个method  
                String rType = field.getType().getSimpleName().toString();  
                if (t < args.length && colIndex == args[t]) {  
                    t++;  
                } else if ("int".equals(rType) || "INTEGER".equals(rType)) {  
                    ps.setInt(temp++, (Integer) method.invoke(object));       
                } else if ("BigDecimal".equals(rType)) {  
                    ps.setBigDecimal(temp++, (BigDecimal) method.invoke(object));       
                }  else {  
                    ps.setObject(temp++, method.invoke(object));  
                }  
                colIndex++;// 更新索引下标  
            }  
            int result=0;
            result=ps.executeUpdate();  
            return result;
        } catch (Exception e) {  
        	e.printStackTrace();
        	throw new RuntimeException(e.getMessage().toString());  
        } 
    }  
    /** 
     * 执行可变参数的SQL语句，进行保存、删除或更新操作 （执行事务）
     *  
     * @param sql 
     *            要执行的sql语句，?的赋值顺序必须与args数组的顺序相同 
     * @param args 
     *            要赋值的参数列表 
     * @return 操作结果，正数 成功，0 失败 
     */  
    public int saveOrUpdateOrDeleteTran(String sql, Object... args) {  
        conn = getSingleThreadConnection(dataSource);
        try {  
            ps = conn.prepareStatement(sql);  
            for (int j = 0; j < args.length; j++) {  
                ps.setObject(j + 1, args[j]);  
            }  
            return ps.executeUpdate();  
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException("Couldn't close connection[" + conn + "].", e);
        }
    }

    /**
     *从ConnectionHolder中获取连接
     */
	private Connection getConnection(DataSource datasource)
	{
		Connection connection=null;
		try {
			connection= connectionHolder.getConnection(dataSource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	/**
     *关闭连接
     */
    private void close(Connection conn,PreparedStatement ps,ResultSet rs)
    {
    	try {
			if(conn!=null)
				conn.close();
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	connectionHolder.removeConnection(dataSource);
    }
    /**
     * 从线程安全的SingleThreadConnectionHolder类中获取Connection对象
     */
    private Connection getSingleThreadConnection(DataSource datasource)
	{
		Connection connection=null;
		try {
			connection= SingleThreadConnectionHolder.getConnection(dataSource);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	} 
    //我再这里添加了代码111111111111111111111
    
}  