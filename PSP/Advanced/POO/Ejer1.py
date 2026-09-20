def main():
    
    class RomanNumeral:

        def __init__(self, numero):
            self.numero = numero

        def convertir(self):
            numeros = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]

            romanos = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]

            resultado = ""

            for i in range(len(numeros)):
                while self.numero >= numeros[i]:
                    resultado = resultado + romanos[i]
                    self.numero = self.numero - numeros[i]

            return resultado


    numero = int(input("Introduce un número: "))

    romano = RomanNumeral(numero)

    print(romano.convertir())

if __name__ == "__main__":
    main()