package Persistencia;

import Dominio.Huesped;
import Utils.AppSQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PHuesped {
    private static Conexion conexion = new Conexion();

    public static boolean agregarHuesped(Huesped h) {
        String sql = "INSERT INTO huesped(idhuesped, nombre, apaterno, amaterno, tipo_documento, num_documento, fecha_nacimiento, telefono, pais) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getIdhuesped(),
                h.getNombre(),
                h.getApaterno(),
                h.getAmaterno(),
                h.getTipo_documento(),
                h.getNum_documento(),
                h.getFecha_nacimiento(),
                h.getTelefono(),
                h.getPais()
        ));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se agregó el huesped con éxito");
                return true;
            }
            System.out.println("Existió un problema al agregar el huesped");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean eliminarHuesped(int idHuesped) {
        String sql = "DELETE FROM huesped WHERE idhuesped = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idHuesped));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se eliminó el huesped con éxito");
                return true;
            }
            System.out.println("Existió un problema al eliminar el huesped");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Huesped> listarHuesped() {
        String sql = "SELECT idhuesped, nombre, apaterno, amaterno, tipo_documento, num_documento, fecha_nacimiento, telefono, pais FROM huesped";
        try {
        List<List<Object>> resultado = conexion.seleccion(sql, null);
        ArrayList<Huesped> huespedes = new ArrayList<>();
        for (List<Object> registro : resultado) {
            int idHuesped = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String apaterno = String.valueOf(registro.get(2));
            String amaterno = String.valueOf(registro.get(3));
            String tipoDocumento = String.valueOf(registro.get(4));
            int numDocumento = (int) registro.get(5);
            LocalDate fechaNacimiento = registro.get(6) != null ? ((java.sql.Date) registro.get(6)).toLocalDate() : null;
            int telefono = (int) registro.get(7);
            String pais = String.valueOf(registro.get(8));

            huespedes.add(new Huesped(idHuesped, nombre, apaterno, amaterno, tipoDocumento, numDocumento, fechaNacimiento, telefono, pais));
        }
        return huespedes;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean modificarHuesped(Huesped h) {
        String sql = "UPDATE huesped SET nombre = ?, apaterno = ?, amaterno = ?, tipo_documento = ?, num_documento = ?, fecha_nacimiento = ?, telefono = ?, pais = ? " +
                "WHERE idhuesped = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getNombre(),
                h.getApaterno(),
                h.getAmaterno(),
                h.getTipo_documento(),
                h.getNum_documento(),
                h.getFecha_nacimiento(),
                h.getTelefono(),
                h.getPais(),
                h.getIdhuesped()
        ));
        try{
            if(conexion.consulta(sql, parametros)){
                System.out.println("Se modificó el huesped con éxito");
                return true;
            }
            System.out.println("Existió un problema al modificar el huesped");
            return false;
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Huesped buscarHuesped(int idHuesped) {
        String sql = "SELECT idhuesped, nombre, apaterno, amaterno, tipo_documento, num_documento, fecha_nacimiento, telefono, pais FROM huesped WHERE idhuesped = ?";
        try{
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idHuesped));
        for (List<Object> registro : conexion.seleccion(sql, parametros)) {
            String nombre = String.valueOf(registro.get(1));
            String apaterno = String.valueOf(registro.get(2));
            String amaterno = String.valueOf(registro.get(3));
            String tipoDocumento = String.valueOf(registro.get(4));
            int numDocumento = (int) registro.get(5);
            LocalDate fechaNacimiento = registro.get(6) != null ? ((java.sql.Date) registro.get(6)).toLocalDate() : null;
            int telefono = (int) registro.get(7);
            String pais = String.valueOf(registro.get(8));

            return new Huesped(idHuesped, nombre, apaterno, amaterno, tipoDocumento, numDocumento, fechaNacimiento, telefono, pais);
        }
        }
        catch(AppSQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

