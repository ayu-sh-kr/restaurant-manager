package org.example.customer.domain

import java.util.UUID

class Customer {
    val id: CustomerId = CustomerId(UUID.randomUUID())
    val name: String
    val email: Email
    val phone: Phone

    constructor(name: String, email: String, code: String?, phone: String) {
        this.name = name
        this.email = Email(email)
        this.phone = if (code != null) Phone(code = code, phone = phone) else Phone(phone = phone)
    }


    override fun toString(): String {
        return "Customer(name='$name', id=${id}, email: ${email} phone: ${phone})"
    }

}

class Email {
    val email: String
    constructor(email: String) {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        check(email.matches(regex))
        this.email = email
    }

    override fun toString(): String {
        return "Email(email=$email)"
    }
}

class Phone {
    val code: String
    val phone: String

    constructor(code: String = "91", phone: String) {
        val phoneRgx = "^[0-9]{10}".toRegex()
        check(phone.matches(phoneRgx))
        this.phone = phone

        val codeRgx = "[0-9]{2}".toRegex()
        check(code.matches(codeRgx), lazyMessage = ({"Phone Code does not pass the check for value: $code"}) )
        this.code = code
    }

    override fun toString(): String {
        return "Phone(code=$code, phone=$phone)"
    }
}

data class CustomerId(val id: UUID)



