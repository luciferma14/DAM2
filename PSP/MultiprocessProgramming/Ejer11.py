import asyncio


async def abrir_aplicacion(nombre_aplicacion):
    try:
        proceso = await asyncio.create_subprocess_exec(nombre_aplicacion)
        print(f"Aplicación '{nombre_aplicacion}' abierta con PID: {proceso.pid}")
        return proceso
    except FileNotFoundError:
        print(f"Error: la aplicación '{nombre_aplicacion}' no existe.")
    except Exception as e:
        print(f"Error al abrir '{nombre_aplicacion}': {e}")
    return None


async def main():
    try:
        total = int(input("¿Cuántas aplicaciones quieres abrir? "))
    except ValueError:
        print("Introduce un número entero válido.")
        return

    nombres = []
    for i in range(total):
        nombres.append(input(f"Nombre de la aplicación {i + 1} (ej. notepad, gedit, cmd): "))

    await asyncio.gather(*(abrir_aplicacion(nombre) for nombre in nombres))

    input("\nTodas las aplicaciones han sido abiertas. Pulsa Enter para cerrar el programa...")


asyncio.run(main())