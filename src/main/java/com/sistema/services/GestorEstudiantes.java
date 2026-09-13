package com.sistema.services;

import com.sistema.models.Estudiante;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestorEstudiantes para gestionar operaciones CRUD de estudiantes.
 */
public class GestorEstudiantes {
    private List<Estudiante> estudiantes;

    public GestorEstudiantes() {
        this.estudiantes = new ArrayList<>();
    }

    // Crear
    public void agregarEstudiante(Estudiante estudiante) {
        if (!existeEstudiante(estudiante.getId())) {
            estudiantes.add(estudiante);
            System.out.println("✓ Estudiante agregado exitosamente: " + estudiante.getNombreCompleto());
        } else {
            System.out.println("✗ El estudiante ya existe en el sistema.");
        }
    }

    // Leer
    public Estudiante obtenerEstudiante(String id) {
        for (Estudiante est : estudiantes) {
            if (est.getId().equals(id)) {
                return est;
            }
        }
        return null;
    }

    public Estudiante obtenerEstudiantePorMatricula(String matricula) {
        for (Estudiante est : estudiantes) {
            if (est.getMatricula().equals(matricula)) {
                return est;
            }
        }
        return null;
    }

    public List<Estudiante> listarTodos() {
        return new ArrayList<>(estudiantes);
    }

    public List<Estudiante> listarPorCarrera(String carrera) {
        List<Estudiante> resultado = new ArrayList<>();
        for (Estudiante est : estudiantes) {
            if (est.getCarrera().equalsIgnoreCase(carrera)) {
                resultado.add(est);
            }
        }
        return resultado;
    }

    // Actualizar
    public boolean actualizarEstudiante(String id, Estudiante datosActualizados) {
        Estudiante est = obtenerEstudiante(id);
        if (est != null) {
            est.setNombre(datosActualizados.getNombre());
            est.setApellido(datosActualizados.getApellido());
            est.setEmail(datosActualizados.getEmail());
            est.setTelefono(datosActualizados.getTelefono());
            est.setEdad(datosActualizados.getEdad());
            est.setCarrera(datosActualizados.getCarrera());
            est.setSemestre(datosActualizados.getSemestre());
            System.out.println("✓ Estudiante actualizado: " + est.getNombreCompleto());
            return true;
        }
        System.out.println("✗ Estudiante no encontrado.");
        return false;
    }

    // Eliminar
    public boolean eliminarEstudiante(String id) {
        Estudiante est = obtenerEstudiante(id);
        if (est != null) {
            estudiantes.remove(est);
            System.out.println("✓ Estudiante eliminado: " + est.getNombreCompleto());
            return true;
        }
        System.out.println("✗ Estudiante no encontrado.");
        return false;
    }

    // Métodos de utilidad
    public int getCantidadEstudiantes() {
        return estudiantes.size();
    }

    public boolean existeEstudiante(String id) {
        return obtenerEstudiante(id) != null;
    }

    public double calcularPromedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Estudiante est : estudiantes) {
            suma += est.calcularPromedio();
        }
        return suma / estudiantes.size();
    }

    public void mostrarTodos() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("\n========== LISTA DE ESTUDIANTES ==========");
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i).toString());
        }
        System.out.println("==========================================\n");
    }
}
