package Controladora;

import Dominio.*;
import Persistencia.PHotel;
import Persistencia.PHabitacion;
import Persistencia.PHuesped;
import Persistencia.PReserva;
import Persistencia.PTarifa;
import Utils.AppException;
import Utils.Utilidades;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static java.time.temporal.ChronoUnit.DAYS;

public class Controladora {
    private static Scanner scanner = new Scanner(System.in);

    public void agregarHotel(){
        System.out.println("AGREGAR HOTEL");
        System.out.println("1. Ingresar el id del hotel");
        int id;
        do {
            System.out.println("Ingrese el id");
            try {
                id = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException n){
                id=0;
                System.out.println("Se ingresó un id no numérico");
            }
            catch(Exception e){
                id=0;
                System.out.println("Ocurrió una excepción en el programa");
            }
        }while (id==0);

        System.out.println(" Ingresar el nombre del hotel");
        String nombre;
        do {
            System.out.println("Ingrese el nombre");
            nombre = scanner.nextLine().trim();
            try{
                if(nombre.length()<3){
                    throw new AppException("El largo del nombre debe ser mayor a 2 dígitos");
                }
            }
            catch(AppException e){
                nombre="";
                System.out.println(e.getMessage());
            }
        }while (nombre.isEmpty());

        System.out.println(" Ingresar la ciudad del hotel");
        String ciudad;
        do {
            System.out.println("Ingrese la ciudad");
            ciudad = scanner.nextLine().trim();
            try{
                if(ciudad.length()<3){
                    throw new AppException("El largo de la ciudad debe ser mayor a 2 dígitos");
                }
            }
            catch(AppException e){
                ciudad="";
                System.out.println(e.getMessage());
            }
        }while (ciudad.isEmpty());

        System.out.println(" Ingresar el pais del hotel");
        String pais;
        do {
            System.out.println("Ingrese el pais");
            pais = scanner.nextLine().trim();
            try{
                if(pais.length()<3){
                    throw new AppException("El largo del pais debe ser mayor a 2 dígitos");
                }
                if(pais.length()>120){
                    throw new AppException("El largo del pais debe ser menor a 120 dígitos");
                }
                if (pais.contains("-")){
                    throw new AppException("El pais debe no debe contener guiones");
                }
            }
            catch(AppException e){
                pais="";
                System.out.println(e.getMessage());
            }
        }while (pais.isEmpty());

        System.out.println(" Ingresar la cantidad de estrelllas del hotel(del 1 al 5)");
        int estrellas;
        do {
            System.out.println("Ingrese las estrellas");
            try {
                estrellas = Integer.parseInt(scanner.nextLine());
                if(estrellas > 5){
                    throw new AppException("Maximo 5 estrellas");
                }
                if(estrellas < 1){
                    throw new AppException("Minimo 1 estrella");
                }
            }catch(AppException e){
                estrellas=0;
                System.out.println(e.getMessage());
            }
            catch(NumberFormatException n){
                estrellas=0;
                System.out.println("Se dwbw ingresar un dato numérico");
            }
            catch(Exception e){
                estrellas=0;
                System.out.println("Ocurrió una excepción en el programa");
            }
        }while (estrellas==0);

        System.out.println(" Ingresar la direccion del hotel");
        String direccion;
        do {
            System.out.println("Ingrese la direccion");
            direccion = scanner.nextLine().trim();
            try{
                if(direccion.length()<6){
                    throw new AppException("El largo de la direccion debe ser mayor a 5 dígitos");
                }
            }
            catch(AppException e){
                direccion="";
                System.out.println(e.getMessage());
            }
        }while (direccion.isEmpty());

        System.out.println(" Ingresar la zona del hotel");
        String zona;
        do {
            System.out.println("Ingrese la zona");
            zona = scanner.nextLine().trim();
            try{
                if(zona.length()<3){
                    throw new AppException("El largo de la zona debe ser mayor a 2 dígitos");
                }
            }
            catch(AppException e){
                zona="";
                System.out.println(e.getMessage());
            }
        }while (zona.isEmpty());

        Hotel h = new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona);
        if (PHotel.agregarHotel(h)){
            System.out.println("Se agrego el hotel");
        }else{
            System.out.println("No se pudo agregar el hotel");
        }
    }

    public void eliminarHotel(){
        System.out.println("Eliminar hotel");
        Hotel h = buscarHotel();
        if (h==null){
            System.out.println("No se encontro el hotel");
        }else {
            if( PHotel.eliminarHotel(h.getIdHotel())){
                System.out.println("Se elimino el hotel");
            }else {
                System.out.println("No se pudo eliminar el hotel");
            }
        }
    }

    public void listarHotel(){
        System.out.println("listado de hoteles");
        for (Hotel h : PHotel.listarHotel()){
            System.out.println(h.toString());
        }
    }

    public void modificarHotel(){
        System.out.println("Modificar hotel");
        Hotel h = buscarHotel();
        if (h == null) {
            System.out.println("No se encontró el hotel.");
        } else {
            System.out.println("Ingrese el nombre (" + h.getNombre() + ")");
            String nombre;
            do {
                nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    nombre = h.getNombre();
                }
                try {
                    if (nombre.length() < 3) {
                        throw new AppException("El largo del nombre debe ser mayor a 2 dígitos.");
                    }
                    h.setNombre(nombre);
                    break;
                } catch (AppException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            System.out.println("Ingrese la ciudad (" + h.getCiudad() + ")");
            String ciudad;
            do {
                ciudad = scanner.nextLine().trim();
                if (ciudad.isEmpty()) {
                    ciudad = h.getCiudad();
                }
                try {
                    if (ciudad.length() < 3) {
                        throw new AppException("El largo de la ciudad debe ser mayor a 2 dígitos.");
                    }
                    h.setCiudad(ciudad);
                    break;
                } catch (AppException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            System.out.println("Ingrese el país (" + h.getPais() + ")");
            String pais;
            do {
                pais = scanner.nextLine().trim();
                if (pais.isEmpty()) {
                    pais = h.getPais();
                }
                try {
                    if (pais.length() < 3) {
                        throw new AppException("El largo del país debe ser mayor a 2 dígitos.");
                    }
                    if (pais.length() > 120) {
                        throw new AppException("El largo del país debe ser menor a 120 dígitos.");
                    }
                    if (pais.contains("-")) {
                        throw new AppException("El país no debe contener guiones.");
                    }
                    h.setPais(pais);
                    break;
                } catch (AppException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            System.out.println("Ingrese la cantidad de estrellas (" + h.getEstrellas() + ")");
            int estrellas;
            do {
                String estrellasStr = scanner.nextLine().trim();
                if (estrellasStr.isEmpty()) {
                    estrellas = h.getEstrellas();
                    break;
                }
                try {
                    estrellas = Integer.parseInt(estrellasStr);
                    if (estrellas < 1 || estrellas > 5) {
                        throw new AppException("Las estrellas deben estar entre 1 y 5.");
                    }
                    h.setEstrellas(estrellas);
                    break;
                } catch (AppException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException nfe) {
                    System.out.println("Se debe ingresar un número válido.");
                }
            } while (true);

            System.out.println("Ingrese la dirección (" + h.getDireccion() + ")");
            String direccion;
            do {
                direccion = scanner.nextLine().trim();
                if (direccion.isEmpty()) {
                    direccion = h.getDireccion();
                }
                try {
                    if (direccion.length() < 6) {
                        throw new AppException("El largo de la dirección debe ser mayor a 5 dígitos.");
                    }
                    h.setDireccion(direccion);
                    break;
                } catch (AppException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            System.out.println("Ingrese la zona (" + h.getZona() + ")");
            String zona;
            do {
                zona = scanner.nextLine().trim();
                if (zona.isEmpty()) {
                    zona = h.getZona();
                }
                try {
                    if (zona.length() < 3) {
                        throw new AppException("El largo de la zona debe ser mayor a 2 dígitos.");
                    }
                    h.setZona(zona);
                    break;
                } catch (AppException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            if (PHotel.modificarHotel(h)) {
                System.out.println("Se modificó con éxito.");
            } else {
                System.out.println("No se pudo modificar el hotel.");
            }
        }

    }

    public Hotel buscarHotel(){
        int id;
        do {
            System.out.println("Ingresar el id del Hotel");
            try {
                id = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                id = 0;
            }

        }while (id == 0);
        return PHotel.buscarHotel(id);
    }

    public void buscarHotelPorNombre(){
        String nombre;
        do {
            System.out.println("Ingresar el nombre del Hotel");
            try {
                nombre = scanner.nextLine();
            }catch (Exception e){
                nombre = "";
            }

        }while (nombre.isEmpty());
        System.out.println(PHotel.buscarHotelPorNombre(nombre));
    }

    public void buscarHotelPorCiudad(){
        String ciudad;
        do {
            System.out.println("Ingresar la ciudad del Hotel");
            try {
                ciudad = scanner.nextLine();
            }catch (Exception e){
                ciudad = "";
            }

        }while (ciudad.isEmpty());
        System.out.println(PHotel.buscarHotelPorCiudad(ciudad));
    }

    public void buscarHotelporCantEstrellas(){
        int cantEstrellas;
        do {
            System.out.println("Ingresar la cantidad de estrellas del Hotel");
            try {
                cantEstrellas = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                cantEstrellas = 0;
            }

        }while (cantEstrellas == 0);
        System.out.println(PHotel.buscarHotelPorCantEstrellas(cantEstrellas));
    }

    public void buscarHotelPorFecha(){
        int idHotel;
        do {
            System.out.println("Ingresar el id del Hotel");
            try {
                idHotel = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                idHotel = 0;
            }

        }while (idHotel == 0);
        LocalDate fechaDesde;
        do {
            System.out.println("Ingrese la fecha de desde la que quiera buscar (YYYY-MM-DD)");
            try {
                fechaDesde = LocalDate.parse(scanner.nextLine());
            }catch (Exception e){
                fechaDesde = null;
            }

        }while (fechaDesde == null);
        LocalDate fechaHasta;
        do {
            System.out.println("Ingrese la fecha de hasta la que quiera buscar (YYYY-MM-DD)");
            try {
                fechaHasta = LocalDate.parse(scanner.nextLine());
            }catch (Exception e){
                fechaHasta = null;
            }

        }while (fechaHasta == null);
        System.out.println(PHabitacion.buscarHabitacionesDisponiblesEnHotel(idHotel, fechaDesde, fechaHasta));
    }

    public void agregarHabitacion(){
        System.out.println("AGREGAR HABITACIÓN");
        int idHabitacion;
        do {
            System.out.println("Ingrese el id de la habitación");
            try {
                idHabitacion = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException n){
                idHabitacion=0;
                System.out.println("Se ingresó un id no numérico");
            }
            catch(Exception e){
                idHabitacion=0;
                System.out.println("Ocurrió una excepción en el programa");
            }
        } while (idHabitacion == 0);

        Hotel hotel = buscarHotel();
        if (hotel == null) {
            System.out.println("Hotel no encontrado. No se puede agregar la habitación.");
            return;
        }

        System.out.println("Ingrese el número de camas");
        int numCamas;
        do {
            try {
                numCamas = Integer.parseInt(scanner.nextLine());
                if (numCamas < 1){
                    throw new AppException("Debe haber al menos una cama");
                }
            }catch(AppException ae){
                numCamas=0;
                System.out.println(ae.getMessage());
            }
            catch(NumberFormatException n){
                numCamas=0;
                System.out.println("Se ingresó un dato no numérico");
            }
            catch(Exception e){
                numCamas=0;
                System.out.println("Ocurrió una excepción en el programa");
            }
        } while (numCamas == 0);

        System.out.println("¿Tiene cama matrimonial? (si/no)");
        boolean camaMatrimonial = false;

        do {
            try {
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("si")) {
                    camaMatrimonial = true;
                    break;
                } else if (respuesta.equals("no")) {
                    camaMatrimonial = false;
                    break;
                } else {
                    throw new AppException("Se debe ingresar 'si' o 'no'.");
                }
            } catch (AppException ae) {
                System.out.println(ae.getMessage());
            }
        } while (true);


        System.out.println("¿Tiene aire acondicionado? (si/no)");
        boolean aireAcondicionado = false;

        do {
            try {
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("si")) {
                    aireAcondicionado = true;
                    break;
                } else if (respuesta.equals("no")) {
                    aireAcondicionado = false;
                    break;
                } else {
                    throw new AppException("Se debe ingresar 'si' o 'no'.");
                }
            } catch (AppException ae) {
                System.out.println(ae.getMessage());
            }
        } while (true);

        System.out.println("¿Tiene balcón? (si/no)");
        boolean balcon = false;

        do {
            try {
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("si")) {
                    balcon = true;
                    break;
                } else if (respuesta.equals("no")) {
                    balcon = false;
                    break;
                } else {
                    throw new AppException("Se debe ingresar 'si' o 'no'.");
                }
            } catch (AppException ae) {
                System.out.println(ae.getMessage());
            }
        } while (true);

        System.out.println("¿Tiene vista? (si/no)");
        boolean vista = false;

        do {
            try {
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("si")) {
                    vista = true;
                    break;
                } else if (respuesta.equals("no")) {
                    vista = false;
                    break;
                } else {
                    throw new AppException("Se debe ingresar 'si' o 'no'.");
                }
            } catch (AppException ae) {
                System.out.println(ae.getMessage());
            }
        } while (true);

        System.out.println("¿Tiene amenities? (si/no)");
        boolean amenities = false;

        do {
            try {
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("si")) {
                    amenities = true;
                    break;
                } else if (respuesta.equals("no")) {
                    amenities = false;
                    break;
                } else {
                    throw new AppException("Se debe ingresar 'si' o 'no'.");
                }
            } catch (AppException ae) {
                System.out.println(ae.getMessage());
            }
        } while (true);


        Habitacion habitacion = new Habitacion(idHabitacion, hotel, numCamas, camaMatrimonial, aireAcondicionado, balcon, vista, amenities);

        if (PHabitacion.agregarHabitacion(habitacion)) {
            System.out.println("Habitación agregada correctamente.");
        } else {
            System.out.println("No se pudo agregar la habitación.");
        }
    }

    public void eliminarHabitacion(){
        System.out.println("Eliminar habitación");
        Habitacion h = buscarHabitacion();
        if (h == null) {
            System.out.println("No se encontró la habitación.");
        } else {
            if (PHabitacion.eliminarHabitacion(h.getIdHabitacion())) {
                System.out.println("Se eliminó la habitación.");
            } else {
                System.out.println("No se pudo eliminar la habitación.");
            }
        }
    }

    public void listarHabitacion(){
        System.out.println("Listado de habitaciones");
        for (Habitacion h : PHabitacion.listarHabitacion()) {
            System.out.println(h.toString());
        }
    }

    public void listarHabitacionesConReserva(){
        System.out.println("Listado de habitaciones con reserva previa");
        for (Habitacion h : PHabitacion.listarHabitacionesConReserva()) {
            System.out.println(h.toString());
        }
    }

    public void listarHabitacionesSinReseva(){
        System.out.println("Listado de habitaciones sin reserva previa");
        for (Habitacion h : PHabitacion.listarHabitacionesSinReserva()) {
            System.out.println(h.toString());
        }
    }

    public void modificarHabitacion(){
        System.out.println("Modificar habitación");
        Habitacion h = buscarHabitacion();
        if (h == null) {
            System.out.println("No se encontró la habitación.");
        } else {
            System.out.println("Ingrese el número de camas (" + h.getNumCamas() + ")");
            int numCamas;
            do {
                try {
                    String numCamasStr = scanner.nextLine().trim();
                    if (numCamasStr.isEmpty()) {
                        numCamas = h.getNumCamas();
                    } else {
                        numCamas = Integer.parseInt(numCamasStr);
                        if (numCamas < 1) {
                            throw new AppException("Debe haber al menos una cama.");
                        }
                    }
                    h.setNumCamas(numCamas);
                    break;
                } catch (AppException ae) {
                    System.out.println(ae.getMessage());
                } catch (NumberFormatException nfe) {
                    System.out.println("Se ingresó un dato no numérico. Intente de nuevo.");
                }
            } while (true);

            System.out.println("¿Tiene cama matrimonial? (" + (h.isCamaMatrimonial() ? "si" : "no") + ")");
            do {
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.isEmpty()) {
                    break;
                } else if (respuesta.equals("si")) {
                    h.setCamaMatrimonial(true);
                    break;
                } else if (respuesta.equals("no")) {
                    h.setCamaMatrimonial(false);
                    break;
                } else {
                    System.out.println("Debe ingresar 'si' o 'no'. Intente nuevamente.");
                }
            } while (true);

            System.out.println("¿Tiene aire acondicionado? (" + (h.isAireAcondicionado() ? "si" : "no") + ")");
            do {
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.isEmpty()) {
                    break;
                } else if (respuesta.equals("si")) {
                    h.setAireAcondicionado(true);
                    break;
                } else if (respuesta.equals("no")) {
                    h.setAireAcondicionado(false);
                    break;
                } else {
                    System.out.println("Debe ingresar 'si' o 'no'. Intente nuevamente.");
                }
            } while (true);

            System.out.println("¿Tiene balcón? (" + (h.isBalcon() ? "si" : "no") + ")");
            do {
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.isEmpty()) {
                    break;
                } else if (respuesta.equals("si")) {
                    h.setBalcon(true);
                    break;
                } else if (respuesta.equals("no")) {
                    h.setBalcon(false);
                    break;
                } else {
                    System.out.println("Debe ingresar 'si' o 'no'. Intente nuevamente.");
                }
            } while (true);

            System.out.println("¿Tiene vista? (" + (h.isVista() ? "si" : "no") + ")");
            do {
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.isEmpty()) {
                    break;
                } else if (respuesta.equals("si")) {
                    h.setVista(true);
                    break;
                } else if (respuesta.equals("no")) {
                    h.setVista(false);
                    break;
                } else {
                    System.out.println("Debe ingresar 'si' o 'no'. Intente nuevamente.");
                }
            } while (true);

            System.out.println("¿Tiene amenities? (" + (h.isAmenities() ? "si" : "no") + ")");
            do {
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.isEmpty()) {
                    break;
                } else if (respuesta.equals("si")) {
                    h.setAmenities(true);
                    break;
                } else if (respuesta.equals("no")) {
                    h.setAmenities(false);
                    break;
                } else {
                    System.out.println("Debe ingresar 'si' o 'no'. Intente nuevamente.");
                }
            } while (true);

            if (PHabitacion.modificarHabitacion(h)) {
                System.out.println("Habitación modificada con éxito.");
            } else {
                System.out.println("No se pudo modificar la habitación.");
            }
        }

    }

    public Habitacion buscarHabitacion(){
        int id;
        do {
            System.out.println("Ingresar el id de la Habitación");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                id = 0;
            }
        } while (id == 0);
        return PHabitacion.buscarHabitacion(id);
    }

    public void agregarHuesped() {
        System.out.println("AGREGAR HUESPED");
        System.out.println("1. Ingresar el ID del huésped");
        int id;
        do {
            System.out.println("Ingrese el ID");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    throw new AppException("El ID debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                id = 0;
                System.out.println("Se ingresó un ID no numérico");
            } catch (AppException e) {
                id = 0;
                System.out.println(e.getMessage());
            }
        } while (id == 0);

        System.out.println("Ingresar el nombre del huésped");
        String nombre;
        do {
            System.out.println("Ingrese el nombre");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío");
            }
        } while (nombre.isEmpty());

        System.out.println("Ingresar el apellido paterno del huésped");
        String apaterno;
        do {
            System.out.println("Ingrese el apellido paterno");
            apaterno = scanner.nextLine().trim();
            if (apaterno.isEmpty()) {
                System.out.println("El apellido paterno no puede estar vacío");
            }
        } while (apaterno.isEmpty());

        System.out.println("Ingresar el apellido materno del huésped");
        String amaterno;
        do {
            System.out.println("Ingrese el apellido materno");
            amaterno = scanner.nextLine().trim();
            if (amaterno.isEmpty()) {
                System.out.println("El apellido materno no puede estar vacío");
            }
        } while (amaterno.isEmpty());

        System.out.println("Ingresar el tipo de documento del huésped");
        String tipoDocumento;
        do {
            System.out.println("Ingrese el tipo de documento");
            tipoDocumento = scanner.nextLine().trim();
            if (tipoDocumento.isEmpty()) {
                System.out.println("El tipo de documento no puede estar vacío");
            }
        } while (tipoDocumento.isEmpty());

        System.out.println("Ingresar el número de documento del huésped");
        int numDocumento;
        do {
            System.out.println("Ingrese el número de documento");
            try {
                numDocumento = Integer.parseInt(scanner.nextLine());
                if (numDocumento <= 0) {
                    throw new AppException("El número de documento debe ser un valor positivo.");
                }
            } catch (NumberFormatException e) {
                numDocumento = 0;
                System.out.println("Se ingresó un valor no numérico");
            } catch (AppException e) {
                numDocumento = 0;
                System.out.println(e.getMessage());
            }
        } while (numDocumento == 0);

        System.out.println("Ingresar la fecha de nacimiento del huésped (YYYY-MM-DD)");
        LocalDate fechaNacimiento = null;
        do {
            try {
                String fechaNacimientoStr = scanner.nextLine().trim();
                fechaNacimiento = Utilidades.validarFecha(fechaNacimientoStr);
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaNacimiento == null);

        System.out.println("Ingresar el teléfono del huésped");
        int telefono;
        do {
            System.out.println("Ingrese el teléfono");
            try {
                telefono = Integer.parseInt(scanner.nextLine());
                if (telefono <= 0) {
                    throw new AppException("El teléfono debe ser un valor positivo.");
                }
            } catch (NumberFormatException e) {
                telefono = 0;
                System.out.println("Se ingresó un valor no numérico");
            } catch (AppException e) {
                telefono = 0;
                System.out.println(e.getMessage());
            }
        } while (telefono == 0);

        System.out.println("Ingresar el país del huésped");
        String pais;
        do {
            System.out.println("Ingrese el país");
            pais = scanner.nextLine().trim();
            if (pais.isEmpty()) {
                System.out.println("El país no puede estar vacío");
            }
        } while (pais.isEmpty());


        Huesped h = new Huesped(id, nombre, apaterno, amaterno, tipoDocumento, numDocumento, fechaNacimiento, telefono, pais);
        if (PHuesped.agregarHuesped(h)) {
            System.out.println("Se agregó el huésped");
        } else {
            System.out.println("No se pudo agregar el huésped");
        }
    }

    public void eliminarHuesped() {
        System.out.println("Eliminar huésped");
        Huesped h = buscarHuesped();
        if (h == null) {
            System.out.println("No se encontró el huésped");
        } else {
            if (PHuesped.eliminarHuesped(h.getIdhuesped())) {
                System.out.println("Se eliminó el huésped");
            } else {
                System.out.println("No se pudo eliminar el huésped");
            }
        }
    }

    public void listarHuesped() {
        System.out.println("Listado de huéspedes");
        for (Huesped h : PHuesped.listarHuesped()) {
            System.out.println(h.toString());
        }
    }

    public void modificarHuesped() {
        System.out.println("Modificar huésped");
        Huesped h = buscarHuesped();
        if (h == null) {
            System.out.println("No se encontró el huésped");
        } else {
            System.out.println("Ingrese el nombre (" + h.getNombre() + ")");
            String nombre;
            do {
                nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacío. Intente nuevamente:");
                }
            } while (nombre.isEmpty());
            h.setNombre(nombre);

            System.out.println("Ingrese el apellido paterno (" + h.getApaterno() + ")");
            String apaterno;
            do {
                apaterno = scanner.nextLine().trim();
                if (apaterno.isEmpty()) {
                    System.out.println("El apellido paterno no puede estar vacío. Intente nuevamente:");
                }
            } while (apaterno.isEmpty());
            h.setApaterno(apaterno);

            System.out.println("Ingrese el apellido materno (" + h.getAmaterno() + ")");
            String amaterno;
            do {
                amaterno = scanner.nextLine().trim();
                if (amaterno.isEmpty()) {
                    System.out.println("El apellido materno no puede estar vacío. Intente nuevamente:");
                }
            } while (amaterno.isEmpty());
            h.setAmaterno(amaterno);

            System.out.println("Ingrese el tipo de documento (" + h.getTipo_documento() + ")");
            String tipoDocumento;
            do {
                tipoDocumento = scanner.nextLine().trim();
                if (tipoDocumento.isEmpty()) {
                    System.out.println("El tipo de documento no puede estar vacío. Intente nuevamente:");
                }
            } while (tipoDocumento.isEmpty());
            h.setTipo_documento(tipoDocumento);

            System.out.println("Ingrese el número de documento (" + h.getNum_documento() + ")");
            int numDocumento;
            do {
                try {
                    numDocumento = Integer.parseInt(scanner.nextLine().trim());
                    if (numDocumento <= 0) {
                        System.out.println("El número de documento debe ser un número positivo.");
                        continue;
                    }
                    h.setNum_documento(numDocumento);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Número de documento inválido. Intente nuevamente:");
                }
            } while (true);

            System.out.println("Ingrese la fecha de nacimiento (" + h.getFecha_nacimiento() + ")");
            String fechaNacimientoStr = scanner.nextLine().trim();
            if (!fechaNacimientoStr.isEmpty()) {
                try {
                    h.setFecha_nacimiento(Utilidades.validarFecha(fechaNacimientoStr));
                } catch (Exception e) {
                    System.out.println("Fecha inválida, no se actualizó.");
                }
            }

            System.out.println("Ingrese el teléfono (" + h.getTelefono() + ")");
            int telefono;
            do {
                try {
                    telefono = Integer.parseInt(scanner.nextLine().trim());
                    if (telefono <= 0) {
                        System.out.println("El teléfono debe ser un número positivo.");
                        continue;
                    }
                    h.setTelefono(telefono);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Teléfono inválido. Intente nuevamente:");
                }
            } while (true);

            System.out.println("Ingrese el país (" + h.getPais() + ")");
            String pais;
            do {
                pais = scanner.nextLine().trim();
                if (pais.isEmpty()) {
                    System.out.println("El país no puede estar vacío. Intente nuevamente:");
                }
            } while (pais.isEmpty());
            h.setPais(pais);

            if (PHuesped.modificarHuesped(h)) {
                System.out.println("Se modificó con éxito");
            } else {
                System.out.println("No se modificó");
            }
        }

    }

    public Huesped buscarHuesped() {
        int id;
        do {
            System.out.println("Ingresar el ID del huésped");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                id = 0;
            }
        } while (id == 0);
        return PHuesped.buscarHuesped(id);
    }

    public void agregarReserva() {
        System.out.println("AGREGAR RESERVA");

        Huesped huesped = buscarHuesped();
        if (huesped == null) {
            System.out.println("Huesped no encontrado. No se puede realizar la reserva.");
            return;
        }

        Habitacion habitacion = buscarHabitacion();
        if (habitacion == null) {
            System.out.println("Habitacion no encontrada. No se puede realizar la reserva.");
            return;
        }

        System.out.println("Ingrese la fecha de la reserva (YYYY-MM-DD):");
        LocalDate fechaReserva = null;
        do {
            try {
                String fechaReservaStr = scanner.nextLine().trim();
                fechaReserva = Utilidades.validarFecha(fechaReservaStr);
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaReserva == null);

        System.out.println("Ingrese la fecha de entrada (YYYY-MM-DD):");
        LocalDate fechaEntrada = null;
        do {
            try {
                String fechaEntradaStr = scanner.nextLine().trim();
                fechaEntrada = Utilidades.validarFecha(fechaEntradaStr);
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaEntrada == null);

        System.out.println("Ingrese la fecha de salida (YYYY-MM-DD):");
        LocalDate fechaSalida = null;
        do {
            try {
                String fechaSalidaStr = scanner.nextLine().trim();
                fechaSalida = Utilidades.validarFecha(fechaSalidaStr);
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaSalida == null);

        System.out.println("¿La reserva está pagada? (si/no):");
        boolean pagada = false;
        do {
            try {
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (respuesta.equals("si")) {
                    pagada = true;
                    break;
                } else if (respuesta.equals("no")) {
                    pagada = false;
                    break;
                } else {
                    throw new AppException("Debe ingresar 'si' o 'no' para indicar si la reserva está pagada.");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        BigDecimal costoTotal = calcularCostoReserva(new Reserva(huesped, habitacion, fechaReserva, fechaEntrada, fechaSalida, pagada));


        Reserva reserva = new Reserva(huesped, habitacion, fechaReserva, fechaEntrada, fechaSalida, pagada, costoTotal);

        if (PReserva.agregarReserva(reserva)) {
            System.out.println("Reserva agregada con éxito.");
        } else {
            System.out.println("No se pudo agregar la reserva.");
        }
    }

    public void eliminarReserva() {
        System.out.println("Eliminar reserva");
        Reserva r = buscarReserva();
        if (r == null) {
            System.out.println("No se encontró la reserva");
        } else {
            if (PReserva.eliminarReserva(r.getIdReserva())) {
                System.out.println("Reserva eliminada con éxito.");
            } else {
                System.out.println("No se pudo eliminar la reserva.");
            }
        }
    }

    public void listarReservas() {
        System.out.println("Listado de reservas");
        for (Reserva r : PReserva.listarReservas()) {
            System.out.println(r.toString());
        }
    }

    public void modificarReserva() {
        System.out.println("Modificar reserva");
        Reserva r = buscarReserva();
        if (r == null) {
            System.out.println("No se encontró la reserva");
        } else {
            System.out.println("Ingrese el ID del huésped (" + r.getHuesped().getIdhuesped() + ")");
            try {
                int idHuesped = Integer.parseInt(scanner.nextLine());
                Huesped huesped = PHuesped.buscarHuesped(idHuesped);
                if (huesped != null) {
                    r.setHuesped(huesped);
                } else {
                    System.out.println("No se encontró el huésped con ese ID.");
                }
            } catch (Exception e) {
                System.out.println("ID de huésped inválido.");
            }

            System.out.println("Ingrese el ID de la habitación (" + r.getHabitacion().getIdHabitacion() + ")");
            try {
                int idHabitacion = Integer.parseInt(scanner.nextLine());
                Habitacion habitacion = PHabitacion.buscarHabitacion(idHabitacion);
                if (habitacion != null) {
                    r.setHabitacion(habitacion);
                } else {
                    System.out.println("No se encontró la habitación con ese ID.");
                }
            } catch (Exception e) {
                System.out.println("ID de habitación inválido.");
            }

            System.out.println("Ingrese la fecha de reserva (" + r.getFechaReserva() + ")");
            String fechaReservaStr = scanner.nextLine();
            if (!fechaReservaStr.isEmpty()) {
                try {
                    r.setFechaReserva(Utilidades.validarFecha(fechaReservaStr));
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Fecha de reserva no actualizada.");
                }
            }

            System.out.println("Ingrese la fecha de entrada (" + r.getFechaEntrada() + ")");
            String fechaEntradaStr = scanner.nextLine();
            if (!fechaEntradaStr.isEmpty()) {
                try {
                    LocalDate nuevaFechaEntrada = Utilidades.validarFecha(fechaEntradaStr);
                    if (nuevaFechaEntrada.isBefore(r.getFechaReserva())) {
                        throw new AppException("La fecha de entrada no puede ser anterior a la fecha de reserva.");
                    }
                    r.setFechaEntrada(nuevaFechaEntrada);
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Fecha de entrada no actualizada.");
                }
            }

            System.out.println("Ingrese la fecha de salida (" + r.getFechaSalida() + ")");
            String fechaSalidaStr = scanner.nextLine();
            if (!fechaSalidaStr.isEmpty()) {
                try {
                    LocalDate nuevaFechaSalida = Utilidades.validarFecha(fechaSalidaStr);
                    if (nuevaFechaSalida.isBefore(r.getFechaEntrada())) {
                        throw new AppException("La fecha de salida no puede ser anterior a la fecha de entrada.");
                    }
                    r.setFechaSalida(nuevaFechaSalida);
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Fecha de salida no actualizada.");
                }
            }

            System.out.println("¿Está pagada? (" + r.isPagada() + ")");
            String pagadaStr = scanner.nextLine().trim().toLowerCase();
            if (!pagadaStr.isEmpty()) {
                try {
                    if (!pagadaStr.equals("true") && !pagadaStr.equals("false")) {
                        throw new AppException("Debe ingresar 'true' o 'false'.");
                    }
                    r.setPagada(Boolean.parseBoolean(pagadaStr));
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Estado de pago no actualizado.");
                }
            }


            if (PReserva.modificarReserva(r)) {
                System.out.println("Reserva modificada con éxito.");
            } else {
                System.out.println("No se pudo modificar la reserva.");
            }
        }
    }

    public Reserva buscarReserva() {
        int id;
        do {
            System.out.println("Ingresar el id de la Reserva");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                id = 0;
            }
        } while (id == 0);

        return PReserva.buscarReserva(id);
    }

    public void agregarTarifa() {
        System.out.println("AGREGAR TARIFA");

        Habitacion habitacion = buscarHabitacion();
        if (habitacion == null) {
            System.out.println("Habitacion no encontrada. No se puede realizar la reserva.");
            return;
        }

        System.out.println("Ingrese el monto de la tarifa:");
        BigDecimal monto = null;
        do {
            try {
                monto = new BigDecimal(scanner.nextLine().trim());
                if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new AppException("El monto debe ser un valor positivo.");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
                monto = null;
            } catch (NumberFormatException e) {
                System.out.println("Monto inválido, intente de nuevo.");
                monto = null;
            }
        } while (monto == null);

        System.out.println("Ingrese la fecha de inicio de vigencia (YYYY-MM-DD):");
        LocalDate fechaInicio = null;
        do {
            try {
                String fechaInicioStr = scanner.nextLine().trim();
                fechaInicio = Utilidades.validarFecha(fechaInicioStr);
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaInicio == null);

        System.out.println("Ingrese la fecha de fin de vigencia (YYYY-MM-DD):");
        LocalDate fechaFin = null;
        do {
            try {
                String fechaFinStr = scanner.nextLine().trim();
                fechaFin = Utilidades.validarFecha(fechaFinStr);
                if (fechaFin.isBefore(fechaInicio)) {
                    throw new AppException("La fecha de fin no puede ser anterior a la fecha de inicio.");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaFin == null);

        Tarifa tarifa = new Tarifa(0, habitacion, monto, fechaInicio, fechaFin);
        if (PTarifa.agregarTarifa(tarifa)) {
            System.out.println("Tarifa agregada con éxito.");
        } else {
            System.out.println("No se pudo agregar la tarifa.");
        }
    }

    public void eliminarTarifa() {
        System.out.println("Eliminar tarifa");
        Tarifa t = buscarTarifa();
        if (t == null) {
            System.out.println("No se encontró la tarifa.");
        } else {
            if (PTarifa.eliminarTarifa(t.getIdTarifa())) {
                System.out.println("Tarifa eliminada con éxito.");
            } else {
                System.out.println("No se pudo eliminar la tarifa.");
            }
        }
    }

    public void listarTarifas() {
        System.out.println("Listado de tarifas:");
        for (Tarifa t : PTarifa.listarTarifas()) {
            System.out.println(t.toString());
        }
    }

    public void modificarTarifa() {
        System.out.println("Modificar tarifa");
        Tarifa t = buscarTarifa();
        if (t == null) {
            System.out.println("No se encontró la tarifa.");
        } else {
            System.out.println("Fecha de inicio de vigencia actual: " + t.getFechaInicio());
            System.out.println("Ingrese nueva fecha de inicio (YYYY-MM-DD) o deje en blanco para mantener la actual:");
            String fechaInicioStr = scanner.nextLine();
            if (!fechaInicioStr.isEmpty()) {
                try {
                    t.setFechaInicio(Utilidades.validarFecha(fechaInicioStr));
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Manteniendo la fecha de inicio actual.");
                }
            }

            System.out.println("Fecha de fin de vigencia actual: " + t.getFechaFin());
            System.out.println("Ingrese nueva fecha de finalización (YYYY-MM-DD) o deje en blanco para mantener la actual:");
            String fechaFinStr = scanner.nextLine();
            if (!fechaFinStr.isEmpty()) {
                try {
                    LocalDate nuevaFechaFin = Utilidades.validarFecha(fechaFinStr);
                    if (nuevaFechaFin.isBefore(t.getFechaInicio())) {
                        throw new AppException("La fecha de fin no puede ser anterior a la fecha de inicio.");
                    }
                    t.setFechaFin(nuevaFechaFin);
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Manteniendo la fecha de fin actual.");
                }
            }

            System.out.println("Monto actual: " + t.getPrecio());
            System.out.println("Ingrese el nuevo monto o deje en blanco para mantener el actual:");
            String precioStr = scanner.nextLine();
            if (!precioStr.isEmpty()) {
                try {
                    BigDecimal nuevoPrecio = new BigDecimal(precioStr.trim());
                    if (nuevoPrecio.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new AppException("El monto debe ser un valor positivo.");
                    }
                    t.setPrecio(nuevoPrecio);
                } catch (AppException e) {
                    System.out.println(e.getMessage() + " Manteniendo el monto actual.");
                } catch (NumberFormatException e) {
                    System.out.println("Monto inválido, manteniendo el monto actual.");
                }
            }

            if (PTarifa.modificarTarifa(t)) {
                System.out.println("Tarifa modificada con éxito.");
            } else {
                System.out.println("No se pudo modificar la tarifa.");
            }
        }
    }

    public Tarifa buscarTarifa() {
        int id;
        do {
            System.out.println("Ingresar el ID de la Tarifa:");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                id = 0;
            }
        } while (id == 0);

        return PTarifa.buscarTarifa(id);
    }

    public BigDecimal calcularCostoReserva(Reserva reserva) {
        List<Tarifa> tarifas = PTarifa.obtenerTarifasVigentes(reserva.getHabitacion(), reserva.getFechaEntrada(), reserva.getFechaSalida());
        BigDecimal costoTotal = BigDecimal.ZERO;

        for (Tarifa tarifa : tarifas) {
            LocalDate inicioPeriodo = tarifa.getFechaInicio().isBefore(reserva.getFechaEntrada()) ? reserva.getFechaEntrada() : tarifa.getFechaInicio();
            LocalDate finPeriodo = tarifa.getFechaFin().isAfter(reserva.getFechaSalida()) ? reserva.getFechaSalida() : tarifa.getFechaFin();

            if (!inicioPeriodo.isAfter(finPeriodo)) {
                long dias = DAYS.between(inicioPeriodo, finPeriodo) + 1;
                costoTotal = costoTotal.add(tarifa.getPrecio().multiply(BigDecimal.valueOf(dias)));
            }
        }
        return costoTotal;
    }

    public void verificarOcupacionHabitaciones() {
        LocalDate hoy = LocalDate.now();
        List<Reserva> reservas = PReserva.listarReservas();

        for (Reserva reserva : reservas) {
            if (reserva.getFechaEntrada().isEqual(hoy) && !reserva.getHabitacion().isOcupada()) {
                Habitacion habitacion = reserva.getHabitacion();
                habitacion.setOcupada(true);
                PHabitacion.actualizarEstadoHabitacion(habitacion);
                System.out.println("Habitación " + habitacion.getIdHabitacion() + " marcada como ocupada.");
            }
        }
    }

    public void liberarHabitaciones() {
        LocalDate hoy = LocalDate.now();
        List<Reserva> reservas = PReserva.listarReservas();

        for (Reserva reserva : reservas) {
            if (reserva.getFechaSalida().isBefore(hoy) && reserva.getHabitacion().isOcupada()) {
                Habitacion habitacion = reserva.getHabitacion();
                habitacion.setOcupada(false);
                PHabitacion.actualizarEstadoHabitacion(habitacion);
                System.out.println("Habitación " + habitacion.getIdHabitacion() + " liberada.");
            }
        }
    }

}
