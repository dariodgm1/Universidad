/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.entidades;

/**
 *
 * @author Usuario
 */
public class Inscripcion {
     private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private double calificacion;

    public Inscripcion() {
    }

    public Inscripcion(Alumno alumno, Materia materia, double calificacion) {
        this.alumno = alumno;
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public Inscripcion(int idInscripcion, Alumno alumno, Materia materia, double calificacion) {
        this.idInscripcion = idInscripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
     @Override
            public String toString(){
            return "Inscripcion{" + "idInscripcion=" + idInscripcion + ",alumno=" + alumno + ",materia=" + materia + ",calificacion=" + calificacion +'}';
  }
}
