# PP - Trabajo Práctico 2
 
Programación Orientada a Objetos en Java - Unidad 2
 
**Materia:** Paradigmas de Programación

**Institución:** UTN - FRM

**Alumno:** Maximiliano Gómez 

**Legajo:** 46965
 
## Descripción
 
Se extiende el modelo de eventos universitarios desarrollado en el TP1, incorporando organización en paquetes, 
manejo de excepciones propias, serialización de objetos, interfaces, generics con bounded types y wildcards, 
clases anidadas e hilos.
 
## Estructura del proyecto
- 📁 `src/`
  - 📄 `App.java`
  - 📁 `excepciones/`
    - 📄 `CupoExcedidoException.java`
  - 📁 `modelo/`
    - 📄 `EventoUniversitario.java`
    - 📄 `Sala.java`
    - 📄 `Estudiante.java`
    - 📄 `Inscripcion.java`
  - 📁 `actividades/`
    - 📄 `Actividad.java`
    - 📄 `Charla.java`
    - 📄 `Taller.java`
    - 📄 `Curso.java`
  - 📁 `certificacion/`
    - 📄 `Certificable.java`
  - 📁 `hilos/`
    - 📄 `EnvioTicketsThread.java`

## Funcionalidades Implementadas

*   **Ejercicio 1 - Excepciones y Persistencia:** Se implementó `CupoExcedidoException` para controlar el límite máximo de estudiantes en las actividades. Además, se agregó la funcionalidad de guardar y recuperar eventos desde el disco mediante la serialización de objetos en archivos `.dat`.
*   **Ejercicio 2 - Interfaces:** Se diseñó la interfaz `Certificable` aplicada específicamente a las clases `Taller` y `Curso`, permitiendo emitir certificados de asistencia a los estudiantes (excluyendo a las charlas, que no son certificables).
*   **Ejercicio 3 - Genéricos (Wildcards):** Se incorporaron métodos parametrizados en `EventoUniversitario` (`filtrarActividadesPorTipo` y `calcularCostoMateriales`) para operar sobre listas dinámicas fuertemente tipadas utilizando wildcards (`<? extends Actividad>`).
*   **Ejercicio 4 - Clases Anidadas y Concurrencia:** Se modeló `TicketDeAcceso` como una clase anidada dentro de `Inscripcion` (ya que un ticket solo tiene sentido dentro de una inscripción). Por otro lado, la clase `EnvioTicketsThread` extiende de `Thread` para enviar todos los tickets confirmados de forma concurrente, permitiendo que el hilo principal del programa siga ejecutándose sin interrupciones.

## Ejecución del Sistema

El punto de entrada del sistema es la clase principal `App.java` (sin paquete asignado). Al ejecutarla, la consola de salida evidencia:
1.  Pruebas de inscripciones exitosas y fallos controlados (try-catch-finally).
2.  Persistencia exitosa del evento en disco (escritura y lectura).
3.  Emisión y visualización de certificados.
4.  Filtrado y cálculo de costos agrupados por tipo de actividad.
5.  Demostración de hilos concurrentes (impresiones del hilo principal alternadas con el envío de tickets).

## Compilación y ejecución
 
Desde la carpeta raíz del proyecto:
 
```bash
javac -d out -sourcepath src src/App.java
java -cp out App
```



## Resultado por consola

<img width="1293" height="587" alt="WhatsApp Image 2026-09-25 at 12 35 10 AM" src="https://github.com/user-attachments/assets/9758a22d-18cb-42e0-a646-a7f731d97a1a" />

<img width="1162" height="604" alt="WhatsApp Image 2026-09-25 at 12 35 37 AM" src="https://github.com/user-attachments/assets/9e9bd407-4c05-4e5c-9f03-62a7c548619b" />

