package com.example;

// Importa las clases necesarias para mostrar cuadros de diálogo
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControlNotas {

    // Arreglo para almacenar objetos de tipo Estudiante, con un máximo de 10 estudiantes
    static Estudiante[] estudiantes = new Estudiante[10];
    static int numEstudiantes = 0; // Contador para llevar la cuenta de estudiantes registrados

    public static void main(String[] args) {
        int opcion; // Variable para almacenar la opción seleccionada por el usuario en el menú
        do {
            // Menú de opciones para el control de notas de estudiantes
            String menu = """
                          Control de Notas de Estudiantes
                          1. Registrar estudiante y nota
                          2. Ver lista de estudiantes y sus notas
                          3. Calcular promedio de notas
                          4. Salir
                          """;
            // Muestra el menú y captura la opción seleccionada por el usuario
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    // Llama al método para registrar un estudiante y su nota
                    registrarEstudiante();
                    break;
                case 2:
                    // Llama al método para ver los estudiantes registrados y sus notas
                    verEstudiantesYNotas();
                    break;
                case 3:
                    // Llama al método para calcular el promedio de las notas
                    calcularPromedioNotas();
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

    // Método para registrar un estudiante y su nota
    public static void registrarEstudiante() {
        if (numEstudiantes < estudiantes.length) {
            // Campos de texto para ingresar los datos del estudiante
            JTextField nombreField = new JTextField();
            JTextField notaField = new JTextField();

            // Arreglo de objetos para el formulario de registro
            Object[] formulario = {
                "Nombre del estudiante:", nombreField,
                "Nota (0-100):", notaField
            };
            // Muestra el formulario de registro y captura la opción seleccionada
            int option = JOptionPane.showConfirmDialog(null, formulario, "Registro de Estudiante", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Obtiene los datos ingresados por el usuario
                String nombre = nombreField.getText();
                int nota = Integer.parseInt(notaField.getText());
                // Verifica que la nota esté en el rango válido
                if (nota >= 0 && nota <= 100) {
                    // Crea un nuevo estudiante y lo añade al arreglo
                    estudiantes[numEstudiantes] = new Estudiante(nombre, nota);
                    numEstudiantes++;
                    // Muestra un mensaje de éxito con los datos del estudiante registrado
                    JOptionPane.showMessageDialog(null, "Estudiante registrado con éxito:\nNombre: " + nombre + "\nNota: " + nota);
                } else {
                    JOptionPane.showMessageDialog(null, "La nota debe estar entre 0 y 100.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más estudiantes.");
        }
    }

    // Método para ver los estudiantes registrados y sus notas
    public static void verEstudiantesYNotas() {
        if (numEstudiantes == 0) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
        } else {
            StringBuilder listadoEstudiantes = new StringBuilder("Estudiantes y sus Notas:\n");
            for (int i = 0; i < numEstudiantes; i++) {
                listadoEstudiantes.append("Estudiante ").append(i + 1).append(": ")
                        .append(estudiantes[i].getNombre()).append(", Nota: ")
                        .append(estudiantes[i].getNota()).append("\n");
            }
            // Muestra la lista de estudiantes y sus notas en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, listadoEstudiantes.toString());
        }
    }

    // Método para calcular el promedio de las notas de los estudiantes
    public static void calcularPromedioNotas() {
        if (numEstudiantes == 0) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
        } else {
            int sumaNotas = 0; // Variable para acumular la suma de las notas
            for (int i = 0; i < numEstudiantes; i++) {
                sumaNotas += estudiantes[i].getNota();
            }
            // Calcula el promedio dividiendo la suma de las notas por el número de estudiantes
            double promedio = (double) sumaNotas / numEstudiantes;
            // Muestra el promedio de las notas en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, "El promedio de las notas es: " + promedio);
        }
    }

    // Clase interna para representar un estudiante
    static class Estudiante {
        private String nombre; // Nombre del estudiante
        private int nota; // Nota del estudiante

        // Constructor de la clase Estudiante
        public Estudiante(String nombre, int nota) {
            this.nombre = nombre;
            this.nota = nota;
        }

        // Métodos para obtener los atributos del estudiante
        public String getNombre() {
            return nombre;
        }

        public int getNota() {
            return nota;
        }
    }
} 