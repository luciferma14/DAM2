# Ejercicio 2. Tres procesos hijos con timeouts distintos; el padre espera a los tres.
from multiprocessing import Process
import os
import time


def hijo(nombre, timeout):
    print(f"{nombre} (PID {os.getpid()}) empieza y esperará {timeout} s")
    time.sleep(timeout)  # Simula el timeout del hijo
    print(f"{nombre} (PID {os.getpid()}) ha terminado tras {timeout} s")


# Necesario en Windows para que los hijos no relancen el programa
if __name__ == '__main__':
    timeouts = [2, 4, 6]  # Un timeout distinto para cada hijo
    procesos = []         # Lista para guardar los procesos

    for i, t in enumerate(timeouts, start=1):
        p = Process(target=hijo, args=(f"Hijo {i}", t))  # args es una tupla
        procesos.append(p)
        p.start()  # Arranca el hijo; todos corren a la vez

    # Se hace join de todos después de arrancarlos: tarda unos 6 s y no 12
    for p in procesos:
        p.join()  # El padre espera a que cada hijo termine

    print("El padre ha comprobado que los tres hijos han terminado.")
