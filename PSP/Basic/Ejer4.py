def main(): 
    cadena = input("Dime una frase: ")
    cont = 0

    for letra in cadena:
        if letra.isupper():
            cont = cont + 1

    print("La frase tiene", cont, "mayúsculas")

if __name__ == "__main__":
    main()