package com.restmicroservice.portfolio4.semester.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.restmicroservice.portfolio4.semester.datasource.AutoDatenBank
import com.restmicroservice.portfolio4.semester.datasource.RadiusDatenBank
import com.restmicroservice.portfolio4.semester.datasource.ReifenDatenBank
import com.restmicroservice.portfolio4.semester.model.Auto
import com.restmicroservice.portfolio4.semester.model.Reifen
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class AutoService(
    private var autoDatenBank: AutoDatenBank,
    private var reifenDatenBank: ReifenDatenBank,
    private var radiusDatenBank: RadiusDatenBank,

    private val objectMapper: ObjectMapper = jacksonObjectMapper()
) {
    fun getCollection(collection: String): Any {
        return when (collection) {
            "reife" -> {
                reifenDatenBank.retrieveCollection()
            }

            "auto" -> {

                autoDatenBank.retrieveCollection()

            }

            "radius" -> {
                radiusDatenBank.retrieveCollection()
            }

            else -> {
                "status:${HttpStatus.NOT_FOUND}"
            }
        }
    }

    fun post(collection: String, resource: Any): Any {
        return when (collection) {
            "auto" -> {
                val desirialisedResource: Auto = objectMapper.readValue(resource.toString(), Auto::class.java)
                desirialisedResource.id = Random.nextInt(1, 150)
                desirialisedResource.reifen = reifenDatenBank.getReifenById(Random.nextInt(1, 7))

                autoDatenBank.autos.add(desirialisedResource) // Use add() method to add the resource to the list
                autoDatenBank.retrieveCollection()
            }

            "reife" -> {
                val desirialisedResource: Reifen = objectMapper.readValue(resource.toString(), Reifen::class.java)
                desirialisedResource.id = reifenDatenBank.reifen.size + 1
                reifenDatenBank.reifen.add(desirialisedResource)
                reifenDatenBank.retrieveCollection()
            }

            else -> "status:${HttpStatus.NOT_ACCEPTABLE}"
        }
    }

    fun getResource(collection: String, id: Int): Any {
        return when (collection) {
            "auto" -> {
                autoDatenBank.findResource(id)
            }

            "reife" -> {
                reifenDatenBank.findResource(id)
            }

            else -> "status:${HttpStatus.INTERNAL_SERVER_ERROR}"
        }
    }

    fun removeResource(collection: String, id: Int): String {
        var deleted = false
        when (collection) {
            "auto" -> {
                deleted = autoDatenBank.deleteAuto(id)
            }

            "reife" -> {
                deleted = reifenDatenBank.deleteReife(id)
            }
        }
        return when (deleted) {
            true -> {
                return "status:${HttpStatus.OK}"
            }

            false -> {
                "status:${HttpStatus.BAD_REQUEST}"
            }
        }
    }

    fun patch(collection: String, id: Int, resource: Any): String {
        var status = "status:${HttpStatus.OK}"
        when (collection) {
            "reife" -> {
                val reife = reifenDatenBank.getReifenById(id)
                if (reife != null) {
                    val desirialisedResource: Reifen = objectMapper.readValue(resource.toString(), Reifen::class.java)
                    if (!desirialisedResource.hersteller.isNullOrBlank())
                        reife.hersteller = desirialisedResource.hersteller
                    if (desirialisedResource.radius != null)
                        reife.radius = radiusDatenBank.getRadiusById(desirialisedResource.radius!!.id)
                } else status = "status:${HttpStatus.NOT_FOUND}"
            }

            "auto" -> {
                val auto = autoDatenBank.getAutoById(id)
                if (auto != null) {
                    val desirialisedResource: Auto = objectMapper.readValue(resource.toString(), Auto::class.java)

                    if (!desirialisedResource.hersteller.isNullOrBlank())
                        auto.hersteller = desirialisedResource.hersteller
                    if (!desirialisedResource.model.isNullOrBlank())
                        auto.model = desirialisedResource.model
                    if (desirialisedResource.reifen != null)
                        auto.reifen = reifenDatenBank.getReifenById(desirialisedResource.reifen!!.id)

                } else
                    status = "status:${HttpStatus.NOT_FOUND}"
            }
        }
        return status

    }

    fun put(collection: String, id: Int, resource: String): Any {
        var status = "status:${HttpStatus.OK}"
        when (collection) {
            "auto" -> {
                val auto = autoDatenBank.getAutoById(id)
                if (auto == null) {
                    return "status:${HttpStatus.NOT_FOUND}"
                } else {
                    val desirialisedResource: Auto = objectMapper.readValue(resource, Auto::class.java)
                    auto.model = desirialisedResource.model
                    auto.hersteller = desirialisedResource.hersteller
                    if (reifenDatenBank.getReifenById(desirialisedResource.reifen!!.id) != null) {
                        auto.reifen = reifenDatenBank.getReifenById(desirialisedResource.reifen!!.id)
                        auto.reifen!!.radius =
                            radiusDatenBank.getRadiusById(desirialisedResource.reifen!!.radius!!.id)
                    }
                }
            }

            "reife" -> {
                val reife = reifenDatenBank.getReifenById(id)
                if (reife == null) {
                    return "status:${HttpStatus.NOT_FOUND}"
                }else{
                    val desirialisedResource: Reifen = objectMapper.readValue(resource, Reifen::class.java)
                    reife.hersteller=desirialisedResource.hersteller
                    reife.radius = radiusDatenBank.getRadiusById(desirialisedResource.radius!!.id)
                }
            }

            else -> status = "status:${HttpStatus.BAD_REQUEST}"
        }
        return status
    }

}

