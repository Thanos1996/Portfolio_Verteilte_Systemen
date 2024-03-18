package com.restmicroservice.portfolio4.semester.datasource

import com.restmicroservice.portfolio4.semester.model.Radius
import org.springframework.stereotype.Repository

@Repository
class RadiusDatenBank (
        internal var radius: MutableList<Radius> = mutableListOf(
                Radius(1,14),
                Radius(2,20),
                Radius(3,21),
                Radius(4,24),
                Radius(5,26),
                Radius(6,19),
                Radius(7,17)
        )
): CollectionSource {
    fun getRadiusById (iD: Int?) : Radius? {
        return radius.find{ it.id == iD}
    }

    override fun retrieveCollection(): Collection<Any> {
        return radius
    }
}