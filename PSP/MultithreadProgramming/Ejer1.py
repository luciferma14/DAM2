import threading
import time


def funcion(frase, times):
    for _ in range(times):
        print(frase)
        time.sleep(1)

if __name__ == "__main__":
    t1 = threading.Thread(target=funcion, args=("Hilo 1", 3))
    t2 = threading.Thread(target=funcion, args=("Hilo 2", 5))
    t3 = threading.Thread(target=funcion, args=("Hilo 3", 7))

    start_time = time.time()
    
    t1.start()
    t2.start()
    t3.start()

    print("Hilos activos: ", threading.active_count())

    t1.join()
    t2.join()
    t3.join()

    tiempo_start = time.time() - start_time

    print("Programa finalizado con start, tiempo total: ", tiempo_start)

    t4 = threading.Thread(target=funcion, args=("Hilo 1", 3))
    t5 = threading.Thread(target=funcion, args=("Hilo 2", 5))
    t6 = threading.Thread(target=funcion, args=("Hilo 3", 7))

    start_time = time.time()

    t4.run()
    t5.run()
    t6.run()

    tiempo_run = time.time() - start_time

    print("Hilos activos: ", threading.active_count())

    print("Programa finalizado con start, tiempo total: ", tiempo_run)

    print("Diferencia entre los dos tiempos: ", tiempo_run - tiempo_start)