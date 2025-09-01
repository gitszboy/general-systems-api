package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.BindersModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import com.ag.generalsystemsapi.api.model.ProductsModel
import com.ag.generalsystemsapi.api.model.payload.RegisterStaffUserRequest
import com.ag.generalsystemsapi.api.model.view.BindersView
import com.ag.generalsystemsapi.api.service.IProductsService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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

    @Operation(summary = "Find Product SubClass Binders", description = "Fetches Product Subclass Binders")
    @GetMapping("/findProductBinders", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findProductBinders(@RequestParam(required = true) productCode: Long): Result<Iterable<BindersView>> = iProductsService.findProductBinders(productCode)

    @Operation(summary = "Find Pet Products", description = "Fetches Pet Products")
    @GetMapping("/findPetProducts", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPetProducts(): Result<ProductsModel?> = iProductsService.findPetProducts()

}