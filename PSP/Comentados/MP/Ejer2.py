# Ejercicio 2 - Proceso hijo que cuenta de 1 a 5 (fork, solo Unix/macOS)
import os
import time


def hijo():
    for i in range(1, 6):
        print(i)
        time.sleep(1)
    os._exit(0)


def padre():
    nuevo_pid = os.fork()
    if nuevo_pid == 0:
        hijo()
    else:
        os.wait()  # espera a que el hijo complete su tarea
        print("The child process has finished.")


padre()