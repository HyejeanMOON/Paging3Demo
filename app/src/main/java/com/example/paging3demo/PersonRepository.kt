package com.example.paging3demo

class PersonRepository {

    fun getPersonList(startIndex: Int, endIndex: Int): List<Person> {
        val list: MutableList<Person> = mutableListOf()

        for (i in startIndex..endIndex) {
            val person = Person(
                name = "name$i",
                age = i,
                photoUrl = "https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8YW5kcm9pZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"
            )
            list.add(person)
        }
        return list
    }
}