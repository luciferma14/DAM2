import psutil

for proc in psutil.process_iter():
    try:
        nombre = proc.name()
        pid = proc.pid
        prioridad = proc.nice()
        print(f"Nombre: {nombre} :: PID: {pid} :: Prioridad: {prioridad}")
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        print("error")