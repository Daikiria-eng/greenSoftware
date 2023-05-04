package org.greenSoftware.repository;

import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dto.QuestionDTO;
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
        final String getModuleQuery="SELECT * FROM modules WHERE module_name='"+module.getName()+"';";
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
                    rs.getString("exam_content")
                );

                return result;
            } else return new ModuleDTO("","","");

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
    
    public QuestionDTO getQuestions(ModuleDTO module){
        ResultSet rs=null;
        CallableStatement call=null;
        /*Statement st=null;
        String[] questions={"",""};

        try (Connection conn=Pool.getConnection()) {
            st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=st.executeQuery("SELECT get_questions('"+module.getName()+"')");
            if(rs.next()){
                Pattern pattern=Pattern.compile("\"\"");
                String result=rs.getString(1);
                Matcher matcher=pattern.matcher(result.substring(1, result.length()-1));
                result=matcher.replaceAll("\"");
                int cont=0;
                for(int i=0;i<result.length();i++){
                    if((""+result.charAt(i)).equals(","))
                        cont++;
                    if(cont==2){
                        i++;
                        questions[1]=result.substring(i, result.length()-1);
                        break;
                    }
                    questions[0]+=""+result.charAt(i);
                }
                System.out.printf("[ %s ],[ %s ]",questions[0],questions[1]);
            }

        } catch (Exception e) {
            System.out.println("[-] ERror getting questions: "+e);
            e.printStackTrace();
        }*/
        try(Connection conn=Pool.getConnection()){
            call=conn.prepareCall("{ call get_questions(?,?,?) }");
            call.setString(1, module.getName());
            call.registerOutParameter(2,12);
            call.registerOutParameter(3,12);
            call.execute();

            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                call.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;
    }
    
    public static void main(String[] args) {
        ModuleRepository mr=new ModuleRepository();
        mr.getQuestions(new ModuleDTO("primer_modulo", "", ""));
    }
}