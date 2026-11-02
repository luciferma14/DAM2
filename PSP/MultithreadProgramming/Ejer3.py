
import time
import threading

cont = 0
lock = threading.Lock()

def incremental():
    global cont
    nombre = threading.current_thread().name
    for _ in range(5):
        with lock:
            temp = cont
            time.sleep(0.5)
            cont = temp + 1
            print(f"{nombre} incrementa el contador a {cont}")

if __name__ == "__main__":
    t1 = threading.Thread(target=incremental, name="Hilo 1")
    t2 = threading.Thread(target=incremental, name="Hilo 2")

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    print("Valor final del contador: ", cont)