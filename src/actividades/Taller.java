package actividades;
import certificacion.Certificable;
import modelo.Estudiante;

public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override public double calcularCostoMateriales() { return requiereNotebook ? 1500.0 : 500.0; }
    @Override public String getTipo() { return "Taller"; }

    @Override public String generarCertificado(Estudiante e) {
        return "El estudiante " + e.getNombre() + " ha completado satisfactoriamente el Taller: " + getTitulo() + " dictado por " + ENTIDAD_EMISORA;
    }
}



