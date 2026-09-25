 import modelo.*;
 import actividades.*;
 import certificacion.Certificable;
 import excepciones.CupoExcedidoException;
 import hilos.EnvioTicketsThread;
 import java.util.List;

 public class App {
     public static void main(String[] args) {


        Estudiante est1 = new Estudiante("10001", "Hannah Arendt");
        Estudiante est2 = new Estudiante("10002", "Damian Calderon");
        Estudiante est3 = new Estudiante("10003", "Roman Pisculichi");


        EventoUniversitario evento = new EventoUniversitario("E-UTN-01", "Jornadas de Sistemas", 2000, false);
        evento.asignarSala(new Sala(1, "Laboratorio 3"));


        evento.crearActividad(1, "Hegel, dialectica e historia en el idealismo ", 2, "Charla");
        evento.crearActividad(2, "Patrones de Diseño en Java", 15, "Taller");
        evento.crearActividad(3, "Cómo patear un tiro libre", 20, "Curso");

        Actividad charla = evento.getActividades().get(0);
        Actividad taller = evento.getActividades().get(1);
        Actividad curso = evento.getActividades().get(2);


        try {
            charla.inscribir(est1).confirmar();
            System.out.println("Inscripción exitosa: " + est1.getNombre());
            charla.inscribir(est2).confirmar();
            System.out.println("Inscripción exitosa: " + est2.getNombre());
            charla.inscribir(est3).confirmar();
            System.out.println("Inscripción exitosa: " + est3.getNombre());
        }
        catch (CupoExcedidoException e) {
            System.err.println("FALLO CONTROLADO: " + e.getMessage());
        }
        finally {
            System.out.println("Operación de inscripción en charla finalizada.");
        }


        try {
            taller.inscribir(est1).confirmar();
            taller.inscribir(est3).confirmar();
            curso.inscribir(est2).confirmar();
        }
        catch (CupoExcedidoException e) { }

        // Persistencia
        System.out.println("\n---PERSISTENCIA ---");
        evento.persistirEvento();
        EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("E-UTN-01");
        if(eventoRecuperado != null) {
        System.out.println("Evento recuperado correctamente de disco:");
        eventoRecuperado.mostrarDatos();
        }

        // Emisión de certificados
        System.out.println("\n--- EMISIÓN DE CERTIFICADOS ---");
        for (Actividad act : evento.getActividades()) {
            if (act instanceof Certificable) {
                for (Inscripcion ins : act.getInscripciones()) {
                System.out.println(((Certificable) act).generarCertificado(ins.getEstudiante()));
                }
            }
        }

        // Filtrado tipado y costos por comodines
        System.out.println("\n---FILTRADO---");
        List<Taller> talleresFiltrados = evento.filtrarActividadesPorTipo(Taller.class);
        System.out.println("Talleres encontrados: " + talleresFiltrados.size());
        System.out.println("Costo en materiales de Talleres: $" + evento.calcularCostoMateriales(talleresFiltrados));

        List<Curso> cursosFiltrados = evento.filtrarActividadesPorTipo(Curso.class);
        System.out.println("Cursos encontrados: " + cursosFiltrados.size());
        System.out.println("Costo en materiales de Cursos: $" + evento.calcularCostoMateriales(cursosFiltrados));

        // Hilos Concurrentes y Clases Anidadas
        System.out.println("\n---(ENVÍO DE TICKETS)---");
        EnvioTicketsThread hiloTickets = new EnvioTicketsThread(evento);
        hiloTickets.start();


        for (int i = 1; i <= 3; i++) {
            System.out.println("[HILO PRINCIPAL] Simulando carga de UI... Tick " + i);
            evento.mostrarDatos();
                try { Thread.sleep(800); }
                    catch (InterruptedException e) { }
        }

    }



 }


