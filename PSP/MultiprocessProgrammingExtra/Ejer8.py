from multiprocessing import Process, Lock, current_process
import time


def escribir_log(lock, mensajes):
    nombre = current_process().name
    for i in range(mensajes):
        with lock:
            with open("log.txt", "a", encoding="utf-8") as f:
                f.write(f"{nombre}: mensaje {i + 1}\n")
        time.sleep(0.1)


if __name__ == '__main__':
    open("log.txt", "w").close() 
    lock = Lock()
    procesos = []

    for i in range(3):
        p = Process(target=escribir_log, args=(lock, 5), name=f"Proceso-{i + 1}")
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print("Contenido de log.txt:")
    with open("log.txt", "r", encoding="utf-8") as f:
        print(f.read())