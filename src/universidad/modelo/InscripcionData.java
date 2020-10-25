/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import universidad.entidades.Alumno;
import universidad.entidades.Inscripcion;
import universidad.entidades.Materia;

/**
 *
 * @author Usuario
 */
public class InscripcionData {
    
    private Connection con;
      private Conexion conexion;

    public InscripcionData (Conexion conexion) {
        this.conexion= conexion;
      con= conexion.getConnection();
    
    }
    
   public void guardarInscripcion(Inscripcion inscripcion){
    
          try {
              String sql = "INSERT INTO inscripcion (idAlumno, idMateria, calificacion) VALUES ( ? , ? , ? );";
              
              PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
              ps.setInt(2, inscripcion.getMateria().getIdMateria());
              ps.setDouble(3,inscripcion.getCalificacion());
               ps.executeUpdate();
              ResultSet rs = ps.getGeneratedKeys();
              
              if (rs.next()) {
                  inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
              }else {
                  System.out.println("No se pudo insertar ");
              }
              ps.close();
          } catch (SQLException ex) {
              Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
          }
   }  
   
    public void borrarInscripcion(int idInscripcion){
    
     
        try {
            
            String sql = "DELETE FROM inscripcion  WHERE idInscripcion=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idInscripcion);
            ps.executeUpdate();
            ps.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    
     public void actualizarinscripcion(Inscripcion inscripcion){
       
       
        try {
            String sql = "UPDATE cursada SET idAlumno=?, idMateria =?, calificacion=? WHERE id=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
              ps.setInt(2, inscripcion.getMateria().getIdMateria());
              ps.setDouble(3,inscripcion.getCalificacion());
              ps.setInt(4, inscripcion.getIdInscripcion());
                   
              ps.executeUpdate();
                    
              ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
     public List<Inscripcion> obtenerInscripcion(){
 
    List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
     Inscripcion inscripcion = null;
            
        try {
           
            String sql = "SELECT * FROM inscripcion;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
          
            
            while(resultSet.next()){
                
                inscripcion= new Inscripcion();
                  inscripcion.setIdInscripcion(resultSet.getInt("idInscripcion"));
              Alumno alumno= buscarAlumno(resultSet.getInt("idAlumno"));
                inscripcion.setAlumno(alumno);
               Materia materia= buscarMateria(resultSet.getInt("idMateria")); 
               inscripcion.setMateria(materia);
               
                inscripciones.add(inscripcion);
            }  
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
return inscripciones;
}
     
  
     
  public Materia buscarMateria(int  idMateria){
         MateriaData md= new MateriaData(conexion);
                
    return md.buscarMateria( idMateria);
 
}   
     
    public List<Inscripcion> obtenerInscripcionXAlumno(int idAlumno){
 
    List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
     Inscripcion inscripcion = null;
            
        try {
           
            String sql = "SELECT * FROM inscripcion  WHERE idAlumno=? ;";
            
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, idAlumno);
            ResultSet resultSet = ps.executeQuery();
          
            
            while(resultSet.next()){
                 inscripcion= new Inscripcion();
                  inscripcion.setIdInscripcion(resultSet.getInt("idInscripcion"));
              Alumno alumno= buscarAlumno(resultSet.getInt("idAlumno"));
                inscripcion.setAlumno(alumno);
               Materia materia= buscarMateria(resultSet.getInt("idMateria")); 
               inscripcion.setMateria(materia);                            
                       
                inscripciones.add(inscripcion);
            }  
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
return inscripciones;
 }  

    private Alumno buscarAlumno(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
