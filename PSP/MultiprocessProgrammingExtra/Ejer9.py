from multiprocessing import Process, Value, Lock, Event
import random
import time

META = 100


def corredor(numero, lock, ganador, fin_carrera):
    pasos = 0
    while not fin_carrera.is_set(): 
        pasos += random.randint(1, 10) 
        print(f"Corredor {numero}: lleva {min(pasos, META)} pasos")
        if pasos >= META:
            with lock: 
                if ganador.value == 0:
                    ganador.value = numero
                    fin_carrera.set()
            break
        time.sleep(random.uniform(0.05, 0.15)) 


if __name__ == '__main__':
    ganador = Value('i', 0) 
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