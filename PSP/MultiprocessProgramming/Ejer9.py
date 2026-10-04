import time
import psutil


def poll(proc):
    try:
        if proc.is_running() and proc.status() != psutil.STATUS_ZOMBIE:
            return None
    except psutil.NoSuchProcess:
        pass
    return 0


def listar_procesos():
    for proc in psutil.process_iter():
        try:
            print(f"{proc.name()} :: {proc.pid}")
        except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
            pass


def main():
    listar_procesos()
    try:
        pid = int(input("\nIntroduce el PID del proceso: "))
    except ValueError:
        print("Error: el PID debe ser un número entero.")
        return

    try:
        proc = psutil.Process(pid)
        print(f"Proceso seleccionado: {proc.name()} ({pid})")
        respuesta = input("¿Quieres matar este proceso? (y/n): ")
        if respuesta.lower() != 'y':
            print("El proceso no ha sido terminado.")
            return

        proc.kill()
        print("Señal de terminación enviada. Monitorizando el proceso...")

        while poll(proc) is None:
            print("El proceso sigue en ejecución...")
            time.sleep(0.5)
        print(f"El proceso {pid} ha terminado.")
    except psutil.NoSuchProcess:
        print(f"Error: no existe ningún proceso con PID {pid}.")
    except psutil.AccessDenied:
        print("Error: no tienes permiso para matar este proceso.")
    except Exception as e:
        print(f"Error inesperado: {e}")


main()