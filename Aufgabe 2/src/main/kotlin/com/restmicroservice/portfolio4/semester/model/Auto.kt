package com.restmicroservice.portfolio4.semester.model

/*
* Hier wird die Data Klasse, die nur die Representation Daten eines Autos dient, deklariert.
* */
data class Auto(
        var  id         :    Int?,
        var  hersteller :    String?,
        var  model      :    String?,
        var  reifen     :    Reifen?
)
