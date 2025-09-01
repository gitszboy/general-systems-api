package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.SubClassesModel
import java.util.*
import javax.persistence.*

class BindersView (
    var bindCode: Long? = null,
    var bindAgentCode: Long? = null,
    var bindAgentShtDesc: String? = null,
    var bindWef: Date? = null,
    var bindWet: Date? = null,
    var bindSubClassCode: Long? = null,
    var bindRemarks: String? = null,
    var bindName: String? = null,
    var bindShtDesc: String? = null,
    var bindType: String? = null,
    var bindDefault: String? = null,
    var bindWebDefault: String? = null,
    var binderLimits: List<Double?>? = null
)