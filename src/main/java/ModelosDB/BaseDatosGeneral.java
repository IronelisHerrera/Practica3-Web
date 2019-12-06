package ModelosDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseDatosGeneral {



        private static BaseDatosGeneral baseDatos;
        private String URL = "jdbc:h2:~/BlogWeb";


        // Consigue una instancia de la base de datos en el caso de que no exista.

        public static BaseDatosGeneral getInstancia() {
            if (baseDatos == null)
                baseDatos = new BaseDatosGeneral();

            return baseDatos;
        }

        // Consigue una conexión de la base de datos para ejeuctar statements y demás.

        public Connection getConexion() {
            Connection conexion = null;

            try {
                conexion = DriverManager.getConnection(URL, "sa", "");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return conexion;
        }

        // Prueba la conexión con la base de datos para probar que la aplicación pueda correr correctamente

        public void testConexion() {
            try {
                getConexion().close();
                System.out.println("Conexión realizado con exito...");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }




}
