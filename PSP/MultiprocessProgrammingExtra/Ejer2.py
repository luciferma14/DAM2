from multiprocessing import Process, current_process
import time


def hijo(timeout):
    nombre = current_process().name
    print(f"{nombre}: empiezo, voy a esperar {timeout} segundos.")
    time.sleep(timeout)
    print(f"{nombre}: he terminado tras {timeout} segundos.")


if __name__ == '__main__':
    timeouts = [3, 1, 2]
    procesos = []

    for i, t in enumerate(timeouts):
        p = Process(target=hijo, args=(t,), name=f"Hijo-{i + 1}")
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print("Padre: los tres procesos hijo han terminado.")