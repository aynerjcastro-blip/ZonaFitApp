package zona_fit.zonafit.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ayner Jose Castro Benavdides
 */
public class Conexion {
    public static Connection getConexion(){
        Connection conexion  = null;
        var basedatos = "zona_fit_db";
        /*Cadena de conexion para establecer para poder conectarse con MYSQL */
        var url = "jdbc:mysql://localhost:3306/"+ basedatos;
        var usuario = "root";
        var pasword = "admin";
        try {
            /*Clase de Conexion a la base de datos */
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*Realizando conexion mediante DriverManager y getConnection */
            conexion = DriverManager.getConnection(url,usuario,pasword);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectarnos a la base de datos: "+e.getMessage());
        }
        return conexion;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion !=null){
            System.out.println("Conexion exitosa:"+conexion);
        }
        else{
            System.out.println("Error al conectarse a la base de datos: "+conexion);
        }   
    }
}
