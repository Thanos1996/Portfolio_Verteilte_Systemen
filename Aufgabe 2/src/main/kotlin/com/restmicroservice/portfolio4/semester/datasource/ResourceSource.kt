package com.restmicroservice.portfolio4.semester.datasource

/*
*  Das Interface deklariert eine Methode, die einzelne Entitäten zurückliefert. Die Methode ist Abstrakt
*  und somit können unterschiedliche Sources retrieved werden. Das dient eine im Weiteren einfachere
*  Implementierung.
* */
interface ResourceSource {

    fun retrieveSource(source :Any ) : Any
}