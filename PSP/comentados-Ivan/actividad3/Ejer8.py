# Ejercicio 8. Matar todos los procesos que tengan un nombre dado (psutil)
import psutil

# Primero se muestran todos los procesos con su PID
for proc in psutil.process_iter():
    try:
        print(proc.name(), ' :: ', proc.pid)
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        pass  # Si no se puede leer, se ignora

nombre = input("Nombre del proceso que quieres terminar: ")
encontrado = False  # Sirve para saber si existía algún proceso con ese nombre
for proc in psutil.process_iter():
    try:
        if proc.name() == nombre:
            encontrado = True
            pid = proc.pid
            print("Killing process: ", nombre, ' ::: ', pid)
            proc.kill()  # Envía SIGKILL (en Windows llama a terminate)
            print("Proceso", pid, "terminado correctamente")
    except psutil.NoSuchProcess:
        print("Error: el proceso ya no existe")
    except psutil.AccessDenied:
        print("Error: no tienes permisos para terminar el proceso")
    except psutil.ZombieProcess:
        print("Error: el proceso es un zombie")

if not encontrado:
    print("No existe ningún proceso con ese nombre")
