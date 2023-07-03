import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArchReservas {
    private static final String ARCHIVO_RESERVAS = "reservas.txt";

    public static ArrayList<Reserva> cargarReservasDesdeArchivo(ArrayList<Alojamiento> alojamientos) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_RESERVAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                int Idalojamiento = Integer.parseInt(datos[0]);
                Alojamiento alojamiento = buscarAlojArch(alojamientos, Idalojamiento);
                if(alojamiento!= null){
                    String nombreHuesped = datos[1];
                    LocalDate fechaInicio = LocalDate.parse(datos[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate fechaFin = LocalDate.parse(datos[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    Double monto = Double.parseDouble(datos[4]);
                    Reserva reserva = new Reserva(Idalojamiento, nombreHuesped,fechaInicio,fechaFin, monto);
                    reservas.add(reserva);
                }else {
                    System.out.println("Habitacion no encontrada " + Idalojamiento);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de reservas no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de reservas: " + e.getMessage());
        }
        return reservas;
    }

    public void guardarReservasEnArchivo(ArrayList<Reserva> reservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_RESERVAS))) {
            for (Reserva reserva : reservas) {
                String linea = reserva.getIdalojamiento() + ";"
                        + reserva.getNombreHuesped() + ";"
                        + reserva.getFechaInicio() + ";"
                        + reserva.getFechaFin() + ";"
                        + reserva.getPago();
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Reservas guardadas en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas en el archivo: " + e.getMessage());
        }
    }
    private static Alojamiento buscarAlojArch(ArrayList<Alojamiento> alojamientos, int numeroAl) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getIdAlojamiento() == numeroAl) {
                return alojamiento;
            }
        }
        return null;
    }

}
