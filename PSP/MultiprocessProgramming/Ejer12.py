import random
import subprocess
import sys


def productor():
    numeros = [random.randint(1, 100) for _ in range(10)]
    
    print(f"Productor: números generados: {numeros}", file=sys.stderr, flush=True)
    print(" ".join(str(n) for n in numeros)) 


def consumidor():
    datos = sys.stdin.read() 
    numeros = [int(n) for n in datos.split()]
    print(f"Consumidor: suma total de los números recibidos: {sum(numeros)}")


def main():
    try:
        p1 = subprocess.Popen([sys.executable, __file__, "productor"], stdout=subprocess.PIPE, text=True)
        p2 = subprocess.Popen([sys.executable, __file__, "consumidor"], stdin=p1.stdout, text=True)
        p1.stdout.close() 
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