# Ejercicio 10. Dos productores generan números y un consumidor los procesa.
# Se usa una Queue. Cada productor avisa con None cuando termina; el consumidor
# acaba cuando ha recibido las dos señales de fin.
from multiprocessing import Process, Queue
import random
import time


def productor(nombre, q):
    for _ in range(5):
        numero = random.randint(1, 100)
        print(f"{nombre}: genera {numero}")
        q.put((nombre, numero))  # Envía una tupla con el nombre y el número
        time.sleep(random.uniform(0.1, 0.5))
    q.put(None)  # Señal de fin de este productor


def consumidor(q, num_productores):
    terminados = 0  # Cuenta cuántos productores han terminado
    while terminados < num_productores:
        dato = q.get()
        if dato is None:
            terminados += 1  # Un productor ha terminado
        else:
            nombre, numero = dato  # Desempaqueta la tupla
            print(f"Consumidor: procesa {numero} de {nombre} -> doble = {numero * 2}")
    print("Consumidor: todos los productores han terminado.")


if __name__ == '__main__':
    q = Queue()  # Una sola cola compartida por los tres procesos
    p1 = Process(target=productor, args=("Productor 1", q))
    p2 = Process(target=productor, args=("Productor 2", q))
    c = Process(target=consumidor, args=(q, 2))

    p1.start()
    p2.start()
    c.start()

    p1.join()
    p2.join()
    c.join()
    print("Fin del programa.")
