import psutil


def listar_procesos():
    for proc in psutil.process_iter():
        try:
            print(f"{proc.name()} :: {proc.pid}")
        except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
            pass


def main():
    listar_procesos()
    objetivo = input("\nIntroduce el nombre del proceso que quieres terminar: ")

    encontrado = False
    for proc in psutil.process_iter():
        try:
            nombre = proc.name()
            if nombre == objetivo:
                encontrado = True
                pid = proc.pid
                print("Matando proceso: ", nombre, ' ::: ', pid)
                proc.kill()
                print(f"El proceso {nombre} ({pid}) ha sido terminado.")
        except psutil.NoSuchProcess:
            print("Error: el proceso ya no existe.")
        except psutil.AccessDenied:
            print("Error: no tienes permiso para matar este proceso.")
        except psutil.ZombieProcess:
            print("Error: el proceso es un zombie.")

    if not encontrado:
        print(f"Error: no se ha encontrado ningún proceso llamado '{objetivo}'.")


main()