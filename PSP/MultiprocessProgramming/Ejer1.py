import os

def child():
    print('Child: %d, Parent: %d' % (os.getpid(), os.getppid()))
    os._exit(0)

def parent():
    
    numHijos = int(input("Cuántos hijos quieres?? "))
    hijosCreados = 0

    while hijosCreados < numHijos:

        newpid = os.fork()
        if newpid == 0:
            child()
        else:
            pids = (os.getpid(), newpid)
            print("Parent: %d, Child: %d\n" % pids)
            reply = input("Press 'y' to create a new process\n")
            if reply != 'y':
                break

    for i in range(hijosCreados):
        os.wait()

if __name__ == "__main__":
    parent()