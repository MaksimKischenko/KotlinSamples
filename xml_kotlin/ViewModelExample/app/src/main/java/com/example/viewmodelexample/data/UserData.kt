package com.example.viewmodelexample.data

import com.example.viewmodelexample.models.User

object UserData {
    fun getUsers() = listOf(
        User("Maksim", "The head of mobile dev"),
        User("John", "Software Engineer"),
        User("Emily", "Graphic Designer"),
        User("David", "Marketing Specialist"),
        User("Sophia", "Product Manager"),
        User("Michael", "Data Analyst"),
        User("Emma", "Frontend Developer"),
        User("Daniel", "Backend Developer"),
        User("Olivia", "UX/UI Designer")
    )

    fun getAnotherUser() = listOf(
        User("Alexander", "QA Engineer"),
        User("Ava", "Project Manager"),
        User("Jacob", "Mobile App Developer"),
        User("Mia", "Data Scientist"),
        User("Ethan", "Full Stack Developer"),
        User("Isabella", "System Administrator"),
        User("William", "Network Engineer"),
        User("Sofia", "Business Analyst"),
        User("Benjamin", "Technical Writer"),
        User("Charlotte", "Cybersecurity Specialist"),
        User("James", "Artificial Intelligence Researcher")
    )
}