package com.example;

// Importa las clases necesarias para mostrar cuadros de diálogo
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class libreria {
    // Arreglo para almacenar objetos de tipo Libro, con un máximo de 15 libros
    static Libro[] libros = new Libro[15];
    static int numLibros = 0; // Contador para llevar la cuenta de libros registrados

    public static void main(String[] args) {
        int opcion; // Variable para almacenar la opción seleccionada por el usuario en el menú
        do {
            // Menú de opciones para la gestión de libros en la librería
            String menu = """
                         Gestion de libros en una libreria
                         1. agregar un libro
                         2. ver lista de libros
                         3. buscar un libro por titulo
                         4. salir
                         """;
            // Muestra el menú y captura la opción seleccionada por el usuario
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    // Llama al método para agregar un libro
                    agregarLibro();
                    break;
                case 2:
                    // Llama al método para ver los libros registrados
                    verLibrosRegistrados();
                    break;
                case 3:
                    // Llama al método para buscar un libro por título
                    buscarLibroPorTitulo();
                    break;
                case 4:
                    // Muestra un mensaje de salida del sistema
                    JOptionPane.showMessageDialog(null, "saliendo...");
                    break;
                default:
                    // Muestra un mensaje si la opción ingresada no es válida
                    JOptionPane.showMessageDialog(null, "opcion no valida. intenta de nuevo");
            }
        } while (opcion != 4); // Repite el menú hasta que el usuario elija salir
    }

    // Método para agregar un libro
    public static void agregarLibro() {
        if (numLibros < libros.length) {
            // Campos de texto para ingresar los datos del libro
            JTextField nombreField = new JTextField();
            JTextField autorField = new JTextField();

            // Arreglo de objetos para el formulario de registro
            Object[] formulario = {
                "Titulo:", nombreField,
                "Autor:", autorField
            };
            // Muestra el formulario de registro y captura la opción seleccionada
            int option = JOptionPane.showConfirmDialog(null, formulario, "Registro de libro", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Obtiene los datos ingresados por el usuario
                String titulo = nombreField.getText();
                String autor = autorField.getText();
                // Crea un nuevo libro y lo añade al arreglo
                libros[numLibros] = new Libro(titulo, autor);
                numLibros++;
                // Muestra un mensaje de éxito con los datos del libro registrado
                JOptionPane.showMessageDialog(null,
                        """
                        Libro registrado con \u00e9xito:
                        Nombre: """ + titulo + "\n" +
                        "Autor: " + autor + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no hay espacio para agregar mas libros");
        }
    }

    // Método para ver los libros registrados
    public static void verLibrosRegistrados() {
        if (numLibros == 0) {
            JOptionPane.showMessageDialog(null, "No hay libros registrados.");
        } else {
            StringBuilder listadoLibros = new StringBuilder("Libros Registrados:\n");
            for (int i = 0; i < numLibros; i++) {
                listadoLibros.append("Libro ").append(i + 1).append(": ")
                        .append(libros[i].getTitulo()).append(" ")
                        .append(libros[i].getAutor()).append("\n");
            }
            // Muestra la lista de libros registrados en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, listadoLibros.toString());
        }
    }

    // Método para buscar un libro por su título
    public static void buscarLibroPorTitulo() {
        if (numLibros == 0) {
            JOptionPane.showMessageDialog(null, "No hay libros registrados.");
        } else {
            // Solicita al usuario el título del libro que desea buscar
            String tituloBuscado = JOptionPane.showInputDialog("Introduce el título del libro a buscar:");
            boolean encontrado = false; // Variable para indicar si el libro fue encontrado
            for (int i = 0; i < numLibros; i++) {
                if (libros[i].getTitulo().equalsIgnoreCase(tituloBuscado)) {
                    // Muestra los detalles del libro encontrado
                    JOptionPane.showMessageDialog(null, "Libro encontrado:\n" +
                            "Título: " + libros[i].getTitulo() + "\n" +
                            "Autor: " + libros[i].getAutor());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Libro no encontrado.");
            }
        }
    }
}

// Clase para representar un libro
class Libro {
    private String titulo; // Título del libro
    private String autor; // Autor del libro

    // Constructor de la clase Libro
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Métodos para obtener los atributos del libro
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}
