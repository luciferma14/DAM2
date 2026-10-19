# Ejercicio 6. Factorial de un número grande con varios procesos en paralelo.
# Se divide el cálculo en rangos de multiplicación; cada proceso calcula el
# producto parcial de su rango y el proceso principal combina los resultados.
from multiprocessing import Process, Queue


def producto_parcial(inicio, fin, q):
    resultado = 1
    for n in range(inicio, fin + 1):  # Multiplica todos los números del rango
        resultado *= n
    print(f"Rango {inicio}-{fin}: producto parcial = {resultado}")
    q.put(resultado)  # Devuelve el producto parcial al padre por la cola


if __name__ == '__main__':
    N = 20
    num_procesos = 4
    tam = N // num_procesos  # Tamaño de cada rango (5)

    q = Queue()
    procesos = []

    for i in range(num_procesos):
        inicio = i * tam + 1
        # El último proceso llega hasta N por si la división no es exacta
        fin = (i + 1) * tam if i < num_procesos - 1 else N
        p = Process(target=producto_parcial, args=(inicio, fin, q))
        procesos.append(p)
        p.start()

    # El proceso principal recoge los productos parciales y los combina
    # Hay que hacer un get() por cada proceso lanzado
    factorial = 1
    for _ in range(num_procesos):
        factorial *= q.get()

    for p in procesos:
        p.join()

    print(f"{N}! = {factorial}")
