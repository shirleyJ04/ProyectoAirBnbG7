import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class AirBnB {
    static Scanner sc = new Scanner(System.in);
    private ArrayList<Alojamiento> alojamientos;
    private ArrayList<Reserva> reservas;
    private Pago pago;
    private ArchAlojamientos archivoAlojamientos;
    private ArchReservas archivoReservas;



    public static void main(String[] args) throws IOException {
        AirBnB menu = new AirBnB();
        menu.mostrarMenu();
    }

    public AirBnB() {
        sc = new Scanner(System.in);
        archivoAlojamientos = new ArchAlojamientos();
        archivoReservas = new ArchReservas();
        alojamientos = archivoAlojamientos.leerAlojamientos();
        reservas=archivoReservas.cargarReservasDesdeArchivo(alojamientos);
    }
    public void mostrarMenu() {
        while (true) {
            System.out.println("=== *** AirBnB *** ===");
            System.out.println("1. Mostrar Alojamientos");
            System.out.println("2. Ingresar como Viajero");
            System.out.println("3. Guardar y Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    mostrarAlojamientos();
                    break;
                case 2:
                    menuViajero();
                    break;
                case 3:
                    archivoAlojamientos.guardarAlojamientos(alojamientos);
                    archivoReservas.guardarReservasEnArchivo(reservas);
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción invalida. Intente nuevamente.");
            }
        }
    }

    private void mostrarAlojamientos() {
        System.out.println("=== *** Alojamientos Disponibles *** ===");
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getEstado()) {
                System.out.println(alojamiento);
            }else{
                System.out.println("No hay alojamientos disponibles");
            }
        }
        System.out.println();
    }

    private void menuViajero() {
        while (true) {
            System.out.println("=== *** Menu Viajero *** ===");
            System.out.println("1. Hacer reserva");
            System.out.println("2. Regresar");
            System.out.print("Ingrese su opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner
            switch (opcion) {
                case 1:
                    hacerReserva();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void hacerReserva() {

        System.out.print("Ingrese el número de alojamiento a reservar: ");
        int codigoAlojamiento = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        Alojamiento alojamiento = buscarAlojamiento(codigoAlojamiento);
        if (alojamiento == null) {
            System.out.println("El alojamiento no existe. Intente nuevamente.");
            return;
        }

        if (!alojamiento.getEstado()) {
            System.out.println("El alojamiento seleccionada no está disponible. Intente nuevamente.");
            return;
        }

        System.out.print("Ingrese el nombre del huésped: ");
        String nombreHuesped = sc.nextLine();

        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String fechaInicioStr = sc.nextLine();
        DateTimeFormatter ymd = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        try {
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);


            System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
            String fechaFinStr = sc.nextLine();
            LocalDate fechaFin = LocalDate.parse(fechaFinStr);

            if (fechaFin.isBefore(fechaInicio)) {
                System.out.println("La fecha de fin no puede ser anterior a la fecha de inicio.");
                return;
            }

            System.out.print("Su total de dias es: ");
            long diasTotales = DAYS.between(fechaInicio, fechaFin);
            System.out.println(diasTotales);
            System.out.print("Su total a pagar es: ");
            double totalPagar = (alojamiento.getPrecio() * diasTotales);
            System.out.println(totalPagar);


            System.out.print("Ingrese el monto del pago total (ingreso de monto con coma(,): ");
            double monto = sc.nextDouble();

            // composicion con pago
            Pago pago = new Pago(totalPagar);
            boolean pagoValido = pago.validarPago(monto);

            if (!pagoValido) {
                System.out.println("El monto ingresado no coincide con el monto de la reserva. La reserva no se pudo completar.");
                return;
            }
            System.out.println("Precio validado");
            Reserva reserva = new Reserva(codigoAlojamiento, nombreHuesped, fechaInicio, fechaFin, monto);
            reservas.add(reserva);
            archivoReservas.guardarReservasEnArchivo(reservas);

            alojamiento.reservar();
            System.out.println("Reserva realizada con éxito.");
            System.out.println();
        }catch (Exception e ){
            System.out.println("Formato Incorrecto");
        }
    }

    private Alojamiento buscarAlojamiento(int numeroHabitacion) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getIdAlojamiento() == numeroHabitacion) {
                return alojamiento;
            }
        }
        return null;
    }

}