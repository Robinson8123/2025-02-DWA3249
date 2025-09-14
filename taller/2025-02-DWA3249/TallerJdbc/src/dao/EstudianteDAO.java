package dao;

import db.Conexion;
import model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public void insertar(Estudiante est) {
        String sql = "INSERT INTO Estudiantes(nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, est.getNombre());
            stmt.setString(2, est.getApellido());
            stmt.setString(3, est.getCorreo());
            stmt.setInt(4, est.getEdad());
            stmt.setString(5, est.getEstadoCivil());

            stmt.executeUpdate();
            System.out.println("Estudiante insertado correctamente.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: El correo ya existe.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Estudiante est) {
        String sql = "UPDATE Estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, est.getNombre());
            stmt.setString(2, est.getApellido());
            stmt.setInt(3, est.getEdad());
            stmt.setString(4, est.getEstadoCivil());
            stmt.setString(5, est.getCorreo());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Estudiante actualizado correctamente.");
            } else {
                System.out.println("No se encontró estudiante con ese correo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String correo) {
        String sql = "DELETE FROM Estudiantes WHERE correo = ?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Estudiante eliminado correctamente.");
            } else {
                System.out.println("No se encontró estudiante con ese correo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estudiante> obtenerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estudiantes";

        try (Connection conn = Conexion.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante est = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        rs.getString("estado_civil"));
                lista.add(est);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Estudiante buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM Estudiantes WHERE correo = ?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        rs.getString("estado_civil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
