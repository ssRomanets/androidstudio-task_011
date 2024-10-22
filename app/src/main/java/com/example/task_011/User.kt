package com.example.task_011

data class User(val name: String, val age: Int){
    override fun toString() = "name - $name age - $age"
}