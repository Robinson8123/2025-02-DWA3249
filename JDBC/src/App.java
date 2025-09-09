import java.sql.Connection;
import java.sql.DriverManager;

public class App {
     static String url= "jdbc:mysql://localhost:3306/TestDB";
     static String userName="root";
     static String password="Pass_123";

    public static void main(String[] args){
        try(Connection conn= DriverManager.getConnection(url, userName, password)){
            PersonaServices personaServices=new PersonaServices();

            //personaServices.obtenerNumeroPersonas(conn);
            //personaServices.insertarPersona(conn);
            //personaServices.actualizarPersona(conn);
            personaServices.eliminarPersona(conn);
            //personaServices.insertarPersonaConValores(conn);
            //personaServices.actualizarPersonaConValores(conn);
            //personaServices.eliminarPersonaConValores(conn);
            //personaServices.obtenerPersonasConValores(conn);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
