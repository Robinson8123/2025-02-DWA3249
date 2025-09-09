import com.mysql.cj.conf.PropertyDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonaServices {
    public void obtenerNumeroPersonas(Connection conn) throws SQLException{
        String sql="SELECT * FROM Persona";
        var stm =conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int cont=0;
        while (rs.next()){
            System.out.print(".");
            cont++;
        }
        System.out.println("Cantidad de registros encontrados " + cont);
    }

    public void insertarPersona(Connection conn) throws SQLException{
        String sql="INSERT INTO Persona (Nombre, Apellido, EstadoCivil) VALUES ('Jim', 'Carrey', 'Casado')";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro insertado de forma correcta");
        }
        else {
            System.out.println("Error en insercion");
        }
    }

    public void actualizarPersona(Connection conn) throws SQLException{
        String sql="UPDATE Persona SET EstadoCivil='VIUDO' WHERE ID=10";
        var stm=conn.prepareStatement(sql);
        int rs = stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro actualizado de forma correcta");
        }
        else {
            System.out.println("Error en la actualizacion");
        }
    }

    public void eliminarPersona(Connection conn) throws SQLException{
        String sql="DELETE FROM Persona WHERE ID=10";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro eliminado de forma correcta");
        }
        else {
            System.out.println("Error en la eliminacion");
        }
    }

    public void insertarPersonaConValores(Connection conn) throws SQLException{
        Scanner in= new Scanner(System.in);
        System.out.println("Digite nombre de la persona: ");
        String nombre= in.nextLine();
        System.out.println("Digite apellido de la persona: ");
        String apellido=in.nextLine();
        System.out.println("Digite el estado civil de la persona: ");
        String estadoCivil=in.nextLine();
        String sql="INSERT INTO Persona (Nombre, Apellido, EstadoCivil) VALUES (?,?,?)";
        var stm=conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2,apellido);
        stm.setString(3,estadoCivil);
        int rs = stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro insertado de forma correcta");
        }
        else{
            System.out.println("Fallo en la insercion");
        }
        in.close();
    }

    public void actualizarPersonaConValores(Connection conn) throws SQLException{
        Scanner in =new Scanner(System.in);
        System.out.println("Digite el nuevo estado civil: ");
        String estadoCivil=in.nextLine();
        System.out.println("Digite el ID de la Persona a actualizar: ");
        int id= in.nextInt();
        String sql="UPDATE Persona SET EstadoCivil=? WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        //stm.setString(1, estadoCivil);
        //stm.setInt(2,id);
        stm.setObject(1, estadoCivil);
        stm.setObject(2,id);
        int rs = stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro actualizado de forma correcta");
        }
        else{
            System.out.println("Fallo en la actualizacion");
        }
        in.close();
    }

    public void eliminarPersonaConValores(Connection conn) throws SQLException{
        Scanner in=new Scanner(System.in);
        System.out.print("Digite el id de la persona a eliminar: ");
        int id=in.nextInt();
        String sql="DELETE FROM Persona WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        stm.setObject(1,id);
        int rs=stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro eliminado");
        }
        else {
            System.out.println("Eiminacion fallida");
        }
        in.close();
    }

    public void obtenerPersonasConValores(Connection conn) throws SQLException{
        String sql="SELECT * FROM Persona";
        var stm=conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String nombre = rs.getString("Nombre");
            String apellido = rs.getString("Apellido");
            String estadoCivil = rs.getString("EstadoCivil");
            System.out.println(String.format("Mi id es %d, mi nombre completo es %s %s y mi estado civil es %s",id,nombre,apellido,estadoCivil));
        }
        System.out.println("Consulta Finalizada");
    }
}
