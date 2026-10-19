# Ejercicio 3. Un productor genera 5 números y un consumidor los procesa con una Queue.
from multiprocessing import Process, Queue
import time


def productor(q):
    for numero in range(1, 6):  # Genera 5 números
        print(f"Productor: genera {numero}")
        q.put(numero)  # Mete el número en la cola
        time.sleep(0.5)
    q.put(None)  # Señal de fin: avisa al consumidor de que no habrá más datos


def consumidor(q):
    while True:
        numero = q.get()  # Saca un dato; si la cola está vacía, espera
        if numero is None:  # Llegó la señal de fin
            break
        print(f"Consumidor: procesa {numero} -> {numero} al cuadrado = {numero ** 2}")


if __name__ == '__main__':
    q = Queue()  # Cola compartida entre los dos procesos (tiene bloqueos internos)
    p1 = Process(target=productor, args=(q,))   # La coma hace que args sea una tupla
    p2 = Process(target=consumidor, args=(q,))
    p1.start()
    p2.start()
    p1.join()  # Espera a que terminen los dos
    p2.join()
    print("Productor y consumidor han terminado.")
