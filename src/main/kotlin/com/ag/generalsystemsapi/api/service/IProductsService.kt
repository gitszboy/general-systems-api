package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.BindersModel
import com.ag.generalsystemsapi.api.model.ProductsModel
import com.ag.generalsystemsapi.api.model.view.BindersView
import com.ag.generalsystemsapi.api.util.Result

interface IProductsService {
    fun populateProductDetails()
    fun findProductBinders(prodCode: Long) : Result<Iterable<BindersView>>
    fun findPetProducts() : Result<ProductsModel?>
}