/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.entidades;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Alumno {
    private int idAlumno;
    private String nombreAlumno;
    private LocalDate fechaNacimiento;
    private boolean activo;

    public Alumno() {
    }

    public Alumno(String nombreAlumno, LocalDate fechaNacimiento, boolean activo) {
        this.nombreAlumno = nombreAlumno;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    public Alumno(int idAlumno, String nombreAlumno, LocalDate fechaNacimiento, boolean activo) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
            public String toString(){
            return "Alumno{" + "idAlumno=" + idAlumno + ", nombreAlumno=" + nombreAlumno +'}';
  }
}
