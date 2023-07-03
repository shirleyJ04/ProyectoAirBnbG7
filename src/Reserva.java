import java.time.LocalDate;
public class Reserva {

    private int idalojamiento;
        private String nombreHuesped;
        private static LocalDate fechaInicio;
        private static LocalDate fechaFin;
        private double pago;

        public Reserva(int idalojamiento, String nombreHuesped, LocalDate fechaInicio, LocalDate fechaFin, double pago) {
            this.idalojamiento = idalojamiento;
            this.nombreHuesped = nombreHuesped;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.pago =pago;
        }

    public int getIdalojamiento() { return idalojamiento;    }

    public String getNombreHuesped() { return nombreHuesped; }

    public LocalDate getFechaInicio() {
            return fechaInicio;
        }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Double getPago() {
        return pago;
    }

}
