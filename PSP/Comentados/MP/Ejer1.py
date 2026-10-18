# Ejercicio 1 - Crear N procesos hijo con fork (solo Unix/macOS)
import os


def hijo():
    print(f"Child: {os.getpid()}, Parent: {os.getppid()}")
    os._exit(0)  # el hijo debe terminar aquí, no debe seguir con el bucle


def padre():
    try:
        total = int(input("¿Cuántos procesos hijo quieres crear? "))
    except ValueError:
        print("Introduce un número entero válido.")
        return

    creados = 0
    while creados < total:
        nuevo_pid = os.fork()
        if nuevo_pid == 0:
            hijo()
        creados += 1
        # Opción para parar la creación antes de llegar al número pedido
        if creados < total:
            respuesta = input("Pulsa 'y' para crear otro proceso (cualquier otra tecla para parar): ")
            if respuesta.lower() != 'y':
                break

    # El padre espera a que terminen todos los hijos
    for _ in range(creados):
        os.wait()
    print(f"Padre: los {creados} proceso(s) hijo han terminado.")


padre()