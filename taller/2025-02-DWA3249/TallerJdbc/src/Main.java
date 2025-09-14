import dao.EstudianteDAO;
import model.Estudiante;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final EstudianteDAO dao = new EstudianteDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar Estudiante");
            System.out.println("2. Actualizar Estudiante");
            System.out.println("3. Eliminar Estudiante");
            System.out.println("4. Consultar todos los estudiantes");
            System.out.println("5. Consultar Estudiante por email");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> insertar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listarTodos();
                case 5 -> buscarPorCorreo();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private static void insertar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.print("Estado Civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estadoCivil = sc.nextLine().toUpperCase();

        Estudiante est = new Estudiante(nombre, apellido, correo, edad, estadoCivil);
        dao.insertar(est);
    }

    private static void actualizar() {
        System.out.print("Correo del estudiante a actualizar: ");
        String correo = sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Nueva edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.print("Nuevo estado civil: ");
        String estadoCivil = sc.nextLine().toUpperCase();

        Estudiante est = new Estudiante(nombre, apellido, correo, edad, estadoCivil);
        dao.actualizar(est);
    }

    private static void eliminar() {
        System.out.print("Correo del estudiante a eliminar: ");
        String correo = sc.nextLine();
        dao.eliminar(correo);
    }

    private static void listarTodos() {
        List<Estudiante> lista = dao.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante e : lista) {
                System.out.println("ID: " + e.getId() +
                        " | Nombre: " + e.getNombre() +
                        " | Apellido: " + e.getApellido() +
                        " | Correo: " + e.getCorreo() +
                        " | Edad: " + e.getEdad() +
                        " | Estado Civil: " + e.getEstadoCivil());
            }
        }
    }

    private static void buscarPorCorreo() {
        System.out.print("Correo del estudiante: ");
        String correo = sc.nextLine();
        Estudiante e = dao.buscarPorCorreo(correo);
        if (e != null) {
            System.out.println("ID: " + e.getId() +
                    " | Nombre: " + e.getNombre() +
                    " | Apellido: " + e.getApellido() +
                    " | Correo: " + e.getCorreo() +
                    " | Edad: " + e.getEdad() +
                    " | Estado Civil: " + e.getEstadoCivil());
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}
