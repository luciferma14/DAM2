# Ejercicio 6 - Listar todos los procesos en ejecución con nombre, PID y prioridad
import psutil

for proc in psutil.process_iter():
    try:
        nombre = proc.name()
        pid = proc.pid
        prioridad = proc.nice()
        print(f"Nombre: {nombre} :: PID: {pid} :: Prioridad: {prioridad}")
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        # El proceso terminó antes de poder leerlo, o no tenemos permiso
        print("error")