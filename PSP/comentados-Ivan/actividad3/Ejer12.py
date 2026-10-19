# Ejercicio 12. Pipeline entre dos procesos (equivale a: generador | sumador)
import subprocess
import sys

# Código del primer proceso, que se ejecuta con python -c "..."
# Genera 10 números aleatorios, los muestra por stderr (para verlos en pantalla)
# y los envía por stdout (que irá al segundo proceso)
codigo_generador = """
import random, sys
numeros = [random.randint(1, 100) for _ in range(10)]
print("Números generados:", numeros, file=sys.stderr)
print(' '.join(str(n) for n in numeros))
"""

# Código del segundo proceso: lee los números por stdin y muestra su suma
codigo_sumador = """
import sys
numeros = [int(n) for n in sys.stdin.read().split()]
print("Suma total de los números recibidos:", sum(numeros))
"""

try:
    # stdout=PIPE: la salida de p1 se queda en una tubería en lugar de ir a la pantalla
    p1 = subprocess.Popen([sys.executable, "-c", codigo_generador], stdout=subprocess.PIPE, text=True)
    # stdin=p1.stdout: p2 lee lo que escribe p1, conectando los dos procesos
    p2 = subprocess.Popen([sys.executable, "-c", codigo_sumador], stdin=p1.stdout, text=True)
    p1.stdout.close()  # El padre cierra su copia de la tubería
    p2.wait()          # Espera a que terminen los dos procesos
    p1.wait()
except FileNotFoundError:
    print("Error: no se ha encontrado el intérprete de Python")
except Exception as e:
    print("Error inesperado:", e)
