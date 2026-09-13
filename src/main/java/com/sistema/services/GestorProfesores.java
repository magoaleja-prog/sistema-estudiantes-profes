package com.sistema.services;

import com.sistema.models.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestorProfesores para gestionar operaciones CRUD de profesores.
 */
public class GestorProfesores {
    private List<Profesor> profesores;

    public GestorProfesores() {
        this.profesores = new ArrayList<>();
    }

    // Crear
    public void agregarProfesor(Profesor profesor) {
        if (!existeProfesor(profesor.getId())) {
            profesores.add(profesor);
            System.out.println("✓ Profesor agregado exitosamente: " + profesor.getNombreCompleto());
        } else {
            System.out.println("✗ El profesor ya existe en el sistema.");
        }
    }

    // Leer
    public Profesor obtenerProfesor(String id) {
        for (Profesor prof : profesores) {
            if (prof.getId().equals(id)) {
                return prof;
            }
        }
        return null;
    }

    public Profesor obtenerProfesorPorEmpleado(String numeroEmpleado) {
        for (Profesor prof : profesores) {
            if (prof.getNumeroEmpleado().equals(numeroEmpleado)) {
                return prof;
            }
        }
        return null;
    }

    public List<Profesor> listarTodos() {
        return new ArrayList<>(profesores);
    }

    public List<Profesor> listarPorDepartamento(String departamento) {
        List<Profesor> resultado = new ArrayList<>();
        for (Profesor prof : profesores) {
            if (prof.getDepartamento().equalsIgnoreCase(departamento)) {
                resultado.add(prof);
            }
        }
        return resultado;
    }

    public List<Profesor> listarActivos() {
        List<Profesor> resultado = new ArrayList<>();
        for (Profesor prof : profesores) {
            if (prof.isActivo()) {
                resultado.add(prof);
            }
        }
        return resultado;
    }

    // Actualizar
    public boolean actualizarProfesor(String id, Profesor datosActualizados) {
        Profesor prof = obtenerProfesor(id);
        if (prof != null) {
            prof.setNombre(datosActualizados.getNombre());
            prof.setApellido(datosActualizados.getApellido());
            prof.setEmail(datosActualizados.getEmail());
            prof.setTelefono(datosActualizados.getTelefono());
            prof.setEdad(datosActualizados.getEdad());
            prof.setDepartamento(datosActualizados.getDepartamento());
            prof.setSalario(datosActualizados.getSalario());
            System.out.println("✓ Profesor actualizado: " + prof.getNombreCompleto());
            return true;
        }
        System.out.println("✗ Profesor no encontrado.");
        return false;
    }

    // Eliminar (desactivar)
    public boolean eliminarProfesor(String id) {
        Profesor prof = obtenerProfesor(id);
        if (prof != null) {
            prof.setActivo(false);
            System.out.println("✓ Profesor desactivado: " + prof.getNombreCompleto());
            return true;
        }
        System.out.println("✗ Profesor no encontrado.");
        return false;
    }

    // Métodos de utilidad
    public int getCantidadProfesores() {
        return profesores.size();
    }

    public int getCantidadProfesoresActivos() {
        int contador = 0;
        for (Profesor prof : profesores) {
            if (prof.isActivo()) {
                contador++;
            }
        }
        return contador;
    }

    public boolean existeProfesor(String id) {
        return obtenerProfesor(id) != null;
    }

    public double calcularSalarioPromedio() {
        if (profesores.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Profesor prof : profesores) {
            suma += prof.getSalario();
        }
        return suma / profesores.size();
    }

    public void mostrarTodos() {
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }
        System.out.println("\n========== LISTA DE PROFESORES ==========");
        for (int i = 0; i < profesores.size(); i++) {
            System.out.println((i + 1) + ". " + profesores.get(i).toString());
        }
        System.out.println("=========================================\n");
    }
}
