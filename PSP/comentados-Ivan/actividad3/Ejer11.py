# Ejercicio 11. Abrir varias aplicaciones a la vez con asyncio
import asyncio


async def open_application(application_name):
    try:
        # Lanza la aplicación sin bloquear; proc.pid es su PID
        proc = await asyncio.create_subprocess_exec(application_name)
        print(f"{application_name} abierta con PID {proc.pid}")
    except FileNotFoundError:
        print(f"Error: la aplicación '{application_name}' no existe")
    except Exception as e:
        print("Error inesperado:", e)


async def main():
    try:
        n = int(input("¿Cuántas aplicaciones quieres abrir? "))
    except ValueError:
        print("Debes introducir un número entero")
        return

    # Se piden los nombres de las aplicaciones y se guardan en una lista
    aplicaciones = []
    for i in range(n):
        aplicaciones.append(input(f"Nombre de la aplicación {i + 1} (notepad, gedit, cmd...): "))

    # gather ejecuta todas las corrutinas a la vez y espera a que terminen todas
    await asyncio.gather(*(open_application(app) for app in aplicaciones))

    # Evita que el programa termine (y cierre las apps hijas) inmediatamente
    input("Todas las aplicaciones abiertas. Pulsa Enter para cerrar el programa...")


asyncio.run(main())
