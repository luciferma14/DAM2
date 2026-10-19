# PSP · UP1 · Multiprocess Programming

Resumen de todas las actividades de la unidad: qué hace cada ejercicio, qué herramienta de la teoría usa y cómo ejecutarlo.

## Índice

- [Actividad 3 (Ejer1 a Ejer14)](#actividad-3)
- [Actividad 4 Extra (ej01 a ej10)](#actividad-4-extra)
- [Herramientas de la teoría de un vistazo](#herramientas-de-la-teoría-de-un-vistazo)

---

## Actividad 3

Carpeta con `Ejer1.py` a `Ejer14.py` y `script.py`. Ejecución general: `python EjerN.py`.

> `script.py` es un script auxiliar que solo imprime los números del 1 al 5. Lo usan `Ejer1.py` y `Ejer14.py`.

### Ejer1 · Lanzar un script y capturar su salida
- **Herramienta:** `subprocess.run` con `capture_output=True`, `text=True` y `check=True`.
- **Cómo funciona:** lanza `script.py` con el mismo intérprete (`sys.executable`), espera a que termine y muestra lo que imprimió (`resultado.stdout`).
- **Errores controlados:** `FileNotFoundError` (no existe el script o el intérprete) y `CalledProcessError` (el script termina con error).
- **Salida esperada:** "Salida del script:" y los números 1 a 5.

### Ejer2 · Proceso hijo con `fork` que cuenta
- **Herramienta:** `os.fork()`, `os.wait()`, `os._exit(0)`.
- **Cómo funciona:** `fork()` devuelve 0 en el hijo y el PID del hijo en el padre. El hijo cuenta de 1 a 5 con 1 segundo entre cada número y sale con `os._exit(0)`. El padre espera con `os.wait()` y avisa de que el hijo ha terminado.
- **Ojo:** `fork` solo funciona en Linux y macOS, no en Windows.

### Ejer3 · Crear N procesos hijos
- **Herramienta:** `multiprocessing.Process`, `start()` y `join()`.
- **Cómo funciona:** pide cuántos hijos crear. Cada hijo imprime su PID y el de su padre (`os.getpid()` y `os.getppid()`). Entre un hijo y el siguiente pregunta si quieres crear otro (`y`) o parar. Al final el padre hace `join()` de todos.
- **Detalle:** primero se hace `start()` de todos y luego `join()`, para que corran en paralelo.

### Ejer4 · El hijo suma y el padre recibe el resultado
- **Herramienta:** `Process` y `Queue`.
- **Cómo funciona:** el hijo suma de 1 a N, imprime la suma y la mete en la cola con `q.put()`. El padre la recoge con `q.get()` y la muestra.
- **Por qué `Queue`:** los procesos no comparten memoria, así que una variable normal modificada por el hijo no llegaría al padre.

### Ejer5 · Ejecutar comandos del sistema en bucle
- **Herramienta:** `subprocess.run(comando.split(), check=True)`.
- **Cómo funciona:** el usuario escribe un comando con sus parámetros (por ejemplo `ping www.google.com -c 5`). Se parte en una lista con `split()` y se ejecuta. Después pregunta si quiere ejecutar otro (`s`/`n`).
- **Errores controlados:** comando inexistente (`FileNotFoundError`) y comando que falla (`CalledProcessError`, muestra el código de salida).

### Ejer6 · Listar procesos con su prioridad
- **Herramienta:** `psutil.process_iter()`, `proc.name()`, `proc.pid`, `proc.nice()`.
- **Cómo funciona:** recorre todos los procesos del sistema e imprime nombre, PID y prioridad.
- **Errores controlados:** `NoSuchProcess`, `AccessDenied` y `ZombieProcess`, porque algunos procesos desaparecen o no se pueden leer.

### Ejer7 · Cambiar la prioridad de un proceso
- **Herramienta:** `psutil.Process(pid).nice(nueva)`.
- **Cómo funciona:** pide un PID y una prioridad entre -20 y 19. Valida el rango, la aplica y muestra la nueva prioridad.
- **Errores controlados:** valor no numérico (`ValueError`), proceso inexistente y falta de permisos. Bajar el valor de `nice` (más prioridad) suele requerir permisos de administrador.
- **Ojo:** el rango de -20 a 19 es el de Linux.

### Ejer8 · Matar procesos por nombre
- **Herramienta:** `psutil.process_iter()` y `proc.kill()`.
- **Cómo funciona:** lista los procesos, pregunta un nombre y mata todos los que se llamen así. Si no hay ninguno, avisa.
- **Errores controlados:** `NoSuchProcess`, `AccessDenied` y `ZombieProcess`.

### Ejer9 · Matar un proceso por PID y esperar a que termine
- **Herramienta:** `psutil.Process`, `kill()`, `is_running()`, `status()`.
- **Cómo funciona:** lista los procesos y pide un PID. Pregunta si quieres matarlo. Si dices que sí, hace `kill()` y espera en un bucle mostrando "El proceso sigue activo..." hasta que desaparece o queda como zombie.
- **Por qué el bucle:** la eliminación de un proceso es asíncrona, puede seguir existiendo un momento después de `kill()`.

### Ejer10 · Comando síncrono frente a asíncrono
- **Herramienta:** `subprocess.run` (síncrono) y `asyncio.create_subprocess_exec` (asíncrono).
- **Cómo funciona:** el usuario escribe un comando y el programa lo ejecuta dos veces, primero de forma síncrona y luego de forma asíncrona. En la versión asíncrona se usa `await proc.communicate()` para leer la salida.
- **Idea clave:** `run()` bloquea el programa hasta que termina, y la versión con `async`/`await` no bloquea mientras espera.

### Ejer11 · Abrir varias aplicaciones a la vez
- **Herramienta:** `asyncio.gather` y `asyncio.create_subprocess_exec`.
- **Cómo funciona:** pide cuántas aplicaciones abrir y sus nombres (`notepad`, `gedit`...). Las abre todas a la vez con `gather` y muestra el PID de cada una. Al final espera un Enter para cerrar el programa.
- **Error controlado:** aplicación inexistente (`FileNotFoundError`).

### Ejer12 · Pipeline entre dos procesos
- **Herramienta:** `subprocess.Popen` con `stdout=PIPE` y `stdin=p1.stdout`.
- **Cómo funciona:** equivale a `generador | sumador` en la consola. El primer proceso genera 10 números aleatorios, los muestra por `stderr` para que se vean y los manda por `stdout`. El segundo los lee por `stdin` y muestra la suma. Se cierra `p1.stdout` en el padre y se espera a los dos procesos con `wait()`.
- **Truco:** el código de cada proceso va en un texto y se ejecuta con `python -c "..."`.

### Ejer13 · Enviar un texto a un hijo por stdin
- **Herramienta:** `subprocess.run(..., input=texto, capture_output=True)`.
- **Cómo funciona:** el usuario escribe un texto, el padre se lo manda al hijo por `stdin` y el hijo cuenta las palabras y las devuelve por `stdout`. El padre muestra el número.

### Ejer14 · Igual que Ejer1
- Mismo código que `Ejer1.py`: ejecuta `script.py` y muestra su salida.

---

## Actividad 4 Extra

Carpeta `ejercicios_psp_extra` con `ej01` a `ej10`. Todos usan `multiprocessing` y funcionan en Windows y Linux, salvo el 1, que es un comando de Linux. Ejecución: `python ejNN_nombre.py`.

### ej01 · Pipeline en Linux (`ej01_pipeline.sh`)
- **Qué hace:** lista los procesos Python ordenados por PID.
- **Comando:** `ps -eo pid,cmd | grep [p]ython | sort -n`.
- **Cómo funciona:** `ps` lista procesos con PID y comando, `grep [p]ython` se queda con los de Python (los corchetes evitan que el propio `grep` salga) y `sort -n` ordena por la primera columna, el PID.
- **Ejecución:** `bash ej01_pipeline.sh`.

### ej02 · Tres hijos con timeouts distintos
- **Herramienta:** `Process`, `time.sleep`, `join`.
- **Cómo funciona:** tres hijos esperan 2, 4 y 6 segundos. El padre hace `start()` de los tres y después `join()` de los tres, así que tarda unos 6 s en total y no 12.

### ej03 · Productor y consumidor con Queue
- **Herramienta:** `Queue`.
- **Cómo funciona:** el productor mete 5 números en la cola y el consumidor los saca y los procesa (calcula el cuadrado). El productor termina enviando `None` como señal de fin y el consumidor sale del bucle al recibirlo.

### ej04 · Comunicación con Pipe
- **Herramienta:** `Pipe`, `send`, `recv`.
- **Cómo funciona:** `Pipe()` devuelve dos extremos. Un proceso envía un mensaje por uno y el otro lo recibe por el otro. Un pipe conecta solo dos procesos y no almacena datos.

### ej05 · Contador compartido
- **Herramienta:** `Value`, `Lock`.
- **Cómo funciona:** dos procesos incrementan 10.000 veces cada uno un entero compartido (`Value('i', 0)`). El `with lock:` evita la condición de carrera. El resultado final es 20000.

### ej06 · Factorial en paralelo
- **Herramienta:** `Process`, `Queue`.
- **Cómo funciona:** 20! se divide en 4 rangos (1-5, 6-10, 11-15, 16-20). Cada proceso multiplica su rango y mete el producto parcial en la cola. El padre recoge los 4 resultados y los multiplica. Resultado: 2432902008176640000.

### ej07 · Suma de 1 a 1.000.000 en 4 partes
- **Herramienta:** `Process`, `Queue`.
- **Cómo funciona:** la lista se parte en 4 trozos de 250.000 con slicing. Cada proceso suma el suyo y lo devuelve por la cola. El padre suma las 4 sumas parciales. Resultado: 500000500000.

### ej08 · Tres procesos escribiendo en el mismo log
- **Herramienta:** `Lock`.
- **Cómo funciona:** tres procesos escriben 5 mensajes cada uno en `log.txt`. Cada escritura va dentro de `with lock:` para que las líneas no se mezclen. Al final el padre muestra el contenido del fichero.

### ej09 · Carrera de 5 procesos
- **Herramienta:** `Value`, `Lock`, `Event`.
- **Cómo funciona:** cada corredor avanza entre 1 y 10 pasos por turno con `random`. Al llegar a 100 toma el `Lock`, comprueba que nadie ha ganado, guarda su número en el `Value` y hace `fin.set()`. El resto comprueba `fin.is_set()` en cada vuelta y se para. El padre anuncia al ganador.

### ej10 · Dos productores y un consumidor
- **Herramienta:** `Queue`.
- **Cómo funciona:** dos productores generan 5 números aleatorios cada uno y los meten en la misma cola junto con su nombre. Cada uno termina enviando un `None`. El consumidor procesa los números (calcula el doble) y se detiene cuando ha recibido los dos `None`.

---

## Herramientas de la teoría de un vistazo

| Necesito... | Uso |
|---|---|
| Crear un proceso que ejecute una función | `multiprocessing.Process(target=f, args=(...,))` |
| Esperar a un hijo | `p.join()` (con `os.fork()`, `os.wait()`) |
| Lanzar un programa externo y esperar | `subprocess.run([...])` |
| Lanzar un programa externo sin bloquear | `subprocess.Popen([...])` |
| Listar o matar procesos, ver o cambiar la prioridad | `psutil` (`process_iter`, `kill`, `nice`) |
| Hacer varias cosas a la vez sin bloquear | `asyncio` (`async def`, `await`, `gather`, `run`) |
| Pasar datos entre procesos | `Queue` (varios procesos) o `Pipe` (solo dos) |
| Compartir una variable | `Value` y `Lock` |
| Limitar cuántos entran a la vez | `Semaphore(n)` |
| Avisar a otros procesos | `Event` (`wait`, `set`, `is_set`) |
| Hacer que todos esperen a todos | `Barrier(n)` |
