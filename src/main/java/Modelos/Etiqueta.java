package Modelos;



public class Etiqueta {


    private long idEtiqueta;
    private String etiqueta;
    private boolean activo;
    private long idArticulo;





    public Etiqueta(long idEtiqueta, String etiqueta, boolean activo){
        this.idEtiqueta= idEtiqueta;
        this.etiqueta=etiqueta;
        this.activo= activo;
    }


    public Etiqueta(long idEtiqueta, String etiqueta, boolean activo, long idArticulo){
        this.idEtiqueta= idEtiqueta;
        this.etiqueta=etiqueta;
        this.activo= activo;
        this.idArticulo = idArticulo;
    }

    public Etiqueta(String etiqueta, boolean activo, long idArticulo){
        this.etiqueta=etiqueta;
        this.activo= activo;
        this.idArticulo = idArticulo;
    }


    public long getIdEtiqueta() {

        return idEtiqueta;
    }

    public void setIdEtiqueta(long idEtiqueta) {

        this.idEtiqueta = idEtiqueta;
    }

    public boolean isActivo() {

        return activo;
    }

    public void setActivo(boolean activo) {

        this.activo = activo;
    }


    public String getEtiqueta() {

        return etiqueta;
    }

    public void setEtiqueta(String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }
}
