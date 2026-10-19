from multiprocessing import Process, Queue 
import os

def hijo1(n1, q):
    total1 = 0
    for i in range(1, 6):
        total1 += i

    print(f"[Proceso 1] PID: {os.getpid()} | PID del padre: {os.getppid()} | Intervalo 1-5 | Suma parcial: {total1}")
    q.put(total1)

def hijo2(n2, q):
    total2 = 0
    for i in range(6, 11):
        total2 += i
    print(f"[Proceso 2] PID: {os.getpid()} | PID del padre: {os.getppid()} | Intervalo 6-10 | Suma parcial: {total2}")
    q.put(total2)

if __name__ == '__main__':
    try:
        n1 = 5
        n2 = 10
    except ValueError:
        print("Introduce un número entero válido.")
    else:
        print(f"Se crearán 2 procesos para sumar los números del 1 al 10.")
        q = Queue()
        p1 = Process(target=hijo1, args=(n1, q))
        p1.start()
        total1 = q.get()
        p1.join()
        print(f"[Coordinador] Resultado recibido para el intervalo 1-5: {total1}")

        p2 = Process(target=hijo2, args=(n2, q))
        p2.start()
        total2 = q.get()
        p2.join()
        print(f"[Coordinador] Resultado recibido para el intervalo 6-10: {total2}")

        sumaTotal = total1 + total2

        print(f"[Coordinador] Suma total: {sumaTotal}")
        print("[Coordinador] Los dos procesos han terminado")