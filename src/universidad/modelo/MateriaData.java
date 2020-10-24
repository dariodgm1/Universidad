/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.modelo;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import universidad.entidades.Materia;
/**
 *
 * @author Usuario
 */
public class MateriaData {
    
     private Connection con;

    public MateriaData (Conexion conexion) {
      con= conexion.getConnection();
      
    }
     public void guardarMateria(Materia materia){
     
       try {
           String sql = "INSERT INTO materia (nombreMateria) VALUES ( ?);";
           
           PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, materia.getNombreMateria());
          
           ps.executeUpdate();
           
           ResultSet rs = ps.getGeneratedKeys();
           
           if (rs.next()) {
               materia.setIdMateria(rs.getInt("idMateria"));
           }else {
                System.out.println("No se pudo insertar ");
            }
                ps.close();
             
               
            } catch (SQLException ex) {
           Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
       }
}
public void borrarMateria(int idMateria){
     
       try {
           String sql = "DELETE FROM materia  WHERE idMateria=?;";
           
           PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setInt(1, idMateria);
           ps.executeUpdate();
           ps.close();
       } catch (SQLException ex) {
           Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
       }



}

    public void actualizarMateria(Materia materia){
       
       
        try {
            String sql = "UPDATE materia SET nombreMateria=? WHERE idMateria=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombreMateria());
            ps.setInt(2, materia.getIdMateria());
                   
            ps.executeUpdate();
             ResultSet rs = ps.getGeneratedKeys();//recorre la base de datos para dar el id
            
            if (rs.next()){
            
            materia.setIdMateria(rs.getInt(1)); //recupero el id
            }
            else {
                System.out.println("Error al actualizar Materia "); 
            }
        
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
   }


public Materia buscarMateria(int idMateria){
    
 Materia materia = null;
        try {
            
           String sql = "SELECT * FROM materia  WHERE idMateria=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idMateria);
            ResultSet resultSet = ps.executeQuery();//no modifica la BD solo saca informacion
            
            
            while(resultSet.next()){//el while recorre toda la BD
                
                materia = new Materia();
                materia.setIdMateria(resultSet.getInt("idMateria"));
                materia.setNombreMateria(resultSet.getString("nombreMateria"));
               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return materia;
}
    
    public List<Materia> obtenerMaterias(){
 
    List<Materia> materias = new ArrayList<Materia>();
            
        try {
           
            String sql = "SELECT * FROM materia;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            Materia materia;
            
            while(resultSet.next()){
                
                materia = new Materia();
                 materia.setIdMateria(resultSet.getInt("idMateria"));
                materia.setNombreMateria(resultSet.getString("nombreMateria"));
               
                
                
                materias.add(materia);
            }  
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
return materias;
 }
}
