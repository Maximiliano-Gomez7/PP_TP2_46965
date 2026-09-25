package modelo;
 import actividades.*;
 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;

 public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id + "_C";
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        cantidadEventos++;
    }

    public double calcularCostoEstimado() { return gratuito ? 0 : costoBase; }
        public void asignarSala(Sala sala) { this.sala = sala; }

    public void crearActividad(int id, String titulo, int cupo, String tipo) {
        Actividad nueva = null;
            switch (tipo.toLowerCase()) {
            case "charla": nueva = new Charla(id, titulo, cupo, "Disertante Asignado"); break;
            case "taller": nueva = new Taller(id, titulo, cupo, true); break;
            case "curso":  nueva = new Curso(id, titulo, cupo, 1); break;
            }
        if (nueva != null) actividades.add(nueva);
    }


        public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
            List<T> filtradas = new ArrayList<>();
            for (Actividad a : actividades) {
                if (tipo.isInstance(a)) {
                filtradas.add(tipo.cast(a));
                }
            }
            return filtradas;
        }

        public double calcularCostoMateriales(List<? extends Actividad> acts) {
            double total = 0;
            for (Actividad a : acts) {
                total += a.calcularCostoMateriales();
            }
            return total;
        }

        public void mostrarDatos() {
            System.out.println("Evento: " + titulo + " | Sala: " + (sala != null ? sala.toString() : "N/A") + " | Actividades: " + actividades.size());
        }


        public boolean persistirEvento() {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(id + ".dat"))) {
                out.writeObject(this);
             return true;
            }
            catch (IOException e) {
                System.err.println("Error al guardar: " + e.getMessage());
                return false;
                }
        }

        public static EventoUniversitario recuperarEvento(String id) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(id + ".dat"))) {
                return (EventoUniversitario) in.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al recuperar: " + e.getMessage());
                return null;
            }
        }

        public static int getCantidadEventos() { return cantidadEventos; }
        public List<Actividad> getActividades() { return actividades; }
        public String getTitulo() { return titulo; }
 }



