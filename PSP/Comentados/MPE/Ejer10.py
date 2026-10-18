# Ejercicio 10 - Dos productores generan números y un consumidor los procesa, usando una Queue
from multiprocessing import Process, Queue, current_process
import random
import time

NUM_PRODUCTORES = 2


def productor(q):
    nombre = current_process().name
    for _ in range(5):
        numero = random.randint(1, 100)
        print(f"{nombre}: genera {numero}")
        q.put((nombre, numero))
        time.sleep(random.uniform(0.1, 0.5))
    q.put(None)  # señal de fin de este productor


def consumidor(q):
    terminados = 0
    while terminados < NUM_PRODUCTORES:  # hasta que acaben los dos productores
        dato = q.get()
        if dato is None:
            terminados += 1
            continue
        nombre, numero = dato
        print(f"Consumidor: procesa {numero} de {nombre} -> doble = {numero * 2}")
    print("Consumidor: los dos productores han terminado.")


if __name__ == '__main__':
    q = Queue()
    productores = [Process(target=productor, args=(q,), name=f"Productor-{i + 1}")
                   for i in range(NUM_PRODUCTORES)]
    c = Process(target=consumidor, args=(q,))

    for p in productores:
        p.start()
    c.start()

    for p in productores:
        p.join()
    c.join()
    print("Padre: todos los procesos han terminado.")