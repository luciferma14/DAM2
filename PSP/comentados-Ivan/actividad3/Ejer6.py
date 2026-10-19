# Ejercicio 6. Listar todos los procesos con su nombre, PID y prioridad (psutil)
import psutil

# process_iter() recorre todos los procesos del sistema
for proc in psutil.process_iter():
    try:
        # name() = nombre, pid = identificador, nice() = prioridad
        print(proc.name(), ' :: ', proc.pid, ' :: ', proc.nice())
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        # El proceso ha desaparecido, no hay permisos o es un zombie
        print("error")
