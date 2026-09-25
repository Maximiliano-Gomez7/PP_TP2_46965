package modelo;
import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
    }

    public void confirmar() { this.estado = "Confirmada"; }
    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }


    public class TicketDeAcceso implements Serializable {
        private String idTicket;

        public TicketDeAcceso(String idTicket) {
            this.idTicket = idTicket;
        }

        public void enviarTicket() {
            System.out.println("Enviando " + idTicket + " al estudiante " + estudiante.getNombre());
        }
    }
}



