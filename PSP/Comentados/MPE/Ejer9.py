# Ejercicio 9 - Carrera entre 5 procesos
# En cada turno, cada proceso avanza un número aleatorio de pasos. Gana el primero en llegar a 100.
from multiprocessing import Process, Value, Lock, Event
import random
import time

META = 100


def corredor(numero, lock, ganador, fin_carrera):
    pasos = 0
    while not fin_carrera.is_set():  # mientras nadie haya ganado
        pasos += random.randint(1, 10)  # avanza un número aleatorio de pasos
        print(f"Corredor {numero}: lleva {min(pasos, META)} pasos")
        if pasos >= META:
            with lock:  # solo uno puede declararse ganador
                if ganador.value == 0:
                    ganador.value = numero
                    fin_carrera.set()  # avisa al resto de que la carrera ha terminado
            break
        time.sleep(random.uniform(0.05, 0.15))  # tiempo aleatorio por turno


if __name__ == '__main__':
    ganador = Value('i', 0)  # 0 = todavía no hay ganador
    lock = Lock()
    fin_carrera = Event()
    procesos = []

    print("¡Empieza la carrera!")
    for i in range(1, 6):
        p = Process(target=corredor, args=(i, lock, ganador, fin_carrera))
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print(f"¡El ganador es el corredor {ganador.value}!")