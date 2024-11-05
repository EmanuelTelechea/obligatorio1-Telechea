package Persistencia;

import Dominio.Hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHotel {
    private static Conexion conexion = new Conexion();
    public static boolean agregarHotel(Hotel h){
        String sql = "Insert into hotel(idHotel, nombre, ciudad, pais, estrellas, direccion, zona) values (?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(h.getIdHotel(), h.getNombre(), h.getCiudad(), h.getPais(), h.getEstrellas(), h.getDireccion(), h.getZona()));
        return conexion.consulta(sql,parametros);
    }
    public static boolean eliminarHotel(int pId){
        String sql = "Delete from hotel where idHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pId));
        return conexion.consulta(sql,parametros);
    }
    public static ArrayList<Hotel> listarHotel(){
        String sql = "select idHotel, nombre, ciudad, pais, estrellas, direccion, zona from hotel";
        List<List<Object>> resultado = conexion.seleccion(sql,null);
        ArrayList<Hotel> hoteles = new ArrayList<>();
        for (List<Object> registro: resultado){
            int id = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String ciudad = String.valueOf(registro.get(2));
            String pais = String.valueOf(registro.get(3));
            int estrellas = (int) registro.get(4);
            String direccion = String.valueOf(registro.get(5));
            String zona = String.valueOf(registro.get(6));

            hoteles.add(new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona));

        }
        return hoteles;
    }

    public static boolean modificarHotel(Hotel h){
        String sql = "Update hotel set nombre = ?, ciudad = ? , pais = ?, estrellas = ?, direccion = ?, zona = ? where idHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getNombre(),
                h.getCiudad(),
                h.getPais(),
                h.getEstrellas(),
                h.getDireccion(),
                h.getZona(),
                h.getIdHotel()
        ));
        return conexion.consulta(sql,parametros);
    }

    public static Hotel buscarHotel(int pId){
        String sql = "Select * from hotel where idHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pId));
        for (List<Object> registro: conexion.seleccion(sql,parametros)){
            int id = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String ciudad = String.valueOf(registro.get(2));
            String pais = String.valueOf(registro.get(3));
            int estrellas = (int) registro.get(4);
            String direccion = String.valueOf(registro.get(5));
            String zona = String.valueOf(registro.get(6));

            Hotel unHotel = new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona);
            return unHotel;
        }
        return null;
    }

    public static Hotel buscarHotelPorNombre(String pNombre){
        String sql = "Select * from hotel where nombre = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pNombre));
        for (List<Object> registro: conexion.seleccion(sql,parametros)){
            int id = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String ciudad = String.valueOf(registro.get(2));
            String pais = String.valueOf(registro.get(3));
            int estrellas = (int) registro.get(4);
            String direccion = String.valueOf(registro.get(5));
            String zona = String.valueOf(registro.get(6));

            Hotel unHotel = new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona);
            return unHotel;
        }
        return null;
    }

    public static Hotel buscarHotelPorCiudad(String pCiudad){
        String sql = "Select * from hotel where ciudad = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pCiudad));
        for (List<Object> registro: conexion.seleccion(sql,parametros)){
            int id = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String ciudad = String.valueOf(registro.get(2));
            String pais = String.valueOf(registro.get(3));
            int estrellas = (int) registro.get(4);
            String direccion = String.valueOf(registro.get(5));
            String zona = String.valueOf(registro.get(6));

            Hotel unHotel = new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona);
            return unHotel;
        }
        return null;
    }

    public static Hotel buscarHotelPorCantEstrellas(int pCantEstrellas){
        String sql = "Select * from hotel where estrellas = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pCantEstrellas));
        for (List<Object> registro: conexion.seleccion(sql,parametros)){
            int id = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String ciudad = String.valueOf(registro.get(2));
            String pais = String.valueOf(registro.get(3));
            int estrellas = (int) registro.get(4);
            String direccion = String.valueOf(registro.get(5));
            String zona = String.valueOf(registro.get(6));

            Hotel unHotel = new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona);
            return unHotel;
        }
        return null;
    }

}
