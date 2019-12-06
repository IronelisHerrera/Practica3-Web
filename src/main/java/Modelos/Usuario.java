package Modelos;

public class Usuario {

    private Long IdUsuario;
    private String username;
    private String name;
    private String password;
    private boolean administrador;
    private boolean autor;
    private String Sesiones;
    private boolean activo;

    public Usuario(Long idUsuario, String username, String name, String password, boolean administrador, boolean autor ){

        this.IdUsuario = idUsuario;
        this.username = username;
        this.name = name;
        this.password= password;
        this.administrador=administrador;
        this.autor= autor;


    }

    public Usuario(){

    }


    //public Usuario(long id, String username, String nombre, String password, boolean administrador) {
   // }

    public boolean isActivo(){
        return activo;
    }
    public void setActivo(boolean activo){
        this.activo = activo;
    }

    public String getSesiones(){
        return Sesiones;
    }

    public void setSesiones(String sesiones) {
        this.Sesiones = sesiones;
    }


    public Long getIdUsuario(){
        return IdUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password= password;
    }

    public  boolean isAutor(){
        return autor;
    }

    public void setAutor(boolean autor){
        this.autor = autor;
    }

    public boolean isAdministrador(){
        return administrador;
    }

    public void setAdministrador(boolean Administrador){
        this.administrador = Administrador;
    }

}
