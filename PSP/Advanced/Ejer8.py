def main(): 

    lista = ["Lucia", "Ivan", "Alvaro", "Anabel", "Salva"]
    letra = input("Dime una letra: ").lower()
    cont = 0

    for palabra in lista:
        palabra_lower = palabra.lower()
        if palabra_lower.startswith(letra):
            cont = cont + 1

    print("Hay", cont, "nombres que empiezan por" , letra)

if __name__ == "__main__":
    main()