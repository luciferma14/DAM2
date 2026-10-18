from multiprocessing import Process, Pipe


def emisor(conn):
    mensaje = "Hola, este es el mensaje enviado por el primer proceso"
    conn.send(mensaje)
    print(f"Emisor: ha enviado: {mensaje}")
    conn.close()


def receptor(conn):
    mensaje = conn.recv()
    print(f"Receptor: ha recibido: {mensaje}")
    conn.close()


if __name__ == '__main__':
    extremo_receptor, extremo_emisor = Pipe()

    p1 = Process(target=emisor, args=(extremo_emisor,))
    p2 = Process(target=receptor, args=(extremo_receptor,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Padre: los dos procesos han terminado.")