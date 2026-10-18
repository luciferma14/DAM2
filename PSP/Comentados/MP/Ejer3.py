# Ejercicio 3 - Crear N procesos hijo con multiprocessing.Process
from multiprocessing import Process
import os


def hijo():
    print(f"Child: {os.getpid()}, Parent: {os.getppid()}")


def padre():
    try:
        total = int(input("¿Cuántos procesos hijo quieres crear? "))
    except ValueError:
        print("Introduce un número entero válido.")
        return

    procesos = []
    while len(procesos) < total:
        p = Process(target=hijo)
        p.start()
        procesos.append(p)
        # Opción para parar la creación antes de llegar al número pedido
        if len(procesos) < total:
            respuesta = input("Pulsa 'y' para crear otro proceso (cualquier otra tecla para parar): ")
            if respuesta.lower() != 'y':
                break

    # El padre espera a que terminen todos los hijos
    for p in procesos:
        p.join()
    print(f"Padre: los {len(procesos)} proceso(s) hijo han terminado.")


if __name__ == '__main__':
    padre()