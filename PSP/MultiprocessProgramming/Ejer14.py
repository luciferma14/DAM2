import os
import subprocess
import sys


def main():
    ruta_script = os.path.join(os.path.dirname(os.path.abspath(__file__)), "script.py")
    try:
        resultado = subprocess.run(
            [sys.executable, ruta_script],
            capture_output=True,
            text=True,
            check=True
        )
        print("Salida de script.py:")
        print(resultado.stdout)
    except FileNotFoundError:
        print("Error: no se ha encontrado python o script.py.")
    except subprocess.CalledProcessError as e:
        print(f"Error: el script ha fallado con código de retorno {e.returncode}.")
        print(e.stderr)
    except Exception as e:
        print(f"Error inesperado: {e}")


main()