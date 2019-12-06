package ModelosDB;

import Modelos.Usuario;
import org.jasypt.util.text.StrongTextEncryptor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDB {


    public static void InsertarUsuario(Usuario user) throws SQLException {


            Connection con = BaseDatosGeneral.getInstancia().getConexion();
            String Insert = "insert into USUARIOS(ID, USERNAME, NOMBRE, PASSWORD, ADMINISTRADOR, AUTOR, ACTIVO) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement insertStatement = con.prepareStatement(Insert);
            insertStatement.setLong(1, user.getIdUsuario());
            insertStatement.setNString(2, user.getUsername());
            insertStatement.setNString(3, user.getName());
            insertStatement.setNString(4, user.getPassword());
            insertStatement.setBoolean(5, user.isAdministrador());
            insertStatement.setBoolean(6, user.isAutor());

            insertStatement.setBoolean(7, user.isActivo());

            insertStatement.executeUpdate();

            insertStatement.close();



    }


    public static Usuario getUsuario(String username) {
        Usuario usu = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from usuarios where username = ?";
            con = BaseDatosGeneral.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, username);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                usu = new Usuario();
                usu.setIdUsuario(rs.getLong("id"));
                usu.setUsername(rs.getString("username"));
                usu.setName(rs.getString("nombre"));
                usu.setPassword(rs.getString("password"));
                usu.setAdministrador(rs.getBoolean("administrador"));
                usu.setAutor(rs.getBoolean("autor"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return usu;
    }

    public static Usuario getUsuarioByID(Long id) {
        Usuario usu = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from usuarios where id = ?";
            con = BaseDatosGeneral.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                usu = new Usuario();
                usu.setIdUsuario(rs.getLong("id"));
                usu.setUsername(rs.getString("username"));
                usu.setName(rs.getString("nombre"));
                usu.setPassword(rs.getString("password"));
                usu.setAdministrador(rs.getBoolean("administrador"));
                usu.setAutor(rs.getBoolean("autor"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return usu;
    }

    //Contar cantidad de filas en la bd

    public static Long ContarFilas() throws SQLException {
        Long  cant= new Long(0);


        Connection db = BaseDatosGeneral.getInstancia().getConexion();
        Statement stmt = db.createStatement();
        String top = "select top 1 * from USUARIOS order by ID desc ";
        ResultSet rs = stmt.executeQuery(top);
        while(rs.next()){

            cant = rs.getLong("ID");


        }

        db.close();
        stmt.close();

        return cant;
    }


    public static Usuario VerificarSiExiste(String name, String password) throws SQLException {

        StrongTextEncryptor strong = new StrongTextEncryptor();
        strong.setPassword("xdxd178245");
        String encrypt2 = strong.decrypt(password);

        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String query = ("select * from Usuarios where username = '" + name +"'");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
         while (rs.next()){
            Usuario user = new Usuario(rs.getLong("ID"),rs.getNString("USERNAME"), rs.getNString("NOMBRE"), rs.getNString("PASSWORD"),
                    rs.getBoolean("ADMINISTRADOR"), rs.getBoolean("AUTOR"));

            System.out.println(strong.decrypt(rs.getNString("PASSWORD")));

            if(encrypt2.equals(strong.decrypt(rs.getNString("PASSWORD"))))
            {
                return user;
            }
         }
        con.close();
        stmt.close();

        return null;

    }

    public static void Sesiones(String sesion, long idUsuario) throws SQLException {

        //DataBaseConection
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String Q =  "UPDATE usuarios \n" + "SET SESIONES='" + sesion + "' WHERE id=" + idUsuario + " ;";

        PreparedStatement stmt = con.prepareStatement(Q);
        stmt.executeUpdate();
        con.close();
        stmt.close();

    }

    //BuscarUsuario con una sesion

    public static Usuario UsuarioConSesion(String sesion) throws SQLException {
        Usuario user;
       Connection con= BaseDatosGeneral.getInstancia().getConexion();

       Statement stmt = con.createStatement();
       String Q = ("select * from Usuarios where SESIONES = '" + sesion +"';");
       ResultSet rs = stmt.executeQuery(Q);
       while (rs.next()){

           user = new Usuario(rs.getLong("ID"), rs.getNString("USERNAME"),
                   rs.getNString("NOMBRE"), rs.getNString("PASSWORD"), rs.getBoolean("ADMINISTRADOR"),
                   rs.getBoolean("AUTOR"));
           return user;

       }

       return null;
    }

    //Buscar usuario, ya que no me lo está actualizando en la db


    public static Usuario Nuevousuario(Long inc, String username, String name, String pass, boolean a, boolean au) throws SQLException {

        Connection con = BaseDatosGeneral.getInstancia().getConexion();


        String u = "insert into usuarios(id, username, nombre, password, administrador, autor, activo, SESIONES) values(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepareStatement = con.prepareStatement(u);
        prepareStatement.setLong(1, inc);
        prepareStatement.setString(2, username);
        prepareStatement.setString(3, name);
        prepareStatement.setString(4, pass);
        prepareStatement.setBoolean(5, a);
        prepareStatement.setBoolean(6, au);
        prepareStatement.setBoolean(7, false);
        prepareStatement.setString(8, null);
        prepareStatement.executeUpdate();

        return null;

    }
}
