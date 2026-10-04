import psutil

NICE_MIN = -20
NICE_MAX = 19

def main():
    try:
        pid = int(input("Introduce el PID del proceso: "))
        nueva_prioridad = int(input(f"Introduce la nueva prioridad ({NICE_MIN} a {NICE_MAX}): "))
    except ValueError:
        print("Error: debes introducir números enteros.")
        return

    if not NICE_MIN <= nueva_prioridad <= NICE_MAX:
        print(f"Error: la prioridad debe estar entre {NICE_MIN} y {NICE_MAX}.")
        return

    try:
        proc = psutil.Process(pid)
        print(f"Proceso: {proc.name()}, prioridad actual: {proc.nice()}")
        proc.nice(nueva_prioridad)
        print(f"Nueva prioridad del proceso: {proc.nice()}")
    except psutil.NoSuchProcess:
        print(f"Error: no existe ningún proceso con PID {pid}.")
    except psutil.AccessDenied:
        print("Error: no tienes permiso para cambiar la prioridad de este proceso.")
    except Exception as e:
        print(f"Error inesperado: {e}")


main()