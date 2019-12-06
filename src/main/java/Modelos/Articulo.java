package Modelos;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Articulo {

    private Long id;
    private String IdAutor;
    private String titulo;
    private String cuerpo;
    private Usuario autor;
    private Timestamp fecha;
    private List<Comentario> arregloComents;
    private List<Etiqueta> arregloEtiquetas;

    public Articulo(String IdAutor, Long id, String titulo, String cuerpo, Usuario autor, Timestamp fecha, ArrayList<Comentario> arregloComents, ArrayList<Etiqueta> arregloEtiquetas){

        this.arregloComents = arregloComents;
        this.arregloEtiquetas= arregloEtiquetas;
        this.id = id;
        this.fecha = fecha;
        this.cuerpo= cuerpo;
        this.titulo= titulo;
        this.IdAutor= IdAutor;
        this.autor = autor;

    }


    public Articulo(String IdAutor, Long id, String titulo, String cuerpo, Timestamp fecha, List<Etiqueta> etiquetas){
        this.id = id;
        this.fecha = fecha;
        this.cuerpo= cuerpo;
        this.titulo= titulo;
        this.IdAutor= IdAutor;
        this.arregloEtiquetas = etiquetas;
    }

    public Articulo(String usuarioid, Long id, String titulo, String cuerpo, Timestamp fecha) {
        this.id = id;
        this.fecha = fecha;
        this.cuerpo= cuerpo;
        this.titulo= titulo;
        this.IdAutor= usuarioid;

    }

    public Articulo(String usuarioid, String titulo, String cuerpo, Timestamp fecha) {
        this.fecha = fecha;
        this.cuerpo= cuerpo;
        this.titulo= titulo;
        this.IdAutor= usuarioid;

    }

    public String getIdAutor() {

        return IdAutor;
    }

    public void setIdAutor(String idAutor) {

        this.IdAutor = IdAutor;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }


    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo)
    {

        this.titulo= titulo;
    }

    public String getCuerpo() {

        return cuerpo;
    }

    public void setCuerpo(String cuerpo)
    {

        this.cuerpo= cuerpo;
    }

    public Usuario getAutor(){
        return autor;
    }

    public void setAutor(Usuario autor){
        this.autor= autor;
    }

    public Timestamp getFecha(){
        return fecha;
    }

    public void setFecha(Timestamp fecha){
        this.fecha= fecha;
    }

    public List<Comentario> getArregloComents() {

        return arregloComents;
    }

    public void setArregloComents(List<Comentario> arregloComents) {
        this.arregloComents = arregloComents;
    }

    public List<Etiqueta> getArregloEtiquetas() {
        return arregloEtiquetas;
    }

    public void setArregloEtiquetas(List<Etiqueta> arregloEtiquetas) {
        this.arregloEtiquetas = arregloEtiquetas;
    }
}
