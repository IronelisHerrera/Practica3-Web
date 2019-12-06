package ModelosDB;

import Modelos.Articulo;
import Modelos.Etiqueta;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ArticuloDB {


    public static Articulo guardarArticulo(String t, String c, long iu, Timestamp f, boolean ac) throws SQLException {
        //insertar
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "insert into ARTICULOS(TITULO, CUERPO, USUARIOID, FECHA, ACTIVO) VALUES(?,?,?,?,?)";
        PreparedStatement prepareStatement = con.prepareStatement(q);
        prepareStatement.setString(1,t );
        prepareStatement.setString(2, c);
        prepareStatement.setLong(3, iu);
        prepareStatement.setTimestamp(4, f);
        prepareStatement.setBoolean(5, ac);

        prepareStatement.executeUpdate();




      return null;

    }

    public static Articulo editarArticulo(String titulo, String cuerpo) throws Exception {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "update ARTICULOS set cuerpo = ? where TITULO = ?";
        PreparedStatement prepareStatement = con.prepareStatement(q);
        prepareStatement.setString(1, cuerpo);
        prepareStatement.setString(2, titulo);

        prepareStatement.executeUpdate();

        return null;
    }

    public static void borrarEtiquetas(String id) throws Exception
    {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "delete from ETIQUETAS where ID_ARTICULO = ?";
        PreparedStatement prepareStatement = con.prepareStatement(q);
        prepareStatement.setString(1, id);

        prepareStatement.executeUpdate();

    }

    public static void borrarComentarios(Long id) throws Exception
    {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "delete from COMENTARIOS where ARTICULO_ID = ?";
        PreparedStatement prepareStatement = con.prepareStatement(q);
        prepareStatement.setLong(1, id);

        prepareStatement.executeUpdate();

    }

    public static void borrarComentarioEspecifico(Long id, Long idComment) throws Exception
    {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "delete from COMENTARIOS where ARTICULO_ID = ? and ID = ?";
        PreparedStatement prepareStatement = con.prepareStatement(q);
        prepareStatement.setLong(1, id);
        prepareStatement.setLong(2, idComment);

        prepareStatement.executeUpdate();

    }

    public static void borrarArticulo(Long id) throws Exception
    {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "delete from ARTICULOS where ID = ?";
        PreparedStatement prepareStatement = con.prepareStatement(q);
        prepareStatement.setLong(1, id);

        prepareStatement.executeUpdate();

    }

//    public static void resetIDEtq() throws Exception {
//        Connection con = BaseDatosGeneral.getInstancia().getConexion();
//        String q = "ALTER TABLE ETIQUETAS ALTER COLUMN id RESTART WITH 1";
//        PreparedStatement prepareStatement = con.prepareStatement(q);
//        prepareStatement.executeUpdate();
//    }

    public static List<Articulo> listaArticulo() throws Exception {
        List<Articulo> articulos = new ArrayList<>();
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String sql = "SELECT USUARIOID, ID, TITULO, CUERPO, FECHA, ACTIVO FROM ARTICULOS order by FECHA desc ;";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            //Articulo articulo = new Articulo(String.valueOf(rs.getLong("USUARIOID")), rs.getString("ID"), rs.getString("TITULO"), rs.getString("CUERPO"), rs.getDate("FECHA"));
            Articulo articulo = new Articulo(String.valueOf(rs.getLong("USUARIOID")), rs.getLong("ID"),
                    rs.getString("TITULO"), rs.getString("CUERPO"), rs.getTimestamp("FECHA"),
                    listEtiquetas(rs.getLong("ID")));
            articulos.add(articulo);


        }

        return articulos;

    }




    public static List<Etiqueta> listEtiquetas(long idArticulo) throws Exception{
        List<Etiqueta> etiquetas = new ArrayList<>();
        System.out.println(etiquetas.size());
        Connection con = BaseDatosGeneral.getInstancia().getConexion();

        String sql = "select  ETIQUETAS.ID,  etiqueta, ETIQUETAS.ACTIVO from ETIQUETAS INNER JOIN " +
                "ARTICULOS ON ETIQUETAS.ID_ARTICULO= ARTICULOS.ID WHERE id_articulo = '"+idArticulo+"'  and ETIQUETAS.activo = true ";
        System.out.println(sql);
        PreparedStatement ps = con.prepareStatement(sql);


        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Etiqueta e = new Etiqueta(rs.getLong("ID"), rs.getString("ETIQUETA"), rs.getBoolean("ACTIVO"));
            //System.out.println("Etiqueta: " + e.getEtiqueta());

            etiquetas.add(e);


        }
        System.out.println(etiquetas.size());
        return etiquetas;
    }


    public static Articulo BucarArticulo(String titulo) throws Exception {

        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String sql = "SELECT * FROM ARTICULOS WHERE titulo = '"+titulo+"'";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet r = st.executeQuery();
        Articulo articulo = null;
        while(r.next()){

            articulo = new Articulo(String.valueOf(r.getLong("USUARIOID")), r.getLong("ID"), r.getString("TITULO"), r.getString("CUERPO"), r.getTimestamp("FECHA"), listEtiquetas(r.getLong("ID")));

        }

        return articulo;
    }

    public static Articulo BucarArticuloByID(Long id) throws Exception {

        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String sql = "SELECT * FROM ARTICULOS WHERE id = '"+id+"'";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet r = st.executeQuery();
        Articulo articulo = null;
        while(r.next()){

            articulo = new Articulo(String.valueOf(r.getLong("USUARIOID")), r.getLong("ID"), r.getString("TITULO"), r.getString("CUERPO"), r.getTimestamp("FECHA"), listEtiquetas(r.getLong("ID")));

        }

        return articulo;
    }


    /*
    public static List<Articulo> listaArticulo(Long usuarioLogueado) throws Exception {
        List<Articulo> articulos = new ArrayList<>();
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String sql = String.format("SELECT USUARIOID, ID, TITULO, CUERPO, FECHA, ACTIVO FROM ARTICULOS WHERE USUARIOID = %s", usuarioLogueado);

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Articulo articulo = new Articulo(String.valueOf(rs.getLong("USUARIOID")), rs.getString("ID"), rs.getString("TITULO"), rs.getString("CUERPO"), rs.getDate("FECHA"), listEtiquetas(rs.getString("ID")));
            articulos.add(articulo);
        }

        return articulos;

    }

     */
}
