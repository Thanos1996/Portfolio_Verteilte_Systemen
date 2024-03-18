package com.restmicroservice.portfolio4.semester.service


/*
*  Hier werden alle übliche Actions POST,PATCH,DELETE deklariert. Auch hier sind die Abstrakt deklariert um sie
* je nach Anwendungsfall implementieren zu können.
* */
interface ActionsToSource {

    fun put (data : Any) : Boolean

    fun patch(data : Any) : Boolean

    fun delete (data : Any) : Boolean
}