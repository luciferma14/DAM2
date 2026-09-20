def main(): 

    lista = [8,34,23,45,54,6,14,21,52,48]
    num = 20
    cont = 0

    for edades in lista:
        if edades > num:
            cont = cont + 1

    print("Hay", cont, "personas que tienen más de", num, "años")

if __name__ == "__main__":
    main()