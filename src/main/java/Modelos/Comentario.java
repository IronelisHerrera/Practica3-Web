package Modelos;


public class Comentario {


    private Long Id;
    private String comentario;
    private Usuario autor;
    private Articulo arti;

    public Comentario(String comentario, Usuario autor,  Articulo arti){

        this.comentario = comentario;
        this.autor= autor;
        this.arti=arti;

    }

    public Comentario(){

    }

    public Articulo getArti() {
        return arti;
    }

    public void setArti(Articulo arti) {
        this.arti = arti;
    }

    public Long getId(){
        return Id;
    }

    public void setId(Long id){
        this.Id= id;
    }

    public String getComentario(){
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor){
        this.autor = autor;
    }
}
