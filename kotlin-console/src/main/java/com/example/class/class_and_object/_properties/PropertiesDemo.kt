package com.example.`class`.class_and_object._properties

class PropertiesDemo {
}

class Rectangle(
    private val width: Int,
    private val height: Int
) {

    val square get() = this.width * this.height

    var message: String = ""
        get() = "\nGetter: This class is refer from $this"
        set(value) {
            println("Setter: Filed is assigned by setter")
            field = value
        }
}

fun main() {
    val rectangle = Rectangle(3, 4)

    // Getter
    println(rectangle.message)
    // Setter
    rectangle.message = ""
}
