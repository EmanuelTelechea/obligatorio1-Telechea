package Dominio;

import java.time.LocalDate;
import java.util.Date;

public class Huesped {
    private int idhuesped;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String tipo_documento;
    private int num_documento;
    private LocalDate fecha_nacimiento;
    private int telefono;
    private String pais;

    public int getIdhuesped() {
        return idhuesped;
    }

    public void setIdhuesped(int idhuesped) {
        this.idhuesped = idhuesped;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(int num_documento) {
        this.num_documento = num_documento;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Huesped(int idhuesped, String nombre, String apaterno, String amaterno, String tipo_documento, int num_documento, LocalDate fecha_nacimiento, int telefono, String pais) {
        this.idhuesped = idhuesped;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "idhuesped=" + idhuesped +
                ", nombre='" + nombre + '\'' +
                ", apaterno='" + apaterno + '\'' +
                ", amaterno='" + amaterno + '\'' +
                ", tipo_documento='" + tipo_documento + '\'' +
                ", num_documento=" + num_documento +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", telefono=" + telefono +
                ", pais='" + pais + '\'' +
                '}';
    }
}
