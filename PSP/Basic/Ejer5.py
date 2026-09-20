def main(): 
    binario = input("Dime un número en binario: ")

    entero = int(binario, 2)

    print("El número binario", binario, "en base decimal es:", entero)

if __name__ == "__main__":
    main()