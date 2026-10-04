from multiprocessing import Process, Value


def hijo(n, resultado):
    total = 0
    for i in range(1, n + 1):
        total += i
    resultado.value = total  
    print(f"Hijo: la suma total es {total}")


if __name__ == '__main__':
    try:
        n = int(input("¿Hasta qué número (N) debe contar el hijo? "))
    except ValueError:
        print("Introduce un número entero válido.")
    else:
        resultado = Value('q', 0)
        p = Process(target=hijo, args=(n, resultado))
        p.start()
        p.join()
        print(f"Padre: la suma total obtenida por el hijo es {resultado.value}")