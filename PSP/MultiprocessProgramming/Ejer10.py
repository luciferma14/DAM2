import asyncio
import subprocess


def ejecutar_comando_sincrono(comando):
    try:
        resultado = subprocess.run(comando, capture_output=True, text=True, check=True)
        print(resultado.stdout)
    except FileNotFoundError:
        print(f"Error: el comando '{comando[0]}' no existe.")
    except subprocess.CalledProcessError as e:
        print(f"Error: el comando ha fallado con código de retorno {e.returncode}.")
        print(e.stderr)
    except Exception as e:
        print(f"Error inesperado: {e}")


async def ejecutar_comando_asincrono(comando):
    try:
        proceso = await asyncio.create_subprocess_exec(
            *comando,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.PIPE
        )
        stdout, stderr = await proceso.communicate()
        if proceso.returncode == 0:
            print(stdout.decode())
        else:
            print(f"Error: el comando ha fallado con código de retorno {proceso.returncode}.")
            print(stderr.decode())
    except FileNotFoundError:
        print(f"Error: el comando '{comando[0]}' no existe.")
    except Exception as e:
        print(f"Error inesperado: {e}")


def main():
    comando = input("Introduce un comando a ejecutar (ej. ping www.google.com -c 4 en Linux): ").split()
    if not comando:
        print("No has introducido ningún comando.")
        return

    print("\n--- Ejecución síncrona ---")
    ejecutar_comando_sincrono(comando)

    print("--- Ejecución asíncrona ---")
    asyncio.run(ejecutar_comando_asincrono(comando))


main()