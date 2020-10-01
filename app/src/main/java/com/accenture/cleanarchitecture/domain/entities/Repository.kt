package com.accenture.cleanarchitecture.domain.entities

class Repository(val name: String = "",
                 val fullName: String = "",
                 val description: String = "",
                 val numberForks: Int = 0,
                 val numberStarts: Int = 0,
                 val author: Author
)