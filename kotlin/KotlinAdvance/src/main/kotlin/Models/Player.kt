package Models

class Player(
    initialName: String,
    val hometown: String = "Neversummer",
    var healthPoints: Int,
    val isImmortal: Boolean
) {
    var name = "madrigal"
        get() = field.replaceFirstChar { it.uppercase() }
        private set(value) {
            field = value.trim()
        }
    var name2 = "madrigal"
        private set

    val title: String
        get() = when {
            name.all { it.isDigit() } -> "The Identifiable"
            name.none { it.isLetter() } -> "The Witness Protection Member"
            name.count { it.lowercase() in "aeiou" } > 4 -> "The Master of Vowels"
            else -> "The Renowned Hero"
        }

    val prophecy by lazy {
        println("$name embarks on an arduous quest to locate a fortune teller")
        Thread.sleep(3000)
        println("The fortune teller bestows a prophecy upon $name")
        "An intrepid hero from $hometown shall some day " + listOf(
            "form an unlikely bond between two warring factions",
            "take possession of an otherworldly blade",
            "bring the gift of creation back to the world",
            "best the world-eater"
        ).random()
    }
    fun changeName(newName: String) {
        println("$name legally changes their name to $newName")
        name = newName
    }
    fun prophesize() {
        println("$name thinks about their future")
        println("A fortune teller told Madrigal, \"$prophecy\"")
    }
}