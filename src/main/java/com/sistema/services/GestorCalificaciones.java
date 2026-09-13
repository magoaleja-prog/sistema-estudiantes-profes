package com.sistema.services;

import com.sistema.models.Calificacion;
import com.sistema.models.Estudiante;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestorCalificaciones para gestionar las calificaciones de estudiantes.
 */
public class GestorCalificaciones {
    private List<Calificacion> calificaciones;

    public GestorCalificaciones() {
        this.calificaciones = new ArrayList<>();
    }

    // Crear
    public void agregarCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
        System.out.println("✓ Calificación registrada: " + calificacion.getEstudiante() + 
                         " - " + calificacion.getMateria() + " (" + calificacion.getNota() + ")");
    }

    // Leer
    public Calificacion obtenerCalificacion(String id) {
        for (Calificacion cal : calificaciones) {
            if (cal.getId().equals(id)) {
                return cal;
            }
        }
        return null;
    }

    public List<Calificacion> obtenerCalificacionesEstudiante(String idEstudiante) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion cal : calificaciones) {
            if (cal.getEstudiante().equals(idEstudiante)) {
                resultado.add(cal);
            }
        }
        return resultado;
    }

    public List<Calificacion> obtenerCalificacionesPorMateria(String materia) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion cal : calificaciones) {
            if (cal.getMateria().equalsIgnoreCase(materia)) {
                resultado.add(cal);
            }
        }
        return resultado;
    }

    public List<Calificacion> obtenerCalificacionesPorProfesor(String idProfesor) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion cal : calificaciones) {
            if (cal.getProfesor().equals(idProfesor)) {
                resultado.add(cal);
            }
        }
        return resultado;
    }

    // Actualizar
    public boolean actualizarCalificacion(String id, double nuevaNota, String observaciones) {
        Calificacion cal = obtenerCalificacion(id);
        if (cal != null) {
            if (nuevaNota >= 0 && nuevaNota <= 10) {
                cal.setNota(nuevaNota);
                cal.setObservaciones(observaciones);
                System.out.println("✓ Calificación actualizada: " + nuevaNota);
                return true;
            } else {
                System.out.println("✗ La nota debe estar entre 0 y 10.");
                return false;
            }
        }
        System.out.println("✗ Calificación no encontrada.");
        return false;
    }

    // Eliminar
    public boolean eliminarCalificacion(String id) {
        Calificacion cal = obtenerCalificacion(id);
        if (cal != null) {
            calificaciones.remove(cal);
            System.out.println("✓ Calificación eliminada.");
            return true;
        }
        System.out.println("✗ Calificación no encontrada.");
        return false;
    }

    // Métodos de utilidad
    public double calcularPromedioEstudiante(String idEstudiante) {
        List<Calificacion> cals = obtenerCalificacionesEstudiante(idEstudiante);
        if (cals.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Calificacion cal : cals) {
            suma += cal.getNota();
        }
        return suma / cals.size();
    }

    public double calcularPromedioMateria(String materia) {
        List<Calificacion> cals = obtenerCalificacionesPorMateria(materia);
        if (cals.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Calificacion cal : cals) {
            suma += cal.getNota();
        }
        return suma / cals.size();
    }

    public double calcularPromedioEstudianteMateria(String idEstudiante, String materia) {
        List<Calificacion> cals = obtenerCalificacionesEstudiante(idEstudiante);
        List<Calificacion> calMateria = new ArrayList<>();
        
        for (Calificacion cal : cals) {
            if (cal.getMateria().equalsIgnoreCase(materia)) {
                calMateria.add(cal);
            }
        }
        
        if (calMateria.isEmpty()) {
            return 0.0;
        }
        
        double suma = 0;
        for (Calificacion cal : calMateria) {
            suma += cal.getNota();
        }
        return suma / calMateria.size();
    }

    public int getCantidadCalificaciones() {
        return calificaciones.size();
    }

    public List<Calificacion> obtenerCalificacionesEnRango(double notaMin, double notaMax) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion cal : calificaciones) {
            if (cal.getNota() >= notaMin && cal.getNota() <= notaMax) {
                resultado.add(cal);
            }
        }
        return resultado;
    }

    public void mostrarCalificacionesEstudiante(String idEstudiante) {
        List<Calificacion> cals = obtenerCalificacionesEstudiante(idEstudiante);
        if (cals.isEmpty()) {
            System.out.println("El estudiante no tiene calificaciones registradas.");
            return;
        }
        System.out.println("\n========== CALIFICACIONES DEL ESTUDIANTE ==========");
        for (int i = 0; i < cals.size(); i++) {
            Calificacion cal = cals.get(i);
            System.out.println((i + 1) + ". " + cal.toString());
        }
        System.out.println("Promedio: " + String.format("%.2f", calcularPromedioEstudiante(idEstudiante)));
        System.out.println("===================================================\n");
    }
}
