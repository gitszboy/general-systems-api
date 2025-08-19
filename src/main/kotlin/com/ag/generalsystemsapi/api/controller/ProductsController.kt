package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.payload.RegisterStaffUserRequest
import com.ag.generalsystemsapi.api.service.IProductsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@Tag(name = "Products Controller", description = "Endpoint - This service manages calls relating to Products")
@CrossOrigin(origins = ["*"])
class ProductsController {
    @Autowired
    lateinit var iProductsService: IProductsService

    @Operation(summary = "Populate Product Details", description = "Populate Product Details")
    @RequestMapping(value = ["/populateProductDetails"], method = [RequestMethod.POST])
    fun populateProductDetails(
    ) : ResponseEntity<*> {
        iProductsService.populateProductDetails()
        return ResponseEntity("Success", HttpStatus.OK)
    }
}