# Ejercicio 2. Proceso hijo con fork que cuenta de 1 a 5 (solo Linux/macOS)
import os
import time


def hijo():
    # Código que ejecuta el proceso hijo
    for i in range(1, 6):
        print(i)
        time.sleep(1)  # Espera 1 segundo entre número y número
    os._exit(0)  # El hijo termina inmediatamente sin seguir el código del padre


# fork() crea el proceso hijo
# Devuelve 0 en el hijo y el PID del hijo en el padre
newpid = os.fork()
if newpid == 0:
    hijo()  # Estamos en el hijo
else:
    os.wait()  # El padre espera a que termine el hijo
    print("The child process has finished.")
