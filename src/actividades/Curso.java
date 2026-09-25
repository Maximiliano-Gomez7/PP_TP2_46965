package actividades;
import certificacion.Certificable;
import modelo.Estudiante;

public class Curso extends Actividad implements Certificable {
    private int nivel;

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    @Override public double calcularCostoMateriales() { return 2500.0 * nivel; }
    @Override public String getTipo() { return "Curso"; }

    @Override public String generarCertificado(Estudiante e) {
        return "El estudiante " + e.getNombre() + " ha aprobado el Curso de Nivel " + nivel + ": " + getTitulo() + " (" + ENTIDAD_EMISORA + ")";
    }
}



