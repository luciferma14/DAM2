# Ejercicio 7 - Dividir la lista de 1 a 1.000.000 en 4 partes y sumarlas con 4 procesos
from multiprocessing import Process, Queue


def suma_parcial(parte, q):
    suma = sum(parte)
    print(f"Proceso: suma parcial = {suma}")
    q.put(suma)  # devuelve la suma parcial al proceso principal


if __name__ == '__main__':
    numeros = list(range(1, 1000001))  # lista de 1 a 1.000.000
    num_partes = 4
    tam = len(numeros) // num_partes
    q = Queue()
    procesos = []

    for i in range(num_partes):
        parte = numeros[i * tam:(i + 1) * tam]  # cada proceso recibe su cuarto de la lista
        p = Process(target=suma_parcial, args=(parte, q))
        procesos.append(p)
        p.start()

    # El proceso principal suma los resultados parciales
    total = 0
    for _ in procesos:
        total += q.get()

    for p in procesos:
        p.join()

    print(f"Suma total: {total}")
    print(f"Comprobación (n*(n+1)/2): {1000000 * 1000001 // 2}")