# Ejercicio 13. Enviar un texto a un proceso hijo por stdin y recibir el resultado
import subprocess
import sys

# Código del hijo: lee todo el texto por stdin y escribe cuántas palabras tiene
codigo_hijo = """
import sys
texto = sys.stdin.read()
print(len(texto.split()))
"""

texto = input("Introduce un texto: ")

try:
    # input=texto se envía al hijo por su stdin
    # capture_output=True recoge lo que el hijo imprime por stdout
    resultado = subprocess.run(
        [sys.executable, "-c", codigo_hijo],
        input=texto,
        capture_output=True,
        text=True,
        check=True
    )
    # strip() quita el salto de línea final
    print("Número de palabras:", resultado.stdout.strip())
except FileNotFoundError:
    print("Error: no se ha encontrado el intérprete de Python")
except subprocess.CalledProcessError as e:
    print("Error en el proceso hijo:", e.stderr)
except Exception as e:
    print("Error inesperado:", e)
