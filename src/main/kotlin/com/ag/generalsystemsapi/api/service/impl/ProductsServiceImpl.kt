package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.*
import com.ag.generalsystemsapi.api.model.view.BindersView
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IProductsService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import com.ag.generalsystemsapi.thirdparty.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

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

    @Autowired
    lateinit var tpCoverTypesRepo: TpCoverTypesRepository

    @Autowired
    lateinit var coverTypesRepo: CoverTypesRepository

    @Autowired
    lateinit var tpSubClassCoverTypesRepo: TpSubClassCoverTypesRepository

    @Autowired
    lateinit var subClassCoverTypesRepo: SubClassCoverTypesRepository

    @Autowired
    lateinit var binderLimitsRepo: BinderLimitsRepository

    @Autowired
    lateinit var binderGroupLimitsRepo: BinderGroupLimitsRepository

    @Autowired
    lateinit var binderGroupsRepo: BinderGroupsRepository

    @Autowired
    lateinit var tpClassPerilsRepo: TpClassPerilsRepository

    @Autowired
    lateinit var classPerilsRepo: ClassPerilsRepository

    @Autowired
    lateinit var subClassPerilsMapRepo: SubClassPerilsMapRepository

    @Autowired
    lateinit var tpSubClassPerilsMapRepo: TpSubClassPerilsMapRepository

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

        //Cover Types.
        for(c in tpCoverTypesRepo.findAll()){
            val coverType = CoverTypesModel(
                coverCode = c.coverCode,
                coverShtDesc = c.coverShtDesc,
                coverDesc = c.coverDesc,
                coverDetails = c.coverDetails
            )
            coverTypesRepo.save(coverType)
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

            //fetch class perils.
            for(cp in tpClassPerilsRepo.findByClPerilClassCode(classes.classCode)){
                var classPerils = ClassPerilsModel(
                    clPerilCode = cp.clPerilCode,
                    clPerilPerilCode = perilsRepo.findById(cp.clPerilPerilCode!!).orElseThrow {Exception("peril not found") } ,
                    clPerilType = cp.clPerilType,
                    clPerilSIorLimit = cp.clPerilSIorLimit,
                    clPerilLimit = cp.clPerilLimit,
                    clPerilClassCode = classes,
                    clPerilExpireOnClaim = cp.clPerilExpireOnClaim
                )
                classPerils = classPerilsRepo.save(classPerils)
            }

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

                    //fetch subclass binder perils.
                    for(sp in tpSubClassPerilsMapRepo.findBySclPerilMapBinder(b.bindCode)){
                        val perilMap = SubClassPerilsMapModel(
                            sclPerilMapCode = sp.sclPerilMapCode,
                            sclPerilMapSubClass = subClasses,
                            sclPerilMapSection = sectionsRepo.findById(sp.sclPerilMapSection!!).orElse(null),
                            sclPerilMapBinder = binder,
                            sclPerilMapClassPeril = classPerilsRepo.findById(sp.sclPerilMapClassPeril!!).orElse(null)
                        )
                        subClassPerilsMapRepo.save(perilMap)
                    }
                }

                //fetch subclass cover types.
                for(sc in tpSubClassCoverTypesRepo.findByScCoverSubClass(s.subClassCode)){
                    val subCover = SubClassCoverTypesModel(
                        scCoverCode = sc.scCoverCode,
                        scCoverCoverTypeCode = coverTypesRepo.findByCoverCode(sc.scCoverCoverTypeCode),
                        scCoverCoverTypeShtDesc = sc.scCoverCoverTypeShtDesc,
                        scCoverCoverTypeDesc = sc.scCoverCoverTypeDesc,
                        scCoverSubClass = subClasses,
                        scCoverDefault = sc.scCoverDefault
                    )
                    subClassCoverTypesRepo.save(subCover)
                }

            }
        }
    }

    /*override fun findProductBinders(prodCode: Long) : Result<Iterable<BindersView>>{
        val product = productsRepo.findById(prodCode)
            .orElseThrow {Exception("Product not found") }

        val subClass = productSubClassesRepo.findByProdSubClassProdCodeAndProdSubClassDefault(product, "Y")
            .orElseThrow {Exception("Subclass not found") }

        val risks = bindersRepo.findByBindSubClassCodeAndBindWebDefault(subClass.prodSubClassSubclassCode, "Y")
            .map { r ->
                val binderLimits = binderLimitsRepo.findByBindLimitBinders(r)
                    .map { it.bindLimitAmount }

                BindersView(
                     bindCode = r.bindCode,
                     bindAgentCode = r.bindAgentCode,
                     bindAgentShtDesc = r.bindAgentShtDesc,
                     bindWef = r.bindWef,
                     bindWet = r.bindWet,
                     bindSubClassCode = r.bindSubClassCode?.subClassCode,
                     bindRemarks = r.bindRemarks,
                     bindName = r.bindName,
                     bindShtDesc = r.bindShtDesc,
                     bindType = r.bindType,
                     bindDefault = r.bindDefault,
                     bindWebDefault = r.bindWebDefault,
                     binderLimits = binderLimits
                )
            }
        return ResultFactory.getSuccessResult(risks)
    }*/

    override fun findProductBinders(prodCode: Long) : Result<Iterable<BindersView>>{
        val product = productsRepo.findById(prodCode)
            .orElseThrow {Exception("Product not found") }

        val subClass = productSubClassesRepo.findByProdSubClassProdCodeAndProdSubClassDefault(product, "Y")
            .orElseThrow {Exception("Subclass not found") }

        val risks = binderGroupsRepo.findByBindGroupSubClassCodeAndBindGroupWebDefault(subClass.prodSubClassSubclassCode, "Y")
            .map { r ->
                val binderLimits = binderGroupLimitsRepo.findByBindGroupLimitGroup(r)
                    .map { it.bindGroupLimitAmount }

                BindersView(
                    bindCode = r.bindGroupCode,
                    bindWef = r.bindGroupWef,
                    bindWet = r.bindGroupWet,
                    bindSubClassCode = r.bindGroupSubClassCode?.subClassCode,
                    bindRemarks = r.bindGroupRemarks,
                    bindName = r.bindGroupName,
                    bindShtDesc = r.bindGroupShtDesc,
                    bindType = r.bindGroupType,
                    bindDefault = r.bindGroupDefault,
                    bindWebDefault = r.bindGroupWebDefault,
                    binderLimits = binderLimits
                )
            }
        return ResultFactory.getSuccessResult(risks)
    }

    override fun findPetProducts() : Result<ProductsModel?>{
        return ResultFactory.getSuccessResult(productsRepo.findByProductCode(7907L))
    }
}