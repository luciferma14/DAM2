# Ejercicio 3 - Un productor genera 5 números y un consumidor los procesa usando una Queue
from multiprocessing import Process, Queue
import random
import time


def productor(q):
    for _ in range(5):
        numero = random.randint(1, 100)
        print(f"Productor: genera {numero}")
        q.put(numero)  # mete el número en la cola
        time.sleep(0.5)
    q.put(None)  # señal de fin: no habrá más números


def consumidor(q):
    while True:
        numero = q.get()  # saca un número de la cola (espera si está vacía)
        if numero is None:
            break
        print(f"Consumidor: procesa {numero} -> su cuadrado es {numero ** 2}")
    print("Consumidor: no hay más números.")


if __name__ == '__main__':
    q = Queue()  # crea la cola
    p1 = Process(target=productor, args=(q,))
    p2 = Process(target=consumidor, args=(q,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Padre: productor y consumidor han terminado.")