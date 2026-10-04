import subprocess


def main():
    while True:
        comando = input("Introduce un comando con sus parámetros (ej. ping www.google.com -c 5): ").split()
        if comando:
            try:
                subprocess.run(comando, check=True)
            except FileNotFoundError:
                print(f"Error: el comando '{comando[0]}' no existe.")
            except subprocess.CalledProcessError as e:
                print(f"Error: el comando ha fallado con código de retorno {e.returncode}.")
            except PermissionError:
                print(f"Error: no tienes permiso para ejecutar '{comando[0]}'.")
            except Exception as e:
                print(f"Error inesperado: {e}")
        else:
            print("No has introducido ningún comando.")

        respuesta = input("¿Quieres ejecutar otro comando? (y/n): ")
        if respuesta.lower() != 'y':
            break
    print("Programa finalizado.")


main()