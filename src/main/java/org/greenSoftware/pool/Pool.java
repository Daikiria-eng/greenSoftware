package org.greenSoftware.pool;

import java.beans.Statement;
import java.io.Closeable;
/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Properties;

import org.greenSoftware.constants.Constants;

public class Pool {

    public static Connection getConnection(){
        //Properties poolConfig=new Properties();
        //
        //poolConfig.load(ClassLoader.getSystemResourceAsStream("environ.properties"));
        /*try{
            //poolConfig.load(ClassLoader.getSystemResourceAsStream("environ.properties"));
            poolConfig.load(new FileInputStream("/home/enikyasta/Documentos/NetBeansProjects/testingTest/src/main/resources"));
        }catch(Exception e){
            System.out.println("ERror localizando properties:\n"+e+"\n");
            e.printStackTrace();
        }*/

        //System.out.printf("pille pues el recurso, o la propiedad %s",poolConfig.getProperty("jdbc.engine"));

        /*final String urlConnection="jdbc:"+poolConfig.getProperty("jdbc.engine")+"://"
            +poolConfig.getProperty("jdbc.host")
            +":"+poolConfig.getProperty("jdbc.port")
            +"/"+poolConfig.getProperty("jdbc.database");*/

        final String urlConnection="jdbc:"+Constants.JDBC_DB_ENGINE
            +"://"+Constants.JDBC_DB_HOST
            +":"+Constants.JDBC_DB_PORT
            +"/"+Constants.JDBC_DB_NAME;
        Connection conn=null;

        try{
            Class.forName(Constants.JDBC_PG_DRIVER);
            conn=DriverManager.getConnection(urlConnection,Constants.JDBC_PG_USER,Constants.JDBC_PG_PASS);
            
            return conn;
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }

    public static void close(Statement st){
        try {
            ((Closeable) st).close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    } 
}