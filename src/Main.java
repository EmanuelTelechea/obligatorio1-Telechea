import Controladora.Controladora;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Controladora controladora = new Controladora();
    public static void main(String[] args) {

        controladora.verificarOcupacionHabitaciones();
        controladora.liberarHabitaciones();

        System.out.println("Gestion de reserva de habitaciones de hotel");

        int opcion;
        do{
            menu();
            System.out.println("Elige una opcion(-1 para salir)");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    menuHotel();
                    break;
                }
                case 2:{
                    menuHabitacion();
                    break;
                }
                case 3:{
                    menuHuesped();
                    break;
                }
                case 4:{
                    menuReserva();
                    break;
                }
                case 5:{
                    menuTarifa();
                    break;
                }
            }

        }while(opcion !=-1);
    }
    private static void menu(){
        System.out.println("MENU");
        System.out.println("1- Hotel");
        System.out.println("2- Habitacion");
        System.out.println("3- Huesped");
        System.out.println("4- Reserva");
        System.out.println("5- Tarifa");
    }

    private static void menuHotel(){
        int opcion;
        do{
            System.out.println("MENU HOTEL");
            System.out.println("1- Agregar Hotel");
            System.out.println("2- Eliminar Hotel");
            System.out.println("3- Modificar Hotel");
            System.out.println("4- Ver Hoteles");
            System.out.println("5- Ver Hoteles por nombre");
            System.out.println("6- Ver Hoteles por ciudad");
            System.out.println("7- Ver Hoteles por cantidad de estrellas");
            System.out.println("8- Ver Hoteles por fecha");
            System.out.println("Elige una opcion(-1 para salir)");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    controladora.agregarHotel();
                    break;
                }
                case 2:{
                    controladora.eliminarHotel();
                    break;
                }
                case 3:{
                    controladora.modificarHotel();
                    break;
                }
                case 4:{
                    controladora.listarHotel();
                    break;
                }
                case 5:{
                    controladora.buscarHotelPorNombre();
                    break;
                }
                case 6:{
                    controladora.buscarHotelPorCiudad();
                    break;
                }
                case 7:{
                    controladora.buscarHotelporCantEstrellas();
                    break;
                }
                case 8:{
                    controladora.buscarHotelPorFecha();
                    break;
                }
            }

        }while(opcion !=-1);
    }

    private static void menuHabitacion(){
        int opcion;
        do{
            System.out.println("MENU HABITACION");
            System.out.println("1- Agregar Habitacion");
            System.out.println("2- Eliminar Habitacion");
            System.out.println("3- Modificar Habitacion");
            System.out.println("4- Ver Habitaciones");
            System.out.println("5- Ver Habitaciones con reserva previa");
            System.out.println("6- Ver Habitaciones sin reserva previa");
            System.out.println("Elige una opcion(-1 para salir)");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    controladora.agregarHabitacion();
                    break;
                }
                case 2:{
                    controladora.eliminarHabitacion();
                    break;
                }
                case 3:{
                    controladora.modificarHabitacion();
                    break;
                }
                case 4:{
                    controladora.listarHabitacion();
                    break;
                }
                case 5:{
                    controladora.listarHabitacionesConReserva();
                    break;
                }
                case 6:{
                    controladora.listarHabitacionesSinReseva();
                    break;
                }
            }

        }while(opcion !=-1);
    }

    private static void menuHuesped(){
        int opcion;
        do{
            System.out.println("MENU Huesped");
            System.out.println("1- Agregar Huesped");
            System.out.println("2- Eliminar Huesped");
            System.out.println("3- Modificar Huesped");
            System.out.println("4- Ver Huespedes");
            System.out.println("Elige una opcion(-1 para salir)");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    controladora.agregarHuesped();
                    break;
                }
                case 2:{
                    controladora.eliminarHuesped();
                    break;
                }
                case 3:{
                    controladora.modificarHuesped();
                    break;
                }
                case 4:{
                    controladora.listarHuesped();
                    break;
                }
            }

        }while(opcion !=-1);
    }

    private static void menuReserva(){
        int opcion;
        do{
            System.out.println("MENU Reserva");
            System.out.println("1- Agregar Reserva");
            System.out.println("2- Eliminar Reserva");
            System.out.println("3- Modificar Reserva");
            System.out.println("4- Ver Reservas");
            System.out.println("Elige una opcion(-1 para salir)");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    controladora.agregarReserva();
                    break;
                }
                case 2:{
                    controladora.eliminarReserva();
                    break;
                }
                case 3:{
                    controladora.modificarReserva();
                    break;
                }
                case 4:{
                    controladora.listarReservas();
                    break;
                }
            }

        }while(opcion !=-1);
    }

    private static void menuTarifa(){
        int opcion;
        do{
            System.out.println("MENU tarifa");
            System.out.println("1- Agregar tarifa");
            System.out.println("2- Eliminar tarifa");
            System.out.println("3- Modificar tarifa");
            System.out.println("4- Ver tarifas");
            System.out.println("Elige una opcion(-1 para salir)");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:{
                    controladora.agregarTarifa();
                    break;
                }
                case 2:{
                    controladora.eliminarTarifa();
                    break;
                }
                case 3:{
                    controladora.modificarTarifa();
                    break;
                }
                case 4:{
                    controladora.listarTarifas();
                    break;
                }
            }

        }while(opcion !=-1);
    }

}