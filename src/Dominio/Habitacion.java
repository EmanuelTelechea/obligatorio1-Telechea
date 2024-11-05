package Dominio;

public class Habitacion {
    private int idHabitacion;
    private Hotel hotel;
    private int numCamas;
    private boolean camaMatrimonial;
    private boolean aireAcondicionado;
    private boolean balcon;
    private boolean vista;
    private boolean amenities;
    private boolean ocupada;

    public Habitacion(int idHabitacion, Hotel hotel, int numCamas, boolean camaMatrimonial, boolean aireAcondicionado, boolean balcon,
                      boolean vista, boolean amenities) {
        this.idHabitacion = idHabitacion;
        this.hotel=hotel;
        this.numCamas = numCamas;
        this.camaMatrimonial = camaMatrimonial;
        this.aireAcondicionado = aireAcondicionado;
        this.balcon = balcon;
        this.vista = vista;
        this.amenities = amenities;
        this.ocupada = false;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public boolean isCamaMatrimonial() {
        return camaMatrimonial;
    }

    public void setCamaMatrimonial(boolean camaMatrimonial) {
        this.camaMatrimonial = camaMatrimonial;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

    public boolean isAmenities() {
        return amenities;
    }

    public void setAmenities(boolean amenities) {
        this.amenities = amenities;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", numCamas=" + numCamas +
                ", camaMatrimonial=" + camaMatrimonial +
                ", aireAcondicionado=" + aireAcondicionado +
                ", balcon=" + balcon +
                ", vista=" + vista +
                ", amenities=" + amenities +
                ", ocupada=" + ocupada +
                '}';
    }
}

