package Persistencia;

import Dominio.Habitacion;
import Dominio.Hotel;
import Dominio.Huesped;
import Dominio.Reserva;
import Persistencia.Conexion;
import Utils.AppSQLException;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PReserva {
    private static Conexion conexion = new Conexion();

    public static boolean agregarReserva(Reserva r) {
        String sql = "INSERT INTO reserva(idReserva, idhuesped, idhabitacion, fechaReserva, fechaEntrada, fechaSalida, pagada, costoTotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                r.getIdReserva(),
                r.getHuesped().getIdhuesped(),
                r.getHabitacion().getIdHabitacion(),
                r.getFechaReserva(),
                r.getFechaEntrada(),
                r.getFechaSalida(),
                r.isPagada(),
                r.getCostoTotal()
        ));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se agregó la reserva con éxito");
                return true;
            }
            System.out.println("Existió un problema al agregar la reserva");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean eliminarReserva(int idReserva) {
        String sql = "DELETE FROM reserva WHERE idReserva = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idReserva));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se eliminó la reserva con éxito");
                return true;
            }
            System.out.println("Existió un problema al eliminar la reserva");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Reserva> listarReservas() {
        String sql = "SELECT idReserva, idhuesped, idhabitacion, fechaReserva, fechaEntrada, fechaSalida, pagada, costoTotal FROM reserva";
        try {
        List<List<Object>> resultado = conexion.seleccion(sql, null);
        ArrayList<Reserva> reservas = new ArrayList<>();
        for (List<Object> registro : resultado) {
            int idReserva = (int) registro.get(0);
            int idHuesped = (int) registro.get(1);
            Huesped huesped = PHuesped.buscarHuesped(idHuesped);
            int idHabitacion = (int) registro.get(2);
            Habitacion habitacion = PHabitacion.buscarHabitacion(idHabitacion);
            LocalDate fechaReserva = registro.get(3) != null ? ((java.sql.Date) registro.get(3)).toLocalDate() : null;
            LocalDate fechaEntrada = registro.get(4) != null ? ((java.sql.Date) registro.get(4)).toLocalDate() : null;
            LocalDate fechaSalida = registro.get(5) != null ? ((java.sql.Date) registro.get(5)).toLocalDate() : null;
            boolean pagada = (boolean) registro.get(6);
            BigDecimal costoTotal = (BigDecimal) registro.get(7) ;

            reservas.add(new Reserva(huesped, habitacion, fechaReserva, fechaEntrada, fechaSalida, pagada, costoTotal));
        }
        return reservas;
        }
            catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
            return null;
        }

    public static boolean modificarReserva(Reserva r) {
        String sql = "UPDATE reserva SET idhuesped = ?, idhabitacion = ?, fechaReserva = ?, fechaEntrada = ?, fechaSalida = ?, pagada = ? WHERE idReserva = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                r.getHuesped().getIdhuesped(),
                r.getHabitacion().getIdHabitacion(),
                r.getFechaReserva(),
                r.getFechaEntrada(),
                r.getFechaSalida(),
                r.isPagada(),
                r.getIdReserva()
        ));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se modificó la huesped con éxito");
                return true;
            }
            System.out.println("Existió un problema al modificar la huesped");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Reserva buscarReserva(int idReserva) {
        String sql = "SELECT * FROM reserva WHERE idReserva = ?";
        try {
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idReserva));
        for (List<Object> registro : conexion.seleccion(sql, parametros)) {
            int idHuesped = (int) registro.get(0);
            Huesped huesped = PHuesped.buscarHuesped(idHuesped);
            int idHabitacion = (int) registro.get(1);
            Habitacion habitacion = PHabitacion.buscarHabitacion(idHabitacion);
            LocalDate fechaReserva = registro.get(2) != null ? ((java.sql.Date) registro.get(3)).toLocalDate() : null;
            LocalDate fechaEntrada = registro.get(3) != null ? ((java.sql.Date) registro.get(4)).toLocalDate() : null;
            LocalDate fechaSalida = registro.get(4) != null ? ((java.sql.Date) registro.get(5)).toLocalDate() : null;
            boolean pagada = (boolean) registro.get(5);
            BigDecimal costoTotal = (BigDecimal) registro.get(7) ;

            return new Reserva(huesped, habitacion, fechaReserva, fechaEntrada, fechaSalida, pagada, costoTotal);
        }
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
            return null;
        }
}

