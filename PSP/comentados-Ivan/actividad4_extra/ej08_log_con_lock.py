# Ejercicio 8. Tres procesos escriben mensajes en el mismo fichero (log.txt).
# Se usa un Lock para que las escrituras no se mezclen.
from multiprocessing import Process, Lock, current_process
import time


def escribir_log(lock, mensajes):
    for i in range(mensajes):
        with lock:  # Solo un proceso escribe a la vez
            with open("log.txt", "a") as f:  # "a" añade al final del fichero
                f.write(f"{current_process().name} escribe el mensaje {i + 1}\n")
        time.sleep(0.1)  # Pequeña pausa para que los procesos se alternen


if __name__ == '__main__':
    open("log.txt", "w").close()  # Vacía el fichero antes de empezar

    lock = Lock()  # El mismo lock se pasa a los tres procesos
    procesos = []

    for _ in range(3):
        p = Process(target=escribir_log, args=(lock, 5))
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print("Contenido de log.txt:")
    with open("log.txt") as f:
        print(f.read())
