def main():
    
    class BuscarPareja:

        def __init__(self, numeros, objetivo):
            self.numeros = numeros
            self.objetivo = objetivo

        def buscar(self):
            for i in range(len(self.numeros)):
                for j in range(i + 1, len(self.numeros)):
                    if self.numeros[i] + self.numeros[j] == self.objetivo:
                        return i, j


    numeros = [10, 20, 10, 40, 50, 60, 70]
    objetivo = 50

    pareja = BuscarPareja(numeros, objetivo)

    print(pareja.buscar())

if __name__ == "__main__":
    main()