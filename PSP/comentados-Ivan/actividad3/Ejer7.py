# Ejercicio 7. Cambiar la prioridad (nice) de un proceso con psutil
import psutil

try:
    pid = int(input("PID del proceso: "))
    nueva = int(input("Nueva prioridad (-20 a 19): "))
    # El rango válido de nice en Linux es de -20 (máxima prioridad) a 19 (mínima)
    if nueva < -20 or nueva > 19:
        print("La prioridad debe estar entre -20 y 19")
    else:
        proc = psutil.Process(pid)  # Obtiene el proceso a partir del PID
        proc.nice(nueva)            # Cambia la prioridad
        print("Nueva prioridad del proceso:", proc.nice())  # nice() sin argumento la lee
except ValueError:
    print("Debes introducir números enteros")
except psutil.NoSuchProcess:
    print("Error: el proceso no existe")
except psutil.AccessDenied:
    # Subir la prioridad (valores más bajos) suele requerir permisos de administrador
    print("Error: no tienes permisos para cambiar la prioridad")
