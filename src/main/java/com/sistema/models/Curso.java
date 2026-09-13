package com.sistema.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Curso para gestionar cursos y sus estudiantes.
 */
public class Curso {
    private String id;
    private String nombre;
    private String codigoMateria;
    private Profesor profesor;
    private List<Estudiante> estudiantes;
    private int capacidad;
    private String horario;
    private String aula;

    public Curso(String id, String nombre, String codigoMateria, Profesor profesor, 
                int capacidad, String horario, String aula) {
        this.id = id;
        this.nombre = nombre;
        this.codigoMateria = codigoMateria;
        this.profesor = profesor;
        this.capacidad = capacidad;
        this.horario = horario;
        this.aula = aula;
        this.estudiantes = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getHorario() {
        return horario;
    }

    public String getAula() {
        return aula;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    // Métodos específicos
    public boolean agregarEstudiante(Estudiante estudiante) {
        if (estudiantes.size() < capacidad && !estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            return true;
        }
        return false;
    }

    public void removerEstudiante(Estudiante estudiante) {
        estudiantes.remove(estudiante);
    }

    public int getEstudiantesInscritos() {
        return estudiantes.size();
    }

    public boolean estaLleno() {
        return estudiantes.size() >= capacidad;
    }

    public int getLugaresDisponibles() {
        return capacidad - estudiantes.size();
    }

    @Override
    public String toString() {
        return "Curso{" +
               "Nombre: " + nombre +
               ", Código: " + codigoMateria +
               ", Profesor: " + profesor.getNombreCompleto() +
               ", Inscritos: " + getEstudiantesInscritos() + "/" + capacidad +
               ", Aula: " + aula +
               ", Horario: " + horario +
               '}';
    }
}
