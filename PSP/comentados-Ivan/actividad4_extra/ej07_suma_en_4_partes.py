# Ejercicio 7. Se divide la lista de 1 a 1.000.000 en 4 partes.
# Cada proceso calcula la suma de su parte y la devuelve por una Queue.
from multiprocessing import Process, Queue


def suma_parcial(parte, q):
    s = sum(parte)  # Suma de los elementos de su parte
    print(f"Suma parcial de {parte[0]} a {parte[-1]}: {s}")
    q.put(s)  # Devuelve el resultado al padre


if __name__ == '__main__':
    lista = list(range(1, 1000001))
    num_procesos = 4
    tam = len(lista) // num_procesos  # 250000 elementos por parte

    q = Queue()
    procesos = []

    for i in range(num_procesos):
        parte = lista[i * tam:(i + 1) * tam]  # Slicing: trozo i de la lista
        p = Process(target=suma_parcial, args=(parte, q))
        procesos.append(p)
        p.start()

    # El proceso principal suma los resultados parciales
    total = 0
    for _ in range(num_procesos):
        total += q.get()  # Un get() por cada proceso

    for p in procesos:
        p.join()

    print("Suma total:", total)
