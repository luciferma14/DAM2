# Ejercicio 1. Lanzar un script y capturar su salida con subprocess.run
import subprocess
import sys

try:
    # sys.executable es el intérprete de Python que está ejecutando este programa
    # capture_output=True guarda la salida del hijo en lugar de mostrarla directamente
    # text=True devuelve la salida como texto (str) y no como bytes
    # check=True lanza CalledProcessError si el script termina con error
    resultado = subprocess.run(
        [sys.executable, "script.py"],
        capture_output=True,
        text=True,
        check=True
    )
    print("Salida del script:")
    print(resultado.stdout)  # stdout contiene lo que ha imprimido el script
except FileNotFoundError:
    # No existe el intérprete o el script
    print("Error: no se ha encontrado el intérprete o el script")
except subprocess.CalledProcessError as e:
    # El script ha terminado con un código de error; stderr guarda el mensaje
    print("Error al ejecutar el script:", e.stderr)
except Exception as e:
    print("Error inesperado:", e)
