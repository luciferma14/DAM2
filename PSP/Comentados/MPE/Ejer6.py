# Ejercicio 6 - Factorial de un número grande con varios procesos en paralelo
# Se divide 1..N en rangos; cada proceso calcula el producto parcial de su rango
# y el proceso principal combina los resultados.
from multiprocessing import Process, Queue


def producto_parcial(inicio, fin, q):
    # Calcula inicio * (inicio+1) * ... * fin
    producto = 1
    for i in range(inicio, fin + 1):
        producto *= i
    print(f"Proceso del rango {inicio}-{fin}: producto parcial = {producto}")
    q.put(producto)


if __name__ == '__main__':
    try:
        n = int(input("Introduce el número del que calcular el factorial (ej. 20): "))
        num_procesos = int(input("¿Cuántos procesos quieres usar? (ej. 4): "))
    except ValueError:
        print("Introduce números enteros válidos.")
    else:
        if n < 1 or num_procesos < 1:
            print("Los números deben ser mayores que 0.")
        else:
            num_procesos = min(num_procesos, n)
            # Dividimos 1..n en rangos de tamaño similar
            tam = n // num_procesos
            q = Queue()
            procesos = []
            for i in range(num_procesos):
                inicio = i * tam + 1
                fin = n if i == num_procesos - 1 else (i + 1) * tam  # el último llega hasta n
                p = Process(target=producto_parcial, args=(inicio, fin, q))
                procesos.append(p)
                p.start()

            # El proceso principal combina los resultados parciales
            factorial = 1
            for _ in procesos:
                factorial *= q.get()

            for p in procesos:
                p.join()

            print(f"{n}! = {factorial}")