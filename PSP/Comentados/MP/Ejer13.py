# Ejercicio 13 - Proceso hijo que cuenta las palabras de un texto
# Este mismo archivo se lanza dos veces:
#   python ej13_contar_palabras.py        -> proceso padre
#   python ej13_contar_palabras.py hijo   -> proceso hijo (lee el texto de stdin)
import subprocess
import sys


def hijo():
    texto = sys.stdin.read()         # el texto que le pasa el padre como entrada
    print(len(texto.split()))        # el recuento se devuelve por stdout


def padre():
    texto = input("Introduce un texto: ")
    try:
        resultado = subprocess.run(
            [sys.executable, __file__, "hijo"],
            input=texto,
            capture_output=True,
            text=True,
            check=True
        )
        palabras = int(resultado.stdout.strip())
        print(f"El texto tiene {palabras} palabra(s).")
    except subprocess.CalledProcessError as e:
        print(f"Error: el proceso hijo ha fallado con código de retorno {e.returncode}.")
        print(e.stderr)
    except FileNotFoundError as e:
        print(f"Error: archivo no encontrado: {e}")
    except ValueError:
        print("Error: el proceso hijo ha devuelto un valor no válido.")
    except Exception as e:
        print(f"Error inesperado: {e}")


if __name__ == "__main__":
    if len(sys.argv) > 1 and sys.argv[1] == "hijo":
        hijo()
    else:
        padre()