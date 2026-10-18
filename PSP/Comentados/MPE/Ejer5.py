# Ejercicio 5 - Dos procesos modifican un contador compartido usando Value
# Se usa un Lock para que solo un proceso a la vez modifique el contador (sección crítica).
from multiprocessing import Process, Value, Lock

INCREMENTOS = 100000


def trabajador(lock, contador):
    for _ in range(INCREMENTOS):
        with lock:  # sección crítica: solo un proceso a la vez
            contador.value += 1


if __name__ == '__main__':
    contador = Value('i', 0)  # contador compartido inicializado a 0
    lock = Lock()

    p1 = Process(target=trabajador, args=(lock, contador))
    p2 = Process(target=trabajador, args=(lock, contador))
    p1.start()
    p2.start()
    p1.join()
    p2.join()

    print(f"Valor esperado: {2 * INCREMENTOS}")
    print(f"Valor final del contador: {contador.value}")