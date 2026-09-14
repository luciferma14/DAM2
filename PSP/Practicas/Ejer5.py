def main(): 
    num1 = int(input("Dime un número: "))
    num2 = int(input("Dime otro número: "))

    if num1 % 2 == 0:
        print(num1, "es par")
    else:
        print(num1, "es impar")

    if num2 % 2 == 0:
        print(num2, "es par")
    else:
        print(num2, "es impar")
    
if __name__ == "__main__":
    main()