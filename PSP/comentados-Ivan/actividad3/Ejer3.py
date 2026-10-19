# Ejercicio 3. Crear N procesos hijos con multiprocessing.Process
from multiprocessing import Process
import os


def hijo():
    # Cada hijo imprime su PID y el PID de su padre
    print("Child: %d, Parent: %d" % (os.getpid(), os.getppid()))


def padre():
    try:
        total = int(input("¿Cuántos procesos hijos quieres crear? "))
    except ValueError:
        # Si no escribe un número entero, se sale
        print("Debes introducir un número entero")
        return

    procesos = []  # Lista para guardar los procesos y hacer join después
    for i in range(total):
        p = Process(target=hijo)  # Crea el proceso (todavía no se ejecuta)
        p.start()                 # Lo arranca
        procesos.append(p)
        # Entre un hijo y el siguiente pregunta si se quiere crear otro
        if i < total - 1:
            reply = input("Pulsa 'y' para crear otro proceso (otra tecla para parar): ")
            if reply != 'y':
                break

    # Primero se arrancan todos y después se espera a todos, así corren en paralelo
    for p in procesos:
        p.join()
    print("Todos los procesos hijos han terminado")


# Imprescindible en Windows para que los hijos no relancen el programa
if __name__ == '__main__':
    padre()
