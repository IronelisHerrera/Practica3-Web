package ModelosDB;


import Modelos.Etiqueta;

import java.sql.*;


public class EtiquetaDB {


    public static Etiqueta insertarEtiqueta(Etiqueta e) throws SQLException {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q = "insert into ETIQUETAS(ETIQUETA, ACTIVO, ID_ARTICULO) VALUES(?,?, ?)";
        PreparedStatement p = con.prepareStatement(q);
        p.setString(1, e.getEtiqueta());
        p.setBoolean(2, e.isActivo());
        p.setLong(3, e.getIdArticulo());
        p.executeUpdate();

        return e;
    }
/*
    public static List<Etiqueta> listaEtiqueta() throws SQLException {
        List<Etiqueta> et = new ArrayList<>();
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String sql = "SELECT ID, ETIQUETA, ACTIVO FROM ETIQUETAS;";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Etiqueta e = new Etiqueta((rs.getString("ID")), rs.getString("ETIQUETA"), rs.getBoolean("ACTIVO"));
            et.add(e);
        }

        return et;

    }
*/

/*
    public static Long contarEtiqueta() throws SQLException {
        Connection con = BaseDatosGeneral.getInstancia().getConexion();
        String q= "SELECT TOP 1 * FROM ETIQUETAS order by ID desc";
        PreparedStatement p = con.prepareStatement(q);
        ResultSet r = p.executeQuery();
        Long cant = new Long(0);
        while(r.next()){
            cant = r.getLong("ID");


        }

        return cant;

    }

*/


}
