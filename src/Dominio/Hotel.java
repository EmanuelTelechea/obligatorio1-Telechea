package Dominio;

public class Hotel {
    private int idHotel;
    private String nombre;
    private String ciudad;
    private String pais;
    private int estrellas;
    private String direccion;
    private String zona;

    public Hotel(int idHotel, String nombre, String ciudad, String pais, int estrellas, String direccion, String zona) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.estrellas = estrellas;
        this.direccion = direccion;
        this.zona = zona;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", estrellas=" + estrellas +
                ", direccion='" + direccion + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}

