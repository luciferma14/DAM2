def main(): 
    par = int(input("Dime un número par: "))
    impar = int(input("Dime un número impar: "))

    if par % 2 != 0:
        print("No es correcto")
    elif impar % 2 == 0:
        print("No es correcto")
    else:
        print("Los dos son correctos")

    
if __name__ == "__main__":
    main()