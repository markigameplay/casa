package com.example;

// Importa la clase necesaria para mostrar cuadros de diálogo
import javax.swing.JOptionPane;

public class Asistencia {
    // Arreglo para almacenar objetos de tipo Estudiante, con un máximo de 20 estudiantes
    static Estudiante[] estudiantes = new Estudiante[20];
    static int numEstudiantes = 0; // Contador para llevar la cuenta de estudiantes registrados

    public static void main(String[] args) {
        int opcion; // Variable para almacenar la opción seleccionada por el usuario en el menú
        do {
            // Menú de opciones para el control de asistencia de estudiantes
            String menu = """
                          Control de Notas de Estudiantes
                          1. Registrar estudiante y asistencia
                          2. Ver lista de estudiantes y sus asistencias
                          3. Cuenta y muestra la cantidad de estudiantes presentes y ausentes.
                          4. Salir
                          """;
            // Muestra el menú y captura la opción seleccionada por el usuario
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    // Llama al método para registrar un estudiante y su asistencia
                    registrarEstudiante();
                    break;
                case 2:
                    // Llama al método para ver los estudiantes registrados y sus asistencias
                    verEstudiantesYAsistencias();
                    break;
                case 3:
                    // Llama al método para contar y mostrar la cantidad de estudiantes presentes y ausentes
                    cantidadPresentesYAusentes();
                    break;
                case 4:
                    // Muestra un mensaje de salida del sistema
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    // Muestra un mensaje si la opción ingresada no es válida
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4); // Repite el menú hasta que el usuario elija salir
    }

    // Método para registrar un estudiante y su asistencia
    public static void registrarEstudiante() {
        if (numEstudiantes < estudiantes.length) {
            // Solicita al usuario el nombre del estudiante
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
            // Opciones para la asistencia del estudiante
            String[] opciones = {"Presente", "Ausente"};
            // Muestra un cuadro de diálogo para seleccionar la asistencia
            int asistencia = JOptionPane.showOptionDialog(null, 
                "Seleccione la asistencia del estudiante:", 
                "Registro de Asistencia",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

            // Verifica que el nombre no esté vacío
            if (nombre != null && !nombre.trim().isEmpty()) {
                // Crea un nuevo estudiante y lo añade al arreglo
                estudiantes[numEstudiantes] = new Estudiante(nombre, asistencia);
                numEstudiantes++;
                // Muestra un mensaje de éxito con los datos del estudiante registrado
                JOptionPane.showMessageDialog(null, 
                    "Estudiante registrado con éxito:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Asistencia: " + (asistencia == 0 ? "Presente" : "Ausente"));
            } else {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más estudiantes.");
        }
    }

    // Método para ver los estudiantes registrados y sus asistencias
    public static void verEstudiantesYAsistencias() {
        if (numEstudiantes == 0) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
        } else {
            StringBuilder listado = new StringBuilder("Estudiantes y sus Asistencias:\n");
            for (int i = 0; i < numEstudiantes; i++) {
                listado.append("Estudiante ").append(i + 1).append(": ")
                      .append(estudiantes[i].getNombre())
                      .append(", Asistencia: ")
                      .append(estudiantes[i].getAsistencia() == 0 ? "Presente" : "Ausente")
                      .append("\n");
            }
            // Muestra la lista de estudiantes y sus asistencias en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, listado.toString());
        }
    }

    // Método para contar y mostrar la cantidad de estudiantes presentes y ausentes
    public static void cantidadPresentesYAusentes() {
        if (numEstudiantes == 0) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
        } else {
            int presentes = 0; // Contador de estudiantes presentes
            int ausentes = 0; // Contador de estudiantes ausentes
            
            for (int i = 0; i < numEstudiantes; i++) {
                if (estudiantes[i].getAsistencia() == 0) {
                    presentes++;
                } else {
                    ausentes++;
                }
            }
            
            // Muestra la cantidad de estudiantes presentes y ausentes en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                "Total de estudiantes: " + numEstudiantes + "\n" +
                "Estudiantes presentes: " + presentes + "\n" +
                "Estudiantes ausentes: " + ausentes);
        }
    }

    // Clase interna para representar un estudiante
    static class Estudiante {
        private String nombre; // Nombre del estudiante
        private int asistencia; // Asistencia del estudiante (0 = presente, 1 = ausente)

        // Constructor de la clase Estudiante
        public Estudiante(String nombre, int asistencia) {
            this.nombre = nombre;
            this.asistencia = asistencia;
        }

        // Métodos para obtener los atributos del estudiante
        public String getNombre() {
            return nombre;
        }

        public int getAsistencia() {
            return asistencia;
        }
    }
}
