/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import  java.util.ArrayList ;
import  java.util.List ;
import  java.util.logging.Level ;
import  java.util.logging.Logger ;
import javax.swing.JOptionPane;
import universidad.entidades.Alumno;

/**
 *
 * @author Usuario
 */
public class AlumnoData {
    private Connection con;
    
    public AlumnoData(Conexion conexion){
    
        con=conexion.getConnection();
    }
    
    public void guardarAlumno(Alumno alumno){
        
    
        String sql="INSERT into alumno (nombreAlumno, fechaNacimiento,activo) "
                + "VALUES(?, ?, ?);";
        
        try{
        
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,alumno.getNombreAlumno() );
        ps.setDate(2, Date.valueOf(alumno.getFechaNacimiento()));
        ps.setBoolean(3, alumno.isActivo());
        
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null,"No puedo obtener id");
            }
       
        con.close();
        }catch(SQLException e){
        
            JOptionPane.showMessageDialog(null,"Error al guardar Alumno");
        }
       
}
    
    public void borrarAlumno(int idAlumno){
    
     
        try {
            
            String sql = "DELETE FROM alumno  WHERE idAlumno=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idAlumno);
            ps.executeUpdate();
            ps.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
          JOptionPane.showMessageDialog(null,"Error al Borrar Alumno");
          
        }
    
    public void actualizarAlumno(Alumno alumno){
       
        try {
            String sql = "UPDATE alumno SET  nombreAlumno=?,  fechaNacimiento=? , activo=? WHERE idAlumno=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, alumno.getNombreAlumno());
            ps.setDate(2, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(3, alumno.isActivo());
            ps.setInt(4, alumno.getIdAlumno());
                  
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"El alumno se actualizó correctamente");
           
             
          ResultSet rs = ps.getGeneratedKeys();//recorre la base de datos para dar el id
            
           if (rs.next()){
            
            alumno.setIdAlumno(rs.getInt(1)); //recupero el id
             
          }
          else {
               System.out.println("Error al actualizar Alumno "); 
           }
        ps.close();
                    
           
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }   
       
   }

   public List<Alumno> obtenerAlumno(){
 
    List<Alumno> alumnos = new ArrayList<Alumno>();
            
        try {
           
            String sql = "SELECT * FROM alumno;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            Alumno alumno;
            
            while(resultSet.next()){
                
                alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("idAlumno"));
                alumno.setNombreAlumno(resultSet.getString("nombreAlumno"));
                alumno.setFechaNacimiento(resultSet.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(resultSet.getBoolean("activo"));               
                
                alumnos.add(alumno);
            }  
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }  
return alumnos;
  }
 }
