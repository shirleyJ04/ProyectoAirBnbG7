import java.io.*;
import java.util.ArrayList;

public class ArchAlojamientos {
    private static final String ARCHIVO_ALOJAMIENTOS = "habitaciones.txt";

    public ArrayList<Alojamiento> leerAlojamientos() {
        ArrayList<Alojamiento> alojamientos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ALOJAMIENTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                int idAlojamiento = Integer.parseInt(partes[0]);
                double precio = Double.parseDouble(partes[1]);
                int capacidad = Integer.parseInt(partes[2]);
                String ciudad = partes[3];
                String direccion = partes[4];
                boolean estado = Boolean.parseBoolean(partes[5]);
                String imagen = partes[6];
                Alojamiento alojamiento = new Alojamiento( idAlojamiento, precio,capacidad,ciudad,direccion,imagen);
                alojamientos.add(alojamiento);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de habitaciones.");
        }

        return alojamientos;
    }

    public void guardarAlojamientos(ArrayList<Alojamiento> alojamientos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ALOJAMIENTOS))) {
            for (Alojamiento alojamiento : alojamientos) {
                bw.write(alojamiento.getIdAlojamiento() + "," + alojamiento.getPrecio() + "," + alojamiento.getCapacidad()
                + "," + alojamiento.getCiudad()+ "," + alojamiento.getDireccion() + "," + alojamiento.getEstado() + "," + alojamiento.getImagen());
                bw.newLine();
            }
            System.out.println("Habitaciones guardadas con éxito.");
        } catch (IOException e) {
            System.out.println("No se pudo guardar las habitaciones.");
        }
    }

}
