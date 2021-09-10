/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.ClsAlumno;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ClsAlumnoJDBC {
    
    private static final String SQL_SELECT = "select * from db_alumnos1";
    
    private static final String SQL_INSERT = "insert into db_alumnos1(nombre,nota1,nota2) values (?,?,?)";
    
    private static final String SQL_UPDATE = "update db_alumnos1 set nombre =?,nota1=?,nota2=? where codigo=?";
    //hacer el delete de tarea.
    private static final String SQL_DELETE = "delete from db_alumnos1 where codigo=?";
    
    //Seleccionar informacion.
    public List<ClsAlumno> seleccion (){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsAlumno alumno = null;
        List<ClsAlumno> alumnos = new ArrayList<ClsAlumno>();
        
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                int nota1 = rs.getInt("nota1");
                int nota2 = rs.getInt("nota2");
                alumno = new ClsAlumno();
                alumno.setCodigo(codigo);
                alumno.setNombre(nombre);
                alumno.setNota1(nota1);
                alumno.setNota2(nota2);
                alumnos.add(alumno);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return alumnos;
        
    }
    
    //insertar informacion a la tabla
    public int insert(ClsAlumno alumno){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, alumno.getNombre());
            stmt.setInt(2, alumno.getNota1());
            stmt.setInt(3, alumno.getNota2());
         
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
    
    public int borrar(ClsAlumno alumno){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn=ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, alumno.getCodigo());
//            stmt.setInt(2, alumno.getNota1());
//            stmt.setInt(3, alumno.getNota2());
            System.out.println("Ejecutando query: "+SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;//wachar esto que esta con un cero
    }
    
    public int actualizar(ClsAlumno alumno){
        
        Connection conn = null;
        PreparedStatement stmt = null;
//        ResultSet rs = null;
        int rows = 0;
        
//        List <ClsAlumno> alumnos = new ArrayList<ClsAlumno>();
        
        try {
            
            conn=ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, alumno.getNombre());
            stmt.setInt(2, alumno.getNota1());
            stmt.setInt(3, alumno.getNota2());
            //stmt.setInt(5, alumno.getCodigo());
            //rs = stmt.executeQuery();
            stmt.setInt(4, alumno.getCodigo());
//            
//            while(rs.next()){
//                int codigo = rs.getInt("codigo");
//                String nombre = rs.getString("nombre");
//                int nota1 = rs.getInt("nota1");
//                int nota2 = rs.getInt("nota2");
//                alumno = new ClsAlumno();
//                alumno.setCodigo(codigo);
//                alumno.setNombre(nombre);
//                alumno.setNota1(nota1);
//                alumno.setNota2(nota2);
//                alumnos.add(alumno);
//                
//            }
            System.out.println("Ejecutando query: "+SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
        finally{//no se si esto del finally aca es necesario
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
    
}
