package Persistencia;

import Dominio.Habitacion;
import Dominio.Huesped;
import Dominio.Reserva;
import Dominio.Tarifa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PTarifa {
    private static Conexion conexion = new Conexion();

    public static boolean agregarTarifa(Tarifa t) {
        String sql = "INSERT INTO tarifa(idhabitacion, precio, fechaInicio, fechaFin) VALUES (?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                t.getHabitacion().getIdHabitacion(),
                t.getPrecio(),
                t.getFechaInicio(),
                t.getFechaFin()
        ));
        return conexion.consulta(sql, parametros);
    }

    public static boolean modificarTarifa(Tarifa t) {
        String sql = "UPDATE tarifa SET precio = ?, fechaInicio = ?, fechaFin = ? WHERE idTarifa = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                t.getPrecio(),
                t.getFechaInicio(),
                t.getFechaFin(),
                t.getIdTarifa()
        ));
        return conexion.consulta(sql, parametros);
    }

    public static boolean eliminarTarifa(int idTarifa) {
        String sql = "DELETE FROM tarifa WHERE idTarifa = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idTarifa));
        return conexion.consulta(sql, parametros);
    }

    public static ArrayList<Tarifa> listarTarifas() {
        String sql = "SELECT idTarifa, idhabitacion, precio, fechaInicio, fechaFin FROM tarifa";
        List<List<Object>> resultado = conexion.seleccion(sql, null);
        ArrayList<Tarifa> tarifas = new ArrayList<>();
        for (List<Object> registro : resultado) {
            int idTarifa = (int) registro.get(0);
            int idHabitacion = (int) registro.get(1);
            Habitacion habitacion = PHabitacion.buscarHabitacion(idHabitacion);
            BigDecimal precio = (BigDecimal) registro.get(2);
            LocalDate fechaInicio = registro.get(3) != null ? ((java.sql.Date) registro.get(3)).toLocalDate() : null;
            LocalDate fechaFin = registro.get(4) != null ? ((java.sql.Date) registro.get(4)).toLocalDate() : null;

            tarifas.add(new Tarifa(idTarifa, habitacion, precio, fechaInicio, fechaFin));
        }
        return tarifas;
    }

    public static Tarifa buscarTarifa(int idTarifa) {
        String sql = "SELECT * FROM tarifa WHERE idTarifa = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idTarifa));
        List<List<Object>> resultado = conexion.seleccion(sql, parametros);
        for (List<Object> registro : resultado) {
            int tarifa = (int) registro.get(0);
            Habitacion habitacion = PHabitacion.buscarHabitacion((int) registro.get(1));
            BigDecimal precio = (BigDecimal) registro.get(2);
            LocalDate fechaInicio  = registro.get(3) != null ? ((java.sql.Date) registro.get(3)).toLocalDate() : null;
            LocalDate fechaFin = registro.get(4) != null ? ((java.sql.Date) registro.get(4)).toLocalDate() : null;

            return new Tarifa(tarifa, habitacion, precio, fechaInicio, fechaFin);
        }
        return null;
    }

    public static List<Tarifa> obtenerTarifasVigentes(Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        String sql = "SELECT * FROM tarifa WHERE idhabitacion = ? AND (fechaInicio <= ? AND (fechaFin IS NULL OR fechaFin >= ?))";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(habitacion.getIdHabitacion(), fechaFin, fechaInicio));

        List<List<Object>> resultados = conexion.seleccion(sql, parametros);
        List<Tarifa> tarifas = new ArrayList<>();

        for (List<Object> registro : resultados) {
            int idTarifa = (int) registro.get(0);
            BigDecimal precio = (BigDecimal) registro.get(2);
            LocalDate vigenciaInicio = registro.get(3) != null ? ((java.sql.Date) registro.get(3)).toLocalDate() : null;
            LocalDate vigenciaFin = registro.get(4) != null ? ((java.sql.Date) registro.get(4)).toLocalDate() : null;
            tarifas.add(new Tarifa(idTarifa, habitacion, precio, vigenciaInicio, vigenciaFin));
        }
        return tarifas;
    }

}
