package com.restmicroservice.portfolio4.semester.model

/*
* Hier wird die Data Klasse, die nur die Representation der Daten eines Autos dient, deklariert.
* */
data class Reifen(
        var id          :    Int?,
        var hersteller  :    String?,
        var radius      :    Radius?
)
