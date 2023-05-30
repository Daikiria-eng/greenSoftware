package org.greenSoftware.repository;

import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.greenSoftware.dto.ModuleContentDTO;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dto.QuestionDTO;
import org.greenSoftware.pool.Pool;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModuleRepository {
    ObjectMapper objMapper=new ObjectMapper();

    public List<ModuleDTO> getAllModules() {
        Connection conn=null;
        ResultSet rs=null;
        Statement st=null;
        String queryString="SELECT * FROM modules";
        List<ModuleDTO> result=new ArrayList<ModuleDTO>();
        
        try{
            conn=Pool.getConnection();
            st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=st.executeQuery(queryString);
            rs.first();

            do{
                ModuleDTO tempModule=new ModuleDTO(
                    rs.getString("module_name"),
                    rs.getString("description"),
                    objMapper.readValue(rs.getString("exam_content"),new TypeReference<List<QuestionDTO>>(){})
                );
                result.add(tempModule);
            }while(rs.next());
            
            return result;
        }catch(Exception e){
            System.out.println("ERror al obtener modulos");
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    public ModuleDTO getModule(ModuleDTO module){
        final String getModuleQuery="SELECT module_name,description FROM modules WHERE module_name='"+module.getName()+"';";
        Connection conn=null;
        ResultSet rs=null;
        Statement st=null;
        
        try{
            conn=Pool.getConnection();
            st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=st.executeQuery(getModuleQuery);
            if(rs.next()){
                ModuleDTO result=new ModuleDTO(
                    rs.getString("module_name"),
                    rs.getString("description"),
                    null
                );

                return result;
            } else return new ModuleDTO(null,null,null);

        }catch(Exception e){
            System.out.println("ERror getting specific module: "+e);
        }finally{
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    public List<QuestionDTO> getQuestions(ModuleDTO module){
        CallableStatement call=null;
        Connection conn=null;

        try{
            conn=Pool.getConnection();
            call=conn.prepareCall("{ ? = call get_questions(?) }");
            call.registerOutParameter(1,12);
            call.setString(2, module.getName());
            call.execute();
            

            return objMapper.readValue(call.getString(1),new TypeReference<List<QuestionDTO>>(){});
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                call.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
    public ModuleContentDTO getContent(ModuleDTO module){
        Connection conn=null;
        CallableStatement call=null;
        
        try{
            conn=Pool.getConnection();
            call=conn.prepareCall("{ call get_content(?,?,?,?,?) }");
            call.setString(1,module.getName());
            call.registerOutParameter(2,Types.VARCHAR);
            call.registerOutParameter(3,Types.VARCHAR);
            call.registerOutParameter(4,Types.VARCHAR);
            call.registerOutParameter(5,Types.INTEGER);
            call.execute();
            
            return new ModuleContentDTO(
                call.getString(2),
                call.getString(3),
                call.getString(4),
                call.getInt(5)
            );
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }finally{
            try{
                call.close();
                conn.close();
            }catch(SQLException e){
                e.getMessage();
            }
        }

        return null;
    }
}