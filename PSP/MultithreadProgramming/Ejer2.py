import threading
import time
import queue

cola = queue.Queue()

def productor():
    for i in range(1,6):
        print("Productor genera el número ", i)
        cola.put(i)
        time.sleep(1)

def consumidor():
    for _ in range(5):
        numero = cola.get()
        print(f"Consumidor procesa el numero {numero}. Resultado: {numero * 2}")
     

if __name__ == "__main__":
    tp = threading.Thread(target=productor)
    tc = threading.Thread(target=consumidor)

    tp.start()
    tc.start()

    tp.join()
    tc.join()

    print("Programa finalizado")