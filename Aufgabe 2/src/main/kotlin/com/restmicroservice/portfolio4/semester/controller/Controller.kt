package com.restmicroservice.portfolio4.semester.controller

import com.restmicroservice.portfolio4.semester.service.AutoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class Controller(
        private val service: AutoService
) {

    @GetMapping("/api/{collection}", produces = ["application/json"])
    @ResponseBody
    fun findCollection (@PathVariable collection : String) = service.getCollection(collection)

    @PostMapping("/api/{collection}", produces = ["application/json"])
    @ResponseBody
    fun postResource (@PathVariable collection: String, @RequestBody resource: String) = service.post(collection,resource)

    @GetMapping("/api/{collection}/{id}", produces = ["application/json"])
    @ResponseBody
    fun findResource (@PathVariable collection : String, @PathVariable id :Int) = service.getResource(collection, id)

    @DeleteMapping("/api/{collection}/{id}", produces = ["application/json"])
    @ResponseBody
    fun deleteResource (@PathVariable collection : String, @PathVariable id :Int) = service.removeResource(collection, id)

    @PatchMapping("/api/{collection}/{id}", produces = ["application/json"])
    @ResponseBody
    fun patchResource (@PathVariable collection : String, @PathVariable id :Int, @RequestBody resource: String) = service.patch(collection,id,resource)

    @PutMapping ("/api/{collection}/{id}", produces = ["application/json"])
    @ResponseBody
    fun putResource (@PathVariable collection : String, @PathVariable id :Int, @RequestBody resource: String) = service.put(collection,id,resource)

}