
import Modelos.Articulo;
import Modelos.Comentario;
import Modelos.Etiqueta;
import Modelos.Usuario;
import ModelosDB.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.jasypt.util.text.StrongTextEncryptor;
import spark.ModelAndView;
import spark.Request;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;


import java.io.IOException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

import static spark.Spark.*;


public class Main {

    static String UsuarioVerificar = "";
    private static List<Usuario> usuarios;
    private static List<Comentario> comentarios;
    private static List<Etiqueta> etiquetas;
    private static List<Articulo> articulos;

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(List<Usuario> usuarios) {
        Main.usuarios = usuarios;
    }

    public static List<Comentario> getComentarios() {
        return comentarios;
    }

    public static void setComentarios(List<Comentario> comentarios) {
        Main.comentarios = comentarios;
    }

    public static List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public static void setEtiquetas(List<Etiqueta> etiquetas) {
        Main.etiquetas = etiquetas;
    }

    public static List<Articulo> getArticulos() {
        return articulos;
    }

    public static void setArticulos(List<Articulo> articulos) {
        Main.articulos = articulos;
    }

    static Usuario u;


    public static void main(String[] args) throws IOException, SQLException {
        staticFiles.location("spark/template/freemarker");
        //String templatePath = new File("").getAbsolutePath();
        final Configuration config = new Configuration(new Version(2, 3, 0));
        config.setClassForTemplateLoading(Main.class, "/");
        config.setDefaultEncoding("UTF-8"); //Renderizar con tildes.


        //Para la Base de Datos y crear las tablas

        BaseDeDatosTablas.iniciarBaseDatos();
        BaseDatosGeneral.getInstancia().testConexion();

        //Crear tablas con el usuario por defecto
        BaseDeDatosTablas.crearTablas();

        //Usuario por defecto
        StrongTextEncryptor encrp = new StrongTextEncryptor();
        encrp.setPassword("xdxd178245");
        String pass = encrp.encrypt("admin");
        Usuario SetUser = new Usuario((long) 1, "batman", "admin", pass, true, false);
        if (UsuarioDB.ContarFilas() == 0) {
           UsuarioDB.InsertarUsuario(SetUser);
       }

        //filtro crear sesion despues de -/-
        //Verificar si el usuario es aceptado con el filtro before

        before("/", (request, response) -> {
            if (request.cookie("PorUnaSemana") != null) {
                StrongTextEncryptor encriptador = new StrongTextEncryptor();
                encriptador.setPassword("xdxd178245");
                String PorUnaSemana = encriptador.decrypt(request.cookie("PorUnaSemana"));
                Usuario user;
                user = UsuarioDB.UsuarioConSesion(PorUnaSemana);
                // UsuarioDB userSesion = new UsuarioDB();
//                UsuarioVerificar = user.getUsername();
//                u = user;
                request.session().attribute("sesionUsuario", user);

                if (user != null) {
                    request.session(true);
                    request.session().attribute("sesionUsuario", user);
                    //response.redirect("/");
                }


            }
        });


        get("/HomeLogin", (request, response) -> {

            Template MostrarTemplate = config.getTemplate("spark/template/freemarker/HomeLogin.ftl");
            return MostrarTemplate;

        });


        post("/HomeLogin", (request, response) -> {

            UsuarioVerificar = request.queryParams("usuario");
            String clave = request.queryParams("clave");

            StrongTextEncryptor strong = new StrongTextEncryptor();
            strong.setPassword("xdxd178245");
            String encrypt2 = strong.encrypt(clave);

            System.out.println(encrypt2);

            u = UsuarioDB.VerificarSiExiste(UsuarioVerificar, encrypt2);
            if (u != null) {
                //Entrar
                request.session().attribute("sesionUsuario", u);
                if (request.queryParams("recordarme") != null) {
                    String sesion = request.session().id();
                    StrongTextEncryptor e = new StrongTextEncryptor();
                    e.setPassword("xdxd178245");
                    String encrypt = e.encrypt(sesion);

                    response.cookie("/", "PorUnaSemana", encrypt, 604800000, false);

                    //Actualizar sesion para el usuario creado
                    UsuarioDB.Sesiones(request.session().id(), u.getIdUsuario());
                }

                response.redirect("/");
            } else {
                response.redirect("/HomeLogin");
            }

            return "";
        });


        get("/", (request, response) -> {

            Usuario user = request.session().attribute("sesionUsuario");
//            if (user == null) {
//                response.redirect("/HomeLogin");
//            }

            List<Articulo> articulos = ArticuloDB.listaArticulo();
            List<Comentario> comentarios;
            for (Articulo artic: articulos
                 ) {
                artic.setArregloComents(ComentarioDB.listaComentarios(artic.getId()));
            }
            setArticulos(articulos);

            //List<Etiqueta> etiquetas = EtiquetaDB.listaEtiqueta();
            for(int i =0; i<articulos.size(); i++){
              articulos.get(i).setArregloEtiquetas(ArticuloDB.listEtiquetas(articulos.get(i).getId()));
            }

            Map<String, Object> m = new HashMap<>();

            m.put("logueado", (request.session().attribute("sesionUsuario")));
            m.put("UsuarioVerificar", UsuarioVerificar);
            if(user != null)
            {
                m.put("Au", user.isAutor());
                m.put("Admin", user.isAdministrador());
            }

            m.put("articulos", articulos);
            return new FreeMarkerEngine().render(new ModelAndView(m, "Navbar.ftl"));

        });

        get("/AdministradorCreaUsuario", (request, response) -> {
            Template Navbar = config.getTemplate("spark/template/freemarker/NuevoUsuarioByAdmin.ftl");
            return Navbar;
        });


        post("/AdministradorCreaUsuario", (request, response) -> {

            String user = request.queryParams("usuario");
            String n = request.queryParams("nombre");
            String clave = request.queryParams("clave");

            StrongTextEncryptor e = new StrongTextEncryptor();
            e.setPassword("xdxd178245");
            String encrypt = e.encrypt(clave);

            Long inc = UsuarioDB.ContarFilas() + 1;
            //Verificar si el usuario existe
            Usuario u = UsuarioDB.VerificarSiExiste(user, encrypt);
            boolean a = request.queryParams("radio").equals("Administrador");
            boolean au = request.queryParams("radio").equals("Autor");
            if (a) {
                au = false;
            } else if (au) {
                a = false;
            }
            if (u == null) {
                u = UsuarioDB.Nuevousuario(inc, user, n, encrypt, a, au);
                System.out.println("Usuario creado");

                response.redirect("/");

            }
            //Crear funcion con permisos
            else {
                response.redirect("/");
            }
            return null;
        });

        //Revisar permisos UsuarioPermisos

        get("/UsuarioPermisos", (request, response) -> {


            Map<String, Object> m = new HashMap<>();
            m.put("logueado", (request.session().attribute("sesionUsuario") != null));
            UsuarioVerificar = request.queryParams("usuario");
            m.put("UsuarioVerificar", UsuarioVerificar);
            m.put("Au", u.isAutor());
            m.put("Admin", u.isAdministrador());

            return new FreeMarkerEngine().render(new ModelAndView(m, "Navbar.ftl"));


        });

        //Cerrar sesion

        get("/CerrarSesion", (request, response) -> {


            Session S = request.session(true);
            S.invalidate();
            response.removeCookie("PorUnaSemana");

            response.redirect("/");

            return null;
        });




        post("/NuevoArticulo", (request, response) -> {
            Usuario user = request.session().attribute("sesionUsuario");
            if (user.isAdministrador()||user.isAutor()) {
                String titulo = request.queryParams("titulo");
                String cuerpoTexto = request.queryParams("cuerpoTexto");
                String Tags = request.queryParams("Tags");
                List<String> T = Arrays.asList(Tags.split(","));
                Timestamp f = Timestamp.valueOf(LocalDateTime.now());
                long idU = u.getIdUsuario();
                ArticuloDB.guardarArticulo(titulo, cuerpoTexto, idU, f, true);
                long idA = ArticuloDB.BucarArticulo(titulo).getId();
                //String sql= "SELECT top 1 * from ARTICULOS order by ID desc";
                System.out.println("articulo insertado");
                ArrayList<Etiqueta> et =  new ArrayList<>();
                for(int i = 0; i < T.size(); i++){
                    Etiqueta e = new Etiqueta(T.get(i), true, idA);
                    et.add(e);
                    EtiquetaDB.insertarEtiqueta(e);

                }
                System.out.println("etiqueta insertada");
                response.redirect("/");
            }
            //No pueden hacer articulos los administradores

            return null;
        });


        //Este login está deprecated! No se usa.
        get("/loginAdministrador", (request, response) -> {
            Template MostrarTemplate = config.getTemplate("spark/template/freemarker/loginAdministrador.ftl");
            return MostrarTemplate;
        });

        get("/VerArticulo/:id", (request, response) -> {

            Usuario user = request.session().attribute("sesionUsuario");
            //String id = UUID.randomUUID().toString();
            Long idA = Long.parseLong(request.params("id"));
            System.out.println("No hay ID" +idA);

            Articulo art = ArticuloDB.BucarArticuloByID(idA);
            List<Comentario> comentarios = ComentarioDB.listaComentarios(idA);


            Map<String, Object> m = new HashMap<>();
            if(request.session().attribute("sesionUsuario") != null)
            {
                m.put("logueado", (request.session().attribute("sesionUsuario") != null));
                m.put("Au", user.isAutor());
                m.put("Admin", user.isAdministrador());
            }
            else {
                m.put("logueado", (request.session().attribute("sesionUsuario") == null));
                m.put("Au", null);
                m.put("Admin", null);
            }

            m.put("articulo", art);
            m.put("comentarios", comentarios);

            return new FreeMarkerEngine().render(new ModelAndView(m, "VerArticulo.ftl"));
        });


        get("/NuevoArticulo", (request, response) -> {
            Usuario user = request.session().attribute("sesionUsuario");
            if (user == null) {
                response.redirect("/");
            }
//            long idA = ArticuloDB.ContarArticulo() + 1;
//            System.out.println(idA);
            Template Navbar = config.getTemplate("spark/template/freemarker/NuevoArticulo.ftl");
            return Navbar;
        });

        post("/GuardarComentario/:id", (request, response) -> {
            Long id = Long.parseLong(request.params("id"));
            String comentario = request.queryParams("comentario");
            Articulo articulo = ArticuloDB.BucarArticuloByID(id);
            Session session=request.session(true);
            Usuario usuario = session.attribute("sesionUsuario");
            Comentario newComentario = new Comentario(comentario, usuario, articulo);

            new ComentarioDB().crearComentario(newComentario);
            response.redirect("/VerArticulo/"+id);
            return "";
        });

        post("/EditarArticulo/:id", (request, response) -> {

            Articulo art = ArticuloDB.BucarArticuloByID(Long.parseLong(request.params("id")));
            ArticuloDB.editarArticulo(art.getTitulo(), request.queryParams("cuerpoTexto"));
            ArticuloDB.borrarEtiquetas(art.getId().toString());
            String Tags = request.queryParams("Tags");
            long idA = art.getId();
            List<String> T = Arrays.asList(Tags.split(","));
            ArrayList<Etiqueta> et =  new ArrayList<>();

            for(int i = 0; i < T.size(); i++){
                Etiqueta e = new Etiqueta(T.get(i), true, idA);
                et.add(e);
                EtiquetaDB.insertarEtiqueta(e);

            }
            response.redirect("/VerArticulo/" + art.getId());
            return null;
        });

        get("/EliminarArticulo/:id", (request, response) -> {
            Articulo art = ArticuloDB.BucarArticuloByID(Long.parseLong(request.params("id")));
            ArticuloDB.borrarEtiquetas(art.getId().toString());
            ArticuloDB.borrarComentarios(art.getId());
            ArticuloDB.borrarArticulo(art.getId());
            response.redirect("/");
            return "";
        });

        get("/EliminarComentario/:id/:idComment", (request, response) -> {
            Articulo art = ArticuloDB.BucarArticuloByID(Long.parseLong(request.params("id")));
            ArticuloDB.borrarComentarioEspecifico(art.getId(), Long.parseLong(request.params("idComment")));
            response.redirect("/VerArticulo/" + art.getId());
            return "";
        });
        //TODO: lOS TAGS NO ME LOS MUESTRA, BITCHHH!! DONE :)
        //TODO: Cuando el Administrador crea un usuario, debe tener permisos de admin o autor -done
        //TODO: vista Para ver los Articulos en detalle con boton de editar y eliminar -done
        //TODO: Buscar un articulo by id - Done

    }
}
