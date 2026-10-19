# Ejercicio 5. Ejecutar comandos del sistema en bucle con subprocess.run
import subprocess

while True:
    comando = input("Introduce un comando con sus parámetros (ej: ping www.google.com -c 5): ")
    try:
        # split() convierte el texto en una lista: "ping x -c 5" -> ["ping", "x", "-c", "5"]
        # check=True lanza CalledProcessError si el comando falla
        subprocess.run(comando.split(), check=True)
    except FileNotFoundError:
        print("Error: el comando no existe")
    except subprocess.CalledProcessError as e:
        # returncode es el código de salida del comando
        print("Error: el comando terminó con código", e.returncode)
    except Exception as e:
        print("Error inesperado:", e)

    # Pregunta si se quiere repetir; cualquier cosa distinta de 's' termina el bucle
    otra = input("¿Quieres ejecutar otro comando? (s/n): ")
    if otra != 's':
        break
