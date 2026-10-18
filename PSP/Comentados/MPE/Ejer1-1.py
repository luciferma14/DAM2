# Ejercicio 1 - Pipeline en Linux: listar los procesos de Python ordenados por PID
# Solo funciona en Linux/macOS (ps, grep y sort son comandos de ese sistema).
#
# Pipeline escrito directamente en la terminal de Linux:
#     ps -eo pid,comm | grep python | sort -n
#
# Aquí lo construimos desde Python conectando tres procesos con tuberías (subprocess.Popen),
# igual que en el ejercicio 12 de la actividad anterior.
import subprocess


def main():
    try:
        # Proceso 1: lista todos los procesos (PID y nombre). Su stdout es una tubería
        p1 = subprocess.Popen(["ps", "-eo", "pid,comm"], stdout=subprocess.PIPE, text=True)
        # Proceso 2: filtra las líneas que contienen "python". Su stdin viene de la tubería del proceso 1
        p2 = subprocess.Popen(["grep", "python"], stdin=p1.stdout, stdout=subprocess.PIPE, text=True)
        p1.stdout.close()  # el padre ya no necesita este extremo
        # Proceso 3: ordena numéricamente (por PID). Su stdin viene de la tubería del proceso 2
        p3 = subprocess.Popen(["sort", "-n"], stdin=p2.stdout, stdout=subprocess.PIPE, text=True)
        p2.stdout.close()

        salida, _ = p3.communicate()
        print("PID COMANDO")
        print(salida)
    except FileNotFoundError as e:
        print(f"Error: comando no encontrado ({e}). Este ejercicio necesita Linux/macOS.")
    except Exception as e:
        print(f"Error inesperado: {e}")


main()