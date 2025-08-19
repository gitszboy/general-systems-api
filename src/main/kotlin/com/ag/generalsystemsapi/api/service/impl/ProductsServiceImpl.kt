package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.*
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IProductsService
import com.ag.generalsystemsapi.thirdparty.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductsServiceImpl : IProductsService{

    @Autowired
    lateinit var productsRepo: ProductsRepository

    @Autowired
    lateinit var tpProductsRepo: TpProductsRepository

    @Autowired
    lateinit var tpClassesRepo: TpClassesRepository

    @Autowired
    lateinit var classesRepo: ClassesRepository

    @Autowired
    lateinit var tpSubClassesRepo: TpSubClassesRepository

    @Autowired
    lateinit var subClassesRepo: SubClassesRepository

    @Autowired
    lateinit var tpProductSubClassesRepo: TpProductSubClassesRepository

    @Autowired
    lateinit var productSubClassesRepo: ProductSubClassesRepository

    @Autowired
    lateinit var tpSectionsRepo: TpSectionsRepository

    @Autowired
    lateinit var sectionsRepo: SectionsRepository

    @Autowired
    lateinit var tpPerilsRepo: TpPerilsRepository

    @Autowired
    lateinit var perilsRepo: PerilsRepository

    @Autowired
    lateinit var tpBindersRepo: TpBindersRepository

    @Autowired
    lateinit var bindersRepo: BindersRepository

    override fun populateProductDetails(){

        //fetch products.
        for(p in tpProductsRepo.findAll()){
            val product = ProductsModel(
                productCode = p.productCode,
                productShtDesc = p.productShtDesc,
                productDesc = p.productDesc,
                productWef = p.productWef,
                productWet = p.productWet
            )
            productsRepo.save(product)
        }

        //fetch sections.
        for(s in tpSectionsRepo.findAll()){
            val section = SectionsModel(
                sectionCode = s.sectionCode,
                sectionDescription = s.sectionDescription,
                sectionShtDesc = s.sectionShtDesc,
                sectionType = s.sectionType
            )
            sectionsRepo.save(section)
        }

        //fetch perils
        for(p in tpPerilsRepo.findAll()){
            val peril = PerilsModel(
                perilCode = p.perilCode,
                perilDescription = p.perilDescription,
                perilFullDescription = p.perilFullDescription,
                perilPaymentType = p.perilPaymentType,
                perilShtDesc = p.perilShtDesc,
                perilType = p.perilType,
                perilWef = p.perilWef,
                perilWet = p.perilWet
            )
            perilsRepo.save(peril)
        }

        //fetch classes.
        for(c in tpClassesRepo.findAll()){
            var classes = ClassesModel(
                classCode = c.classCode,
                classDescription = c.classDescription,
                classShortDesc = c.classShortDesc,
                classWef = c.classWef,
                classWet = c.classWet
            )
            classes = classesRepo.save(classes)

            //fetch subclasses.
            for(s in tpSubClassesRepo.findBySubClassClassCode(c.classCode)){
                var subClasses = SubClassesModel(
                    subClassCode = s.subClassCode,
                    subClassDescription = s.subClassDescription,
                    subClassClassCode = classes,
                    subClassShortDesc = s.subClassShortDesc,
                    subClassWef = s.subClassWef,
                    subClassWet = s.subClassWet
                )
                subClasses = subClassesRepo.save(subClasses)

                //fetch product subclasses.
                for(ps in tpProductSubClassesRepo.findByProdSubClassSubclassCode(s.subClassCode)){
                    val prdSub = ProductSubClassesModel(
                        prodSubClassCode = ps.prodSubClassCode,
                        prodSubClassProdCode = productsRepo.findByProductCode(ps.prodSubClassProdCode),
                        prodSubClassSubclassCode = subClasses,
                        prodSubClassWef = ps.prodSubClassWef,
                        prodSubClassWet = ps.prodSubClassWet,
                        prodSubClassMandatory = ps.prodSubClassMandatory
                    )
                    productSubClassesRepo.save(prdSub)
                }

                //fetch subclass binders.
                for(b in tpBindersRepo.findByBindSubClassCode(s.subClassCode)){
                    val binder = BindersModel(
                        bindCode = b.bindCode,
                        bindAgentCode = b.bindAgentCode,
                        bindAgentShtDesc = b.bindAgentShtDesc,
                        bindDefault = b.bindDefault,
                        bindName = b.bindName,
                        bindRemarks = b.bindRemarks,
                        bindShtDesc = b.bindShtDesc,
                        bindType = b.bindType,
                        bindSubClassCode = subClasses,
                        bindWebDefault = b.bindWebDefault,
                        bindWef = b.bindWef,
                        bindWet = b.bindWet
                    )
                    bindersRepo.save(binder)
                }
            }
        }
    }
}