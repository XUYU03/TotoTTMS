package xupt.se.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DBUtil
{
    private final String dbConnFile="jdbc.properties";

    private Connection conn=null;
    private String dbDriver; // 定义驱动
    private String dbURL; // 定义URL
    private String userName; // 定义用户名
    private String password; // 定义密码

    // 从配置文件取数据库链接参数
    private void loadConnProperties()
    {
        Properties props=new Properties();
        try
        {
            // 根据配置文件路径Conf加载配置文件
        	props.load(this.getClass().getClassLoader().getResourceAsStream(dbConnFile));
            
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        // 从配置文件中取得相应的参数并设置类变量
        this.dbDriver=props.getProperty("driver");
        this.dbURL=props.getProperty("url");
        this.userName=props.getProperty("username");
        this.password=props.getProperty("password");

    }

    public boolean openConnection()
    {
        try
        {
            loadConnProperties();

            Class.forName(dbDriver);

            this.conn=DriverManager.getConnection(dbURL, userName, password);
            return true;
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            classnotfoundexception.printStackTrace();
            System.err.println("db: " + classnotfoundexception.getMessage());
        }
        catch(SQLException sqlexception)
        {
            System.err.println("err code:" + sqlexception.getErrorCode());

        }
        return false;
    }

    protected void finalize() throws Exception
    {
        try
        {
            if(null != conn)
                conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    // 查询并得到结果集
    public ResultSet execQuery(String sql) throws Exception
    {
        ResultSet rstSet=null;
        try
        {
            if(null == conn)
                throw new Exception("Database not connected!");
            Statement stmt=conn.createStatement();
            rstSet=stmt.executeQuery(sql);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return rstSet;
    }

    // 插入一条新纪录，并获取标识列的值
    public ResultSet getInsertObjectIDs(String insertSql) throws Exception
    {
        ResultSet rst=null;
        try
        {
            if(null == conn)
                throw new Exception("Database not connected!");

            Statement stmt=conn.createStatement();

            stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
            rst=stmt.getGeneratedKeys();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return rst;
    }

    public ResultSet getInsertObjectIDsT(List<String> list) throws Exception
    {
        ResultSet rst=null;
        try
        {
            if(null == conn)
                throw new Exception("Database not connected!");

            Statement stmt=conn.createStatement();
            conn.setAutoCommit(false);
            for(int i=0; i < list.size(); i++)
            {
                stmt.executeUpdate(list.get(i), Statement.RETURN_GENERATED_KEYS);

            }
            conn.commit();// 提交JDBC事务
            conn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
            rst=stmt.getGeneratedKeys();
            return rst;

        }
        catch(SQLException e)
        {
            conn.rollback();
            e.printStackTrace();
            return null;
        }

    }

    // 插入、更新、删除
    public int execCommand(String sql) throws Exception
    {
        int flag=0;
        try
        {
            if(null == conn)
                throw new Exception("Database not connected!");

            Statement stmt=conn.createStatement();
            flag=stmt.executeUpdate(sql);

            stmt.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    // 释放资源
    public void close(ResultSet rst) throws Exception
    {
        try
        {
            Statement stmt=rst.getStatement();
            rst.close();
            stmt.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    // 释放资源
    public void close(Statement stmt) throws Exception
    {
        try
        {
            stmt.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    // 释放资源
    public void close() throws SQLException, Exception
    {
        if(null != conn)
        {
            conn.close();
            conn=null;
        }
    }

}
