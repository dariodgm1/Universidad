/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import universidad.entidades.Alumno;
import universidad.entidades.Materia;
import universidad.modelo.AlumnoData;
import universidad.modelo.Conexion;
import universidad.modelo.MateriaData;

/**
 *
 * @author Usuario
 */
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion c=new Conexion();
        
        
        Alumno juan = new Alumno("Juan Perez",LocalDate.of(1983, Month.APRIL, 8),true);
        Alumno luis = new Alumno("Luis Sosa",LocalDate.of(1984,Month.JUNE, 9),true);
        Alumno camila = new Alumno("Camila Nuñez",LocalDate.of(1982,Month.JULY, 10),true);
        Alumno lucrecia = new Alumno("Lucrecia Godoy",LocalDate.of(1984,Month.MAY, 15),true);
        AlumnoData ad=new AlumnoData(c);
        ad.guardarAlumno(juan);
        ad.guardarAlumno(luis);
        ad.guardarAlumno(camila);
        ad.guardarAlumno(lucrecia);
        
        /*Materia matematica = new Materia(1,"Matematica");
        Materia laboratorio = new Materia(2,"Laboratorio");
        Materia eda = new Materia(3,"Eda");
        Materia web = new Materia(4,"Web");
        MateriaData md =new MateriaData(c);
        md.guardarMateria(matematica);
        md.guardarMateria(laboratorio);
        md.guardarMateria(eda);
        md.guardarMateria(web);*/
        
    }
    
}
