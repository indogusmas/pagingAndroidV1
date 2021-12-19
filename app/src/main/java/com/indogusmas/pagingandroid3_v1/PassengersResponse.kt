package com.indogusmas.pagingandroid3_v1

data class PassengersResponse (
    val `data` : List<Passenger>,
    val totalPage: Int,
    val totalPassengers: Int
        )
