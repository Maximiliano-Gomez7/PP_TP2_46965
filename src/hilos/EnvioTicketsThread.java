package hilos;
import modelo.EventoUniversitario;
import actividades.Actividad;
import modelo.Inscripcion;

public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("\n[HILO 2] Iniciando envío de tickets de acceso.");
        for (Actividad act : evento.getActividades()) {
            for (Inscripcion ins : act.getInscripciones()) {
                if (ins.getEstado().equals("Confirmada")) {
                    Inscripcion.TicketDeAcceso ticket = ins.new TicketDeAcceso("TKT-" + (int)(Math.random() * 1000));
                    ticket.enviarTicket();
                    try {
                        Thread.sleep(1000); // Simulamos el retraso de red al enviar el ticket
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("[HILO 2] Todos los tickets fueron enviados.");
    }
}