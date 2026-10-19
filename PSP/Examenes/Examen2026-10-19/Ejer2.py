from multiprocessing import Process
import random, sys


def hijo():
    numeros = random.randint(1, 3)
    print(numeros)


def padre():
    try:
        total = 3
    except ValueError:
        print("Introduce un número entero válido.")
        return

    procesos = []
    while len(procesos) < total:
        p = Process(target=hijo)
        p.start()
        procesos.append(p)

    # El padre espera a que terminen todos los hijos
    for p in procesos:
        p.join()
    print(f"Padre: los {len(procesos)} procesos hijo han terminado.")


if __name__ == '__main__':
    padre()