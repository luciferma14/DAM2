import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;
import academia.*;

public class Ejer3 {

    // Colas de objetos pendientes de vincular
    static Queue<Curso> cursosPendientes = new LinkedList<>();                // cursos sin alumno
    static Queue<Alumno> alumnosSinCurso = new LinkedList<>();                // alumnos sin curso
    static Queue<Alumno> alumnosSinExpediente = new LinkedList<>();           // alumnos sin expediente
    static Queue<Expediente> expedientesSinAlumno = new LinkedList<>();       // expedientes sin alumno

    // Todos los alumnos creados (para guardarlos al final)
    static List<Alumno> alumnos = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_ACADEMIA");

        try {
            int opcion = -1;

            while (opcion != 0) {
                System.out.println();
                System.out.println("===== MENÚ =====");
                System.out.println("1. Crear curso");
                System.out.println("2. Crear alumno");
                System.out.println("3. Crear expediente");
                System.out.println("4. Ver objetos pendientes");
                System.out.println("5. Guardar todo en la base de datos");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");

                opcion = -1;

                try {
                    opcion = Integer.parseInt(sc.nextLine());

                    switch (opcion) {
                        case 1:
                            crearCurso(sc);
                            break;
                        case 2:
                            crearAlumno(sc);
                            break;
                        case 3:
                            crearExpediente(sc);
                            break;
                        case 4:
                            verPendientes();
                            break;
                        case 5:
                            guardarTodo(emf);
                            break;
                        case 0:
                            System.out.println("Hasta pronto");
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Dato no válido: " + e.getMessage());
                }
            }

        } finally {
            emf.close();
            sc.close();
        }
    }

    // Crea un curso. Si hay un alumno esperando curso se vincula con él;
    // si no, el curso se añade a la cola de cursos pendientes.
    static void crearCurso(Scanner sc) {

        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine();
        System.out.print("Código del curso: ");
        String codigo = sc.nextLine();
        System.out.print("Horas: ");
        int horas = Integer.parseInt(sc.nextLine());
        System.out.print("Precio: ");
        BigDecimal precio = new BigDecimal(sc.nextLine());

        Curso curso = new Curso(nombre, codigo, horas, precio);

        if (!alumnosSinCurso.isEmpty()) {
            Alumno alumno = alumnosSinCurso.poll();
            alumno.setCurso(curso);
            System.out.println("Curso creado y vinculado al alumno " + alumno.getDni());
        } else {
            cursosPendientes.add(curso);
            System.out.println("Curso creado y añadido a la cola de cursos pendientes");
        }
    }

    // Crea un alumno. Se vincula con el primer curso de la cola (si hay)
    // y con el primer expediente sin alumno (si hay).
    static void crearAlumno(Scanner sc) {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Alumno alumno = new Alumno(nombre, apellidos, dni, email);
        alumnos.add(alumno);

        // Vinculación con el curso
        if (!cursosPendientes.isEmpty()) {
            alumno.setCurso(cursosPendientes.poll());
            System.out.println("Alumno vinculado a un curso pendiente");
        } else {
            alumnosSinCurso.add(alumno);
            System.out.println("No hay cursos pendientes: el alumno esperará al próximo curso");
        }

        // Vinculación con el expediente
        if (!expedientesSinAlumno.isEmpty()) {
            alumno.setExpediente(expedientesSinAlumno.poll());
            System.out.println("Alumno vinculado a un expediente pendiente");
        } else {
            alumnosSinExpediente.add(alumno);
            System.out.println("No hay expedientes pendientes: el alumno esperará al próximo expediente");
        }
    }

    // Crea un expediente. Se vincula con el primer alumno sin expediente (si hay);
    // si no, se añade a la cola de expedientes sin alumno.
    static void crearExpediente(Scanner sc) {

        System.out.print("Número de expediente: ");
        String numero = sc.nextLine();
        System.out.print("Fecha de alta (aaaa-mm-dd): ");
        LocalDate fechaAlta = LocalDate.parse(sc.nextLine());
        System.out.print("Nota media: ");
        BigDecimal notaMedia = new BigDecimal(sc.nextLine());

        Expediente expediente = new Expediente(numero, fechaAlta, notaMedia);

        if (!alumnosSinExpediente.isEmpty()) {
            Alumno alumno = alumnosSinExpediente.poll();
            alumno.setExpediente(expediente);
            System.out.println("Expediente creado y vinculado al alumno " + alumno.getDni());
        } else {
            expedientesSinAlumno.add(expediente);
            System.out.println("Expediente creado y añadido a la cola de expedientes sin alumno");
        }
    }

    // Muestra cuántos objetos están esperando ser vinculados
    static void verPendientes() {
        System.out.println("Alumnos creados: " + alumnos.size());
        System.out.println("Cursos sin alumno: " + cursosPendientes.size());
        System.out.println("Alumnos sin curso: " + alumnosSinCurso.size());
        System.out.println("Alumnos sin expediente: " + alumnosSinExpediente.size());
        System.out.println("Expedientes sin alumno: " + expedientesSinAlumno.size());
    }

    // Guarda todo en una única transacción.
    // Si hay un alumno sin curso o sin expediente, o un expediente sin alumno,
    // se produce una excepción y se hace rollback de toda la transacción.
    static void guardarTodo(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Cursos que no se han vinculado a ningún alumno
            for (Curso curso : cursosPendientes) {
                em.persist(curso);
            }

            // Alumnos: la cascada guarda también su curso y su expediente
            for (Alumno alumno : alumnos) {
                em.persist(alumno);
            }

            // Expedientes sin alumno (provocarán error y rollback)
            for (Expediente expediente : expedientesSinAlumno) {
                em.persist(expediente);
            }

            tx.commit();

            System.out.println("Todos los datos se han guardado correctamente.");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
            System.out.println("Se ha hecho rollback: no se ha guardado nada.");
        } finally {
            em.close();
        }

        // Tanto si ha ido bien como si ha habido rollback, empezamos de cero
        cursosPendientes.clear();
        alumnosSinCurso.clear();
        alumnosSinExpediente.clear();
        expedientesSinAlumno.clear();
        alumnos.clear();
    }
}