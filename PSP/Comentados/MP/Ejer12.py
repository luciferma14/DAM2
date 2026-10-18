# Ejercicio 12 - Comunicación entre dos procesos mediante una tubería (subprocess.Popen)
# Este mismo archivo se lanza tres veces:
#   python ej12_pipes_popen.py            -> programa principal (crea los dos procesos)
#   python ej12_pipes_popen.py productor  -> proceso 1: genera y envía los números
#   python ej12_pipes_popen.py consumidor -> proceso 2: recibe los números y los suma
import random
import subprocess
import sys


def productor():
    numeros = [random.randint(1, 100) for _ in range(10)]
    # Se imprime por stderr para que aparezca en consola (stdout es la tubería)
    print(f"Productor: números generados: {numeros}", file=sys.stderr, flush=True)
    print(" ".join(str(n) for n in numeros))  # enviado por la tubería


def consumidor():
    datos = sys.stdin.read()  # recibido por la tubería
    numeros = [int(n) for n in datos.split()]
    print(f"Consumidor: suma total de los números recibidos: {sum(numeros)}")


def main():
    try:
        # Primer proceso: su stdout es la tubería
        p1 = subprocess.Popen([sys.executable, __file__, "productor"], stdout=subprocess.PIPE, text=True)
        # Segundo proceso: su stdin lee de la tubería del primero
        p2 = subprocess.Popen([sys.executable, __file__, "consumidor"], stdin=p1.stdout, text=True)
        p1.stdout.close()  # el padre ya no necesita este extremo
        p2.wait()
        p1.wait()
        if p1.returncode != 0 or p2.returncode != 0:
            print("Error: alguno de los procesos ha terminado con errores.")
    except FileNotFoundError as e:
        print(f"Error: archivo no encontrado: {e}")
    except Exception as e:
        print(f"Error inesperado: {e}")


if __name__ == "__main__":
    try:
        if len(sys.argv) > 1 and sys.argv[1] == "productor":
            productor()
        elif len(sys.argv) > 1 and sys.argv[1] == "consumidor":
            consumidor()
        else:
            main()
    except Exception as e:
        print(f"Error: {e}", file=sys.stderr)
        sys.exit(1)