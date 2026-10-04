import os, time

def child():
    for n in range(1,6):
        print(n)
        time.sleep(1)
    os._exit(0)

def parent():

    newpid = os.fork()
    if newpid == 0:
        child()
    else:
        os.wait()
        print("El proceso hijo ha finalizado")

if __name__ == "__main__":
    parent()
