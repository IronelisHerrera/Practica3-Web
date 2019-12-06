package ModelosDB;

import org.sql2o.Sql2o;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatosTablas {

   //Transacciones
    public static void iniciarBaseDatos() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }



    public static void detenerBaseDatos() throws SQLException {
        Server.shutdownTcpServer("jdbc:h2:~/BlogWeb", "", true, true);
    }

    //Para las consultas pasadas por parametros




    public static void crearTablas() throws SQLException {



        String sql = "CREATE TABLE IF NOT EXISTS USUARIOS (\n" +
                        "ID BIGINT  PRIMARY KEY auto_increment,\n" +
                        "USERNAME VARCHAR2(50) UNIQUE NOT NULL, \n" +
                        "NOMBRE VARCHAR2(150),\n"+
                        "PASSWORD VARCHAR2(40) NOT NULL, \n" +
                        "ADMINISTRADOR BOOLEAN NOT NULL, \n" +
                        "AUTOR BOOLEAN NOT NULL, \n" +
                        "ACTIVO BOOLEAN, \n"+
                        "SESIONES VARCHAR2(255)"+
                        ");\n " +




                        "CREATE TABLE IF NOT EXISTS ARTICULOS (\n" +

                        "ID BIGINT PRIMARY KEY auto_increment,\n" +
                        "TITULO VARCHAR2(100) UNIQUE NOT NULL, \n" +
                        "CUERPO VARCHAR2(750000) NOT NULL, \n" +
                        "USUARIOID BIGINT REFERENCES USUARIOS(ID) ON UPDATE CASCADE, \n" +
                        "FECHA TIMESTAMP NOT NULL, \n" +
                        "ACTIVO BOOLEAN \n"+
                        "); \n"+

                        "CREATE TABLE IF NOT EXISTS ETIQUETAS (\n" +

                        "ID BIGINT PRIMARY KEY auto_increment,\n" +
                        "ETIQUETA VARCHAR2(250) NOT NULL,\n" +
                        "ACTIVO BOOLEAN, \n"+
                         "ID_ARTICULO VARCHAR(36) REFERENCES ARTICULOS(ID) ON UPDATE CASCADE\n"+
                        ");\n "+


                        "CREATE TABLE IF NOT EXISTS COMENTARIOS(\n" +
                        "ID BIGINT PRIMARY KEY auto_increment,\n" +
                        "COMENTARIO VARCHAR2(2000) NOT NULL, \n" +
                        "AUTOR_ID BIGINT REFERENCES USUARIOS (ID) ON UPDATE CASCADE,\n"+
                        "ARTICULO_ID BIGINT REFERENCES ARTICULOS (ID) ON UPDATE CASCADE,\n"+
                        "ACTIVO BOOLEAN \n "+
                        ");\n"+

                        "CREATE TABLE IF NOT EXISTS ARTICULO_DE_COMENTARIOS(\n" +

                         "ID_ARTICULO BIGINT REFERENCES ARTICULOS (ID) ON UPDATE CASCADE,\n"+
                         "ID_COMENTARIO BIGINT REFERENCES COMENTARIOS (ID) ON UPDATE CASCADE,\n "+
                         "ACTIVO BOOLEAN \n"+
                          "  );\n"+

                        "CREATE TABLE IF NOT EXISTS ARTICULO_DE_ETIQUETAS (\n" +
                        "ID_ARTICULO BIGINT REFERENCES ARTICULOS (ID) ON UPDATE CASCADE,\n"+
                         "ID_ETIQUETA BIGINT REFERENCES ETIQUETAS (ID) ON UPDATE CASCADE,\n"+
                        "ACTIVO BOOLEAN \n"+
                        "  );\n"+
                        "\n";
        Connection conexion = BaseDatosGeneral.getInstancia().getConexion();

        Statement statement = conexion.createStatement();

        statement.execute(sql);
        statement.close();

        conexion.close();
    }


}