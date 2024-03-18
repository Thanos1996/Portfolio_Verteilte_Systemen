package com.restmicroservice.portfolio4.semester.datasource

import com.restmicroservice.portfolio4.semester.model.Auto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import kotlin.random.Random

@Repository
class AutoDatenBank(

        // Hier initialisieren wir ein Objekt von Typ ReifenDatenbank, damit wir die Methode getReifenByID
        // verwenden können. Damit verwenden wir in der Zukunft auch Datenredudanzen, wenn ein Refentyp
        //  gelöscht wird. Es wird auch im Auto Angaben nicht angezeigt.
        private val reifenDatenBank: ReifenDatenBank,
        // Constructor to initialize des Auto-Collection
        internal var autos: MutableList<Auto> = mutableListOf(
                Auto(Random.nextInt(1, 150), "Porsche", "Taycan", reifenDatenBank.getReifenById(7)),
                Auto(Random.nextInt(1, 150), "Porsche", "Hurracan", reifenDatenBank.getReifenById(5)),
                Auto(Random.nextInt(1, 150), "Porsche", "911", reifenDatenBank.getReifenById(3)),
                Auto(Random.nextInt(1, 150), "BMW", "M4", reifenDatenBank.getReifenById(2)),
                Auto(Random.nextInt(1, 150), "Toyota", "Corolla", reifenDatenBank.getReifenById(6)),
                Auto(Random.nextInt(1, 150), "Hyundai", "i30", reifenDatenBank.getReifenById(4)),
                Auto(Random.nextInt(1, 150), "Kia", "Picanto", reifenDatenBank.getReifenById(1))
        )
) : CollectionSource {

    fun deleteAuto(id: Int): Boolean {
        for (auto in autos)
            if (auto.id == id) {
                autos.remove(auto)
                return true
            }
        return false
    }

    fun getAutoById (iD: Int?) : Auto? {
        return autos.find{ it.id == iD}
    }
    fun findResource(id: Int): Any {
        for (auto in autos) {
            if (id == auto.id)
                return auto
        }
        return "status:${HttpStatus.NOT_FOUND}"
    }


    override fun retrieveCollection(): Collection<Any> {

        val updatedAutos: MutableList<Auto> = mutableListOf()
        for (auto in autos)
            updatedAutos.add(auto)
        return updatedAutos
    }

}
