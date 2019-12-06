package ModelosDB;

import Modelos.Comentario;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentarioDB {
    public static List<Comentario> listaComentarios(long idArt) {
        Long autor;
        String idArticulo;
        Usuario autorUsu;
        List<Comentario> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from comentarios where ARTICULO_ID = ?";
            con = BaseDatosGeneral.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setLong(1, idArt);

            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comentario com = new Comentario();
                com.setId(rs.getLong("id"));
                com.setComentario(rs.getString("comentario"));
                autor = rs.getLong("autor_id");
                com.setAutor(UsuarioDB.getUsuarioByID(autor));
                idArticulo = rs.getString("articulo_id");
                com.setArti(ArticuloDB.BucarArticulo(idArticulo));

                lista.add(com);
                //Controladora.getInstance().getMisComentarios().add(com);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    /**
     * Retorna un estudiante dado su matricula
     * @param id
     * @return
     */
    public Comentario getComentario(int id) throws Exception {
        String autor;
        long idArticulo;
        Comentario comentario = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from comentarios where id = ?";
            con = BaseDatosGeneral.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comentario com = new Comentario();
                com.setId(rs.getLong("id"));
                com.setComentario(rs.getString("comentario"));
                autor = rs.getString("autor");
                com.setAutor(UsuarioDB.getUsuario(autor));
                idArticulo = rs.getLong("articulo");
                com.setArti(ArticuloDB.BucarArticuloByID(idArticulo));
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

        return comentario;
    }

    /**
     *
     * @param comentario
     * @return
     */
    public boolean crearComentario(Comentario comentario){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into comentarios(comentario, autor_id, articulo_id) values(?,?,?)";
            con = BaseDatosGeneral.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, comentario.getComentario());
            prepareStatement.setLong(2, comentario.getAutor().getIdUsuario());
            prepareStatement.setLong(3, comentario.getArti().getId());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }


}
