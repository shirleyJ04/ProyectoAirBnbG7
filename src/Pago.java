public class Pago {

    private double montoReserva;

    public Pago(double monto) {
        this.montoReserva = monto;
    }
    public boolean validarPago(double monto) {
        return monto == montoReserva;
    }


}
