# Ejercicio 4. El hijo suma de 1 a N y el padre recibe el resultado con una Queue
from multiprocessing import Process, Queue


def contar(n, q):
    suma = 0
    for i in range(1, n + 1):
        suma += i
    print(f"Hijo: la suma total es {suma}")
    q.put(suma)  # Envía el resultado al padre por la cola


if __name__ == '__main__':
    try:
        n = int(input("¿Hasta qué número (N) debe contar el hijo? "))
    except ValueError:
        print("Debes introducir un número entero")
    else:
        q = Queue()  # Cola para comunicar hijo y padre (no comparten memoria)
        p = Process(target=contar, args=(n, q))  # args es una tupla
        p.start()
        suma = q.get()  # El padre espera hasta que el hijo meta el dato
        p.join()
        print(f"Padre: la suma total obtenida por el hijo es {suma}")
