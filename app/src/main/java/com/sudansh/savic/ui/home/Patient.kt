package com.sudansh.savic.ui.home

data class Patient(
    val fullname: String,
    val email: String,
    val gender: String,
    val age: Int
) {
    override fun toString(): String {
        return "Patient{fullName='$fullname', gender='$gender', age='$age'"
    }
}