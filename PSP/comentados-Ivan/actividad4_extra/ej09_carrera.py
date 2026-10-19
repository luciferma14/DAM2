# Ejercicio 9. Carrera de 5 procesos. En cada turno cada proceso avanza un
# número aleatorio de pasos. El primero en llegar a 100 pasos gana.
# Se usa un Lock + Value para registrar al ganador y un Event para avisar
# al resto de que la carrera ha terminado.
from multiprocessing import Process, Value, Lock, Event
import random
import time


def corredor(numero, ganador, lock, fin):
    pasos = 0
    while not fin.is_set():  # Sigue corriendo mientras nadie haya ganado
        pasos += random.randint(1, 10)  # Avanza un número aleatorio de pasos
        print(f"Corredor {numero} lleva {min(pasos, 100)} pasos")
        if pasos >= 100:
            with lock:  # Evita que dos procesos se declaren ganadores a la vez
                if ganador.value == 0:  # 0 significa que todavía no hay ganador
                    ganador.value = numero
                    fin.set()  # Avisa al resto de que la carrera ha terminado
            break
        time.sleep(random.uniform(0.01, 0.1))  # Pausa aleatoria entre turnos


if __name__ == '__main__':
    ganador = Value('i', 0)  # Entero compartido: número del ganador (0 = nadie)
    lock = Lock()
    fin = Event()  # Se activa cuando alguien gana
    procesos = []

    print("¡Empieza la carrera!")
    for n in range(1, 6):
        p = Process(target=corredor, args=(n, ganador, lock, fin))
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print(f"\n¡El ganador es el corredor {ganador.value}!")
