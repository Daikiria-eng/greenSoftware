package org.greenSoftware.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.pool.Pool;

public class ModuleRepository {
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
                    rs.getString("exam_content")
                );
                result.add(tempModule);
            }while(rs.next());
            
            return result;
        }catch(Exception e){
            System.out.println("ERror al obtener modulos");
            e.printStackTrace();
        }finally{
            try {rs.close();} catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
            try {st.close();} catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
            try {conn.close();} catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    public ModuleDTO getModule(ModuleDTO module){
        final String getModuleQuery="SELECT * FROM modules WHERE module_name='"+module.getModuleName()+"';";
        Connection conn=null;
        ResultSet rs=null;
        Statement st=null;
        
        try{
            conn=Pool.getConnection();
            st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=st.executeQuery(getModuleQuery);
            rs.next();
            ModuleDTO result=new ModuleDTO(
                rs.getString("module_name"),
                rs.getString("description"),
                rs.getString("exam_content")
            );

            return result;
        }catch(Exception e){
            System.out.println("ERror getting specific module: "+e);
        }finally{
            try {rs.close();} catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
            try {st.close();} catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
            try {conn.close();} catch (SQLException e) {
                System.out.println("Error closing resource: "+e);
                e.printStackTrace();
            }
        }
        
        return null;
    }
}