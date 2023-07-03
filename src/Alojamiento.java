import java.io.*;
import java.util.ArrayList;


public class Alojamiento {
    private int idAlojamiento;
    private double precio;
    private int capacidad;
    private String ciudad;
    private String direccion;
    private Boolean estado;
    private String imagen;

    public Alojamiento(int idAlojamiento, double precio, int capacidad, String ciudad, String direccion, String imagen) {
        this.idAlojamiento = idAlojamiento;
        this.precio = precio;
        this.capacidad = capacidad;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.estado = true;
        this.imagen = imagen;
    }
    public void reservar() {
        this.estado = false;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public double getPrecio() {

        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return  "\n"+
                "Numero de Alojamiento: " + idAlojamiento +"\n"+
                "     "+ imagen + "\n" +
                "*Precio por dia: " + precio + "\n" +
                "*Capacidad: " + capacidad + " personas"+ "\n"+
                "*Ciudad: " + ciudad + "\n"+
                "*Direccion: " + direccion + "\n"+
                "*Se encuentra disponible: " + estado +
                " ";
    }
}
