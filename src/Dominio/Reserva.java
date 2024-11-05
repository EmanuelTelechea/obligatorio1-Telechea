package Dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Reserva {
    private int idReserva;
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private boolean pagada;
    private BigDecimal costoTotal;
    private static int autonumerico = 6;

    public Reserva(Huesped huesped, Habitacion habitacion, LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida, boolean pagada, BigDecimal costoTotal) {
        this.idReserva = autonumerico;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaReserva = fechaReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.pagada = pagada;
        this.costoTotal = costoTotal;
        autonumerico++;
    }
    public Reserva(Huesped huesped, Habitacion habitacion, LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida, boolean pagada) {
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaReserva = fechaReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.pagada = pagada;
        this.costoTotal = BigDecimal.ZERO;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", huesped=" + huesped +
                ", habitacion=" + habitacion +
                ", fechaReserva='" + fechaReserva + '\'' +
                ", fechaEntrada='" + fechaEntrada + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", pagada=" + pagada +
                ", Precio=" + costoTotal +
                '}';
    }
}

