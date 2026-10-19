# Ejercicio 5. Dos procesos modifican un contador compartido usando Value.
# Se usa un Lock para evitar condiciones de carrera.
from multiprocessing import Process, Value, Lock


def incrementar(contador, lock, veces):
    for _ in range(veces):
        # Sección crítica: solo un proceso a la vez puede modificar el contador
        # Sin el lock, dos procesos podrían leer el mismo valor y perder incrementos
        with lock:
            contador.value += 1  # .value accede al valor del entero compartido


if __name__ == '__main__':
    contador = Value('i', 0)  # Entero ('i') compartido entre procesos, empieza en 0
    lock = Lock()

    p1 = Process(target=incrementar, args=(contador, lock, 10000))
    p2 = Process(target=incrementar, args=(contador, lock, 10000))
    p1.start()
    p2.start()
    p1.join()
    p2.join()

    print("Valor final del contador:", contador.value)  # Debe ser 20000
