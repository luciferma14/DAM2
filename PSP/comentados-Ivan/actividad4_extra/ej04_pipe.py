# Ejercicio 4. Dos procesos se comunican mediante un Pipe.
# El primero envía un mensaje y el segundo lo recibe.
from multiprocessing import Process, Pipe


def emisor(conn):
    mensaje = "Hola, este es el mensaje enviado por el primer proceso"
    conn.send(mensaje)  # Envía el mensaje por su extremo del pipe
    print(f"Emisor (proceso 1) ha enviado: {mensaje}")
    conn.close()  # Mejor cerrar la conexión al terminar


def receptor(conn):
    mensaje = conn.recv()  # Espera hasta que llegue el mensaje
    print(f"Receptor (proceso 2) ha recibido: {mensaje}")
    conn.close()


if __name__ == '__main__':
    # Pipe() devuelve los dos extremos de la tubería
    # Un pipe solo conecta dos procesos y no guarda datos: es comunicación directa
    extremo_recibir, extremo_enviar = Pipe()

    p1 = Process(target=emisor, args=(extremo_enviar,))    # Cada proceso recibe un extremo
    p2 = Process(target=receptor, args=(extremo_recibir,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Comunicación terminada.")
