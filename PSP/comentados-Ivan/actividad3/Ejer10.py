# Ejercicio 10. Ejecutar un comando de forma síncrona y de forma asíncrona
import subprocess
import asyncio


def execute_synchronous_command(command):
    # Versión síncrona: run() bloquea el programa hasta que el comando termina
    try:
        resultado = subprocess.run(command, capture_output=True, text=True, check=True)
        print(resultado.stdout)
    except FileNotFoundError:
        print("Error: el comando no existe")
    except subprocess.CalledProcessError as e:
        print("Error: el comando terminó con código", e.returncode)
    except Exception as e:
        print("Error inesperado:", e)


# async def define una función asíncrona (corrutina)
async def execute_asynchronous_command(command):
    try:
        # create_subprocess_exec lanza el proceso sin bloquear; *command desempaqueta la lista
        # PIPE permite capturar la salida estándar y la de error
        proc = await asyncio.create_subprocess_exec(
            *command,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.PIPE
        )
        # communicate() espera a que termine y devuelve (stdout, stderr) en bytes
        stdout, stderr = await proc.communicate()
        print(stdout.decode())  # decode() pasa de bytes a texto
        if proc.returncode != 0:
            print("Error: el comando terminó con código", proc.returncode)
    except FileNotFoundError:
        print("Error: el comando no existe")
    except Exception as e:
        print("Error inesperado:", e)


async def main():
    # split() convierte el texto en lista de argumentos
    comando = input("Introduce el comando (ej: ping www.google.com -c 4): ").split()

    print("--- Ejecución síncrona ---")
    execute_synchronous_command(comando)

    print("--- Ejecución asíncrona ---")
    await execute_asynchronous_command(comando)  # await espera a que la corrutina acabe


# asyncio.run arranca la ejecución de la función asíncrona principal
asyncio.run(main())
