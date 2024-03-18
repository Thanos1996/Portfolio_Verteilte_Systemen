package com.restmicroservice.portfolio4.semester.datasource

import com.restmicroservice.portfolio4.semester.model.Reifen
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class ReifenDatenBank(
        radiusDatenBank: RadiusDatenBank,
        // Constructor to Initialize der Reifen-Collection
        internal val reifen: MutableList<Reifen> =   mutableListOf(
                Reifen(1, "Hancook", radiusDatenBank.getRadiusById(2)),
                Reifen(2, "Continental", radiusDatenBank.getRadiusById(8)),
                Reifen(3, "Zolo", radiusDatenBank.getRadiusById(4)),
                Reifen(4, "Xolo", radiusDatenBank.getRadiusById(2)),
                Reifen(5, "Darius", radiusDatenBank.getRadiusById(6)),
                Reifen(6, "Psarius", radiusDatenBank.getRadiusById(1)),
                Reifen(7, "Zerious", radiusDatenBank.getRadiusById(5))
        )
): CollectionSource {

    fun deleteReife(id : Int) :Boolean{
        for (reife in reifen)
            if(reife.id == id){
                reifen.remove(reife)
                return true
            }
        return false
    }
    fun findResource (id: Int) : Any {
        for(reife in reifen){
            if (id == reife.id)
                return reife
        }
        return "status:${HttpStatus.NOT_FOUND}"
    }
    fun getReifenById (iD: Int?) : Reifen? {
        return reifen.find{ it.id == iD}
    }


    override fun retrieveCollection(): Collection<Any> {
        return reifen
    }


}