class Person:

    def __init__(self, name="", age=0, id=""):
        self.name = name
        self.age = age
        self.id = id

    def get_name(self):
        return self.name

    def set_name(self, name):
        if name != "":
            self.name = name

    def get_age(self):
        return self.age

    def set_age(self, age):
        if age >= 0:
            self.age = age

    def get_id(self):
        return self.id

    def set_id(self, id):
        if id != "":
            self.id = id

    def show(self):
        print("Nombre:", self.name)
        print("Edad:", self.age)
        print("ID:", self.id)

    def isAdult(self):
        if self.age >= 18:
            return True
        else:
            return False