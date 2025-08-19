package com.ag.generalsystemsapi.api.model.view

class SystemRoleAreasRequest (
    var moduleId: Long?,
    var roleId: Long,
    var roleAreaActive: Boolean,
    var roleAreaCreate: Boolean,
    var roleAreaUpdate: Boolean,
    var roleAreaDelete: Boolean,
    var roleAreaRead: Boolean,
)