package com.restmicroservice.portfolio4.semester.datasource


/*
*  Das Interface deklariert eine Methode, die Collection (Liste, Objekt) zurückliefert. Die Methode ist Abstrakt
*  und somit können unterschiedliche Collections retrieved werden. So können wir das Interface
*  einfacher je nach Anwendungsfall implementieren.
* */

interface CollectionSource {

    fun retrieveCollection() : Collection<Any>
}