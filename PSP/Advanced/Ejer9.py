def main(): 

    palabra = input("Dime una palabra: ").lower()

    print("La letra 'a' aparece:", palabra.count('a'), "veces.")
    print("La letra 'e' appears:", palabra.count('e'), "veces.")
    print("La letra 'i' aparece:", palabra.count('i'), "veces.")
    print("La letra 'o' aparece:", palabra.count('o'), "veces.")
    print("La letra 'u' aparece:", palabra.count('u'), "veces.")

if __name__ == "__main__":
    main()