# Ejercicio 4 - Dos procesos se comunican mediante un Pipe: el primero envía un mensaje, el segundo lo recibe
from multiprocessing import Process, Pipe


def emisor(conn):
    mensaje = "Hola, este es el mensaje enviado por el primer proceso"
    conn.send(mensaje)
    print(f"Emisor: ha enviado: {mensaje}")
    conn.close()  # es mejor cerrar la conexión al terminar


def receptor(conn):
    mensaje = conn.recv()  # espera hasta recibir el mensaje
    print(f"Receptor: ha recibido: {mensaje}")
    conn.close()


if __name__ == '__main__':
    # Pipe devuelve los dos extremos de la tubería
    extremo_receptor, extremo_emisor = Pipe()

    p1 = Process(target=emisor, args=(extremo_emisor,))
    p2 = Process(target=receptor, args=(extremo_receptor,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Padre: los dos procesos han terminado.")