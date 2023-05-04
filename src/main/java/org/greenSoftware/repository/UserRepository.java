package org.greenSoftware.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.dto.UserResponseDTO;
import org.greenSoftware.pool.Pool;
import org.greenSoftware.util.Util;
import com.auth0.jwt.algorithms.Algorithm;

public class UserRepository {
    public int insertUser(UserDTO user){
        Connection conn=null;
        CallableStatement call=null;

        try{
            conn=Pool.getConnection();
            conn.setAutoCommit(true);
            call=conn.prepareCall("{ ? = call public.insert_user(gen_random_uuid(),?,?,?) }");
            call.registerOutParameter(1,Types.INTEGER);
            call.setString(2,user.getName());
            call.setString(3,user.getEmail());
            call.setString(4,user.getPassword());
            call.execute();
            
            return call.getInt(1);
        }catch(Exception e){
            System.out.println("[+] Error inserting");
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                call.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return 0;
    } 
    
    public UserResponseDTO validateUser(UserDTO user){
        Connection conn=null;
        Statement call=null;
        ResultSet rs=null;
        String result="";

        try {
            conn=Pool.getConnection();
            conn.setAutoCommit(false);
            call=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=call.executeQuery("SELECT validate_user('"+user.getEmail()+"','"+user.getPassword()+"')");
            if(rs.next()){
                result=rs.getString(1);
                result=result.substring(1, result.length()-1);
                String splited[]=result.split(",");
                user.setName(splited[0]);
                user.setLevel(Integer.valueOf(splited[1]));
                user.setPassword(null);
                
                String token=Util.createJWT(user, Algorithm.HMAC256("daSecret"));

                return new UserResponseDTO(user, true, token);
            }else return new UserResponseDTO(user, false, null);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                call.close();
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}