package Persistencia;

import Dominio.Habitacion;
import Dominio.Hotel;
import Utils.AppSQLException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHabitacion {
    private static Conexion conexion = new Conexion();

    public static boolean agregarHabitacion(Habitacion h) {
        String sql = "INSERT INTO habitacion(idHabitacion, idHotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getIdHabitacion(),
                h.getHotel().getIdHotel(),
                h.getNumCamas(),
                h.isCamaMatrimonial(),
                h.isAireAcondicionado(),
                h.isBalcon(),
                h.isVista(),
                h.isAmenities(),
                h.isOcupada()
        ));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se agregó la habitacion con éxito");
                return true;
            }
            System.out.println("Existió un problema al agregar la habitacion");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;

    }

    public static boolean eliminarHabitacion(int idHabitacion) {
        String sql = "DELETE FROM habitacion WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idHabitacion));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se eliminó la habitacion con éxito");
                return true;
            }
            System.out.println("Existió un problema al eliminar la habitacion");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Habitacion> listarHabitacion() {
        String sql = "SELECT idHabitacion, idHotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada FROM habitacion";
        try {
        List<List<Object>> resultado = conexion.seleccion(sql, null);
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        for (List<Object> registro : resultado) {
            int idHabitacion = (int) registro.get(0);
            int idHotel = (int) registro.get(1);
            Hotel hotel = PHotel.buscarHotel(idHotel);
            int numCamas = (int) registro.get(2);
            boolean camaMatrimonial = (boolean) registro.get(3);
            boolean aireAcondicionado = (boolean) registro.get(4);
            boolean balcon = (boolean) registro.get(5);
            boolean vista = (boolean) registro.get(6);
            boolean amenities = (boolean) registro.get(7);
            boolean ocupada = (boolean) registro.get(8);

            habitaciones.add(new Habitacion(idHabitacion, hotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities));
            habitaciones.get(habitaciones.size() - 1).setOcupada(ocupada);
        }
        return habitaciones;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean modificarHabitacion(Habitacion h) {
        String sql = "UPDATE habitacion SET numCamas = ?, camaMatrimonial = ?, aireAcondicionado = ?, balcon = ?, vista = ?, amenities = ?, ocupada = ? WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getNumCamas(),
                h.isCamaMatrimonial(),
                h.isAireAcondicionado(),
                h.isBalcon(),
                h.isVista(),
                h.isAmenities(),
                h.isOcupada(),
                h.getIdHabitacion()
        ));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se modificó la habitacion con éxito");
                return true;
            }
            System.out.println("Existió un problema al modificar la habitacion");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Habitacion buscarHabitacion(int idHabitacion) {
        String sql = "SELECT idHabitacion, idHotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada FROM habitacion WHERE idHabitacion = ?";
        try{
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idHabitacion));
        for (List<Object> registro : conexion.seleccion(sql, parametros)) {
            int idHotel = (int) registro.get(1);
            Hotel hotel = PHotel.buscarHotel(idHotel);
            int numCamas = (int) registro.get(2);
            boolean camaMatrimonial = (boolean) registro.get(3);
            boolean aireAcondicionado = (boolean) registro.get(4);
            boolean balcon = (boolean) registro.get(5);
            boolean vista = (boolean) registro.get(6);
            boolean amenities = (boolean) registro.get(7);
            boolean ocupada = (boolean) registro.get(8);

            Habitacion habitacion = new Habitacion(idHabitacion, hotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities);
            habitacion.setOcupada(ocupada);
            return habitacion;
        }
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean actualizarEstadoHabitacion(Habitacion habitacion) {
        String sql = "UPDATE habitacion SET ocupada = ? WHERE idHabitacion = ?";
        List<Object> parametros = Arrays.asList(habitacion.isOcupada(), habitacion.getIdHabitacion());
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se actualizo el estado de la habitacion con éxito");
                return true;
            }
            System.out.println("Existió un problema al actualizar el estado de la habitacion");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Habitacion> listarHabitacionesConReserva() {
        String sql = "SELECT DISTINCT h.* FROM habitacion h "
                + "JOIN reserva r ON h.idHabitacion = r.idhabitacion";;
                try {
                    List<List<Object>> resultado = conexion.seleccion(sql, null);
                    ArrayList<Habitacion> habitaciones = new ArrayList<>();
                    for (List<Object> registro : resultado) {
                        int idHabitacion = (int) registro.get(0);
                        int idHotel = (int) registro.get(1);
                        Hotel hotel = PHotel.buscarHotel(idHotel);
                        int numCamas = (int) registro.get(2);
                        boolean camaMatrimonial = (boolean) registro.get(3);
                        boolean aireAcondicionado = (boolean) registro.get(4);
                        boolean balcon = (boolean) registro.get(5);
                        boolean vista = (boolean) registro.get(6);
                        boolean amenities = (boolean) registro.get(7);
                        boolean ocupada = (boolean) registro.get(8);

                        habitaciones.add(new Habitacion(idHabitacion, hotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities));
                        habitaciones.get(habitaciones.size() - 1).setOcupada(ocupada);
                    }
                    return habitaciones;
                }
                catch(AppSQLException e){
                    System.out.println(e.getMessage());
                }
                return null;

    }

    public static ArrayList<Habitacion> listarHabitacionesSinReserva() {
        String sql = "SELECT * FROM habitacion h "
                + "WHERE h.idHabitacion NOT IN (SELECT DISTINCT idhabitacion FROM reserva)";
        try {
        List<List<Object>> resultado = conexion.seleccion(sql, null);
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        for (List<Object> registro : resultado) {
            int idHabitacion = (int) registro.get(0);
            int idHotel = (int) registro.get(1);
            Hotel hotel = PHotel.buscarHotel(idHotel);
            int numCamas = (int) registro.get(2);
            boolean camaMatrimonial = (boolean) registro.get(3);
            boolean aireAcondicionado = (boolean) registro.get(4);
            boolean balcon = (boolean) registro.get(5);
            boolean vista = (boolean) registro.get(6);
            boolean amenities = (boolean) registro.get(7);
            boolean ocupada = (boolean) registro.get(8);

            habitaciones.add(new Habitacion(idHabitacion, hotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities));
            habitaciones.get(habitaciones.size() - 1).setOcupada(ocupada);
        }
        return habitaciones;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Habitacion> buscarHabitacionesDisponiblesEnHotel(int idHotel, LocalDate fechaEntrada, LocalDate fechaSalida) {
        String sql = "SELECT h.* FROM habitacion h " +
                "JOIN hotel ht ON h.idHotel = ht.idHotel " +
                "LEFT JOIN reserva r ON h.idHabitacion = r.idhabitacion " +
                "AND (r.fechaEntrada < ? AND r.fechaSalida > ?) " +
                "WHERE ht.idHotel = ? AND r.idReserva IS NULL";
        try {
            ArrayList<Object> parametros = new ArrayList<>();
            parametros.add(fechaSalida);
            parametros.add(fechaEntrada);
            parametros.add(idHotel);
            List<Habitacion> habitacionesDisponibles = new ArrayList<>();
            for (List<Object> registro : conexion.seleccion(sql, parametros)) {
                int idHabitacion = (int) registro.get(0);
                int idhotel = (int) registro.get(1);
                Hotel hotel = PHotel.buscarHotel(idhotel);
                int numCamas = (int) registro.get(2);
                boolean camaMatrimonial = (boolean) registro.get(3);
                boolean aireAcondicionado = (boolean) registro.get(4);
                boolean balcon = (boolean) registro.get(5);
                boolean vista = (boolean) registro.get(6);
                boolean amenities = (boolean) registro.get(7);

                Habitacion habitacion = new Habitacion(idHabitacion, hotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities);
                habitacionesDisponibles.add(habitacion);
            }
            return habitacionesDisponibles;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
