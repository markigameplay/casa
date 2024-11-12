package com.example;

// Importa las clases necesarias para mostrar cuadros de diálogo y manejar fechas
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JTextField;

public class Semana13dos {
    
    // Arreglo para almacenar objetos de tipo Cliente, con un máximo de 10 clientes
    static Cliente[] clientes = new Cliente[10];
    static int numClientes = 0; // Contador para llevar la cuenta de clientes registrados

    public static void main(String[] args) {
        // Arreglo booleano para representar el estado de las habitaciones (true = ocupada, false = disponible)
        boolean[] habitaciones = new boolean[10];
        int Opcion; // Variable para almacenar la opción seleccionada por el usuario en el menú
        do {
            // Menú de opciones para la gestión de habitaciones del hotel
            String Menu = """
                          Gestion de habitaciones del hotel 
                          1. para ver el estado de habitacion.
                          2. reservar una habitacion.
                          3. Para liberar una habitacion.
                          4. Mostrar estadisticas de ocupacion.
                          5. Registrar usuario.
                          6. ver clientes registrados.
                          7. salir 
                          Elige una opcion
                          """;
            // Muestra el menú y captura la opción seleccionada por el usuario
            Opcion = Integer.parseInt(JOptionPane.showInputDialog(Menu));
            switch (Opcion) {
                case 1:
                    // Llama al método para ver el estado de las habitaciones
                    verEstadoHabitaciones(habitaciones);
                    break;
                case 2:
                    // Llama al método para reservar una habitación
                    reservarHabitaciones(habitaciones);
                    break;
                case 3:
                    // Llama al método para liberar una habitación
                    liberarHabitaciones(habitaciones);
                    break;
                case 4:
                    // Llama al método para mostrar estadísticas de ocupación
                    mostrarEstadisticas(habitaciones);
                    break;
                case 5:
                    // Llama al método para registrar un nuevo usuario
                    registrarUsuario();
                    break;
                case 6:
                    // Llama al método para ver los clientes registrados
                    verClientesRegistrados();
                    break;
                case 7:
                    // Muestra un mensaje de salida del sistema
                    JOptionPane.showMessageDialog(null, "saliendo del sistema");
                    break;
                default:
                    // Muestra un mensaje si la opción ingresada no es válida
                    JOptionPane.showMessageDialog(null, "opcion no valida. intenta de nuevo");
            }
        } while (Opcion != 7); // Repite el menú hasta que el usuario elija salir
    }
    
    // Método para mostrar el estado de las habitaciones
    public static void verEstadoHabitaciones(boolean[] habitaciones) {
        // StringBuilder para construir el mensaje que se mostrará al usuario
        StringBuilder Estado = new StringBuilder("Estado de habitaciones: \n ");
        for (int i = 0; i < habitaciones.length; i++) {
            // Añade el estado de cada habitación al StringBuilder
            Estado.append("habitacion").append(i + 1).append(": ").append(habitaciones[i] ? "Ocupada" : "Disponible").append("\n");
        }
        // Muestra el estado de las habitaciones en un cuadro de diálogo
        JOptionPane.showMessageDialog(null, Estado.toString());
    }
    
    // Método para reservar una habitación
    public static void reservarHabitaciones(boolean[] habitaciones) {
        // Solicita al usuario el número de habitación que desea reservar
        int numeroHabitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingresar habitacion a reservar")) - 1;
        // Verifica que el número de habitación sea válido
        if (numeroHabitacion >= 0 && numeroHabitacion < habitaciones.length) {
            if (!habitaciones[numeroHabitacion]) {
                // Marca la habitación como ocupada
                habitaciones[numeroHabitacion] = true;
                JOptionPane.showMessageDialog(null, "Habitacion " + (numeroHabitacion + 1) + " Reservada con exito");
            } else {
                JOptionPane.showMessageDialog(null, "La habitacion ya esta reservada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Numero de habitacion no valido");
        }
    }
    
    // Método para liberar una habitación
    public static void liberarHabitaciones(boolean[] habitaciones) {
        // Solicita al usuario el número de habitación que desea liberar
        int numeroHabitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingresar habitacion a liberar")) - 1;
        // Verifica que el número de habitación sea válido
        if (numeroHabitacion >= 0 && numeroHabitacion < habitaciones.length) {
            if (habitaciones[numeroHabitacion]) {
                // Marca la habitación como disponible
                habitaciones[numeroHabitacion] = false;
                JOptionPane.showMessageDialog(null, "Habitacion " + (numeroHabitacion + 1) + " Liberada con exito");
            } else {
                JOptionPane.showMessageDialog(null, "La habitacion ya esta disponible");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Numero de habitacion no valido");
        }
    }
    
    // Método para mostrar estadísticas de ocupación de las habitaciones
    public static void mostrarEstadisticas(boolean[] habitaciones) {
        int ocupadas = 0; // Contador de habitaciones ocupadas
        int disponibles = 0; // Contador de habitaciones disponibles
        for (boolean estado : habitaciones) {
            if (estado) {
                ocupadas++;
            } else {
                disponibles++;
            }
        }
        // Muestra las estadísticas de ocupación en un cuadro de diálogo
        JOptionPane.showMessageDialog(null, """
                                            Estadisticas de ocupacion: 
                                            habitaciones ocupadas: """ + ocupadas + "\n" + "habitaciones disponibles: " + disponibles);
    }
    
    // Método para registrar un nuevo usuario
    public static void registrarUsuario() {
        if (numClientes < clientes.length) {
            // Campos de texto para ingresar los datos del cliente
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField fechaNacimientoField = new JTextField();
            JTextField direccionField = new JTextField();

            // Arreglo de objetos para el formulario de registro
            Object[] formulario = {
                    "Nombre:", nombreField,
                    "Apellido:", apellidoField,
                    "Fecha de Nacimiento (YYYY-MM-DD):", fechaNacimientoField,
                    "Dirección:", direccionField
            };

            // Muestra el formulario de registro y captura la opción seleccionada
            int option = JOptionPane.showConfirmDialog(null, formulario, "Registro de Usuario", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // Obtiene los datos ingresados por el usuario
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String fechaNacimiento = fechaNacimientoField.getText();
                String direccion = direccionField.getText();
                int edad = calcularEdad(fechaNacimiento);

                // Crea un nuevo cliente y lo añade al arreglo
                clientes[numClientes] = new Cliente(nombre, apellido, fechaNacimiento, edad, direccion);
                numClientes++;

                // Muestra un mensaje de éxito con los datos del cliente registrado
                JOptionPane.showMessageDialog(null,
                        """
                        Usuario registrado con \u00e9xito:
                        Nombre: """ + nombre + " " + apellido + "\n" +
                        "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                        "Edad: " + edad + " años\n" +
                        "Dirección: " + direccion);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más clientes.");
        }
    }

    // Método para ver los clientes registrados
    public static void verClientesRegistrados() {
        if (numClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
        } else {
            StringBuilder listadoClientes = new StringBuilder("Clientes Registrados:\n");
            for (int i = 0; i < numClientes; i++) {
                listadoClientes.append("Cliente ").append(i + 1).append(": ")
                        .append(clientes[i].getNombre()).append(" ")
                        .append(clientes[i].getApellido()).append(", ")
                        .append("Edad: ").append(clientes[i].getEdad()).append(", ")
                        .append("Dirección: ").append(clientes[i].getDireccion()).append("\n");
            }
            // Muestra la lista de clientes registrados en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, listadoClientes.toString());
        }
    }

    // Método para calcular la edad de un cliente a partir de su fecha de nacimiento
    public static int calcularEdad(String fechaNacimiento) {
        // Convierte la fecha de nacimiento de String a LocalDate
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
        // Obtiene la fecha actual
        LocalDate hoy = LocalDate.now();
        // Calcula la diferencia en años entre la fecha de nacimiento y la fecha actual
        return Period.between(fechaNac, hoy).getYears();
    }

    // Clase interna para representar un cliente
    public static class Cliente {
        private String Nombre; // Nombre del cliente
        private String Apellido; // Apellido del cliente
        private String FechaDeNacimiento; // Fecha de nacimiento del cliente
        private int Edad; // Edad del cliente
        private String direccion; // Dirección del cliente

        // Constructor de la clase Cliente
        public Cliente(String Nombre, String Apellido, String FechaDeNacimiento, int Edad, String direccion) {
            this.Nombre = Nombre;
            this.Apellido = Apellido;
            this.FechaDeNacimiento = FechaDeNacimiento;
            this.Edad = Edad;
            this.direccion = direccion;
        }

        // Métodos para obtener los atributos del cliente
        public String getNombre() {
            return Nombre;
        }

        public String getApellido() {
            return Apellido;
        }

        public int getEdad() {
            return Edad;
        }

        public String getDireccion() {
            return direccion;
        }
    }
}