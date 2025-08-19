package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "organizations")
class OrganizationModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_code")
    var orgCode: Long? = null,

    @Column(name = "org_name", nullable = true)
    var orgName: String? = null,

    @Column(name = "org_postal_address", nullable = true)
    var orgPostalAddress: String?,

    @Column(name = "org_physical_address", nullable = true)
    var orgPhysicalAddress: String? = null,

    @Column(name = "org_primary_tel", nullable = true)
    var orgPrimaryTelephone: String? = null,

    @Column(name = "org_secondary_tel", nullable = true)
    var orgSecondaryTelephone: String? = null,

    @Column(name = "org_mobile_tel", nullable = true)
    var orgMobileTelephone: String? = null,

    @Column(name = "org_pin", nullable = true)
    var orgPin: String? = null,

    @Column(name = "org_moto", nullable = true)
    var orgMoto: String? = null,

    @Column(name = "org_website", nullable = true)
    var orgWebsite: String? = null,

    @Column(name = "org_scheme_name", nullable = true)
    var orgSchemeName: String? = null,

    @Column(name = "org_status", nullable = true)
    var orgStatus: String? = null,

    @Column(name = "org_activation_date", nullable = true)
    var orgActivationDate: Date? = null,

    @Column(name = "org_deactivation_date", nullable = true)
    var orgDeactivationDate: Date? = null,

    @Column(name = "org_default", nullable = true)
    var orgDefault: Boolean? = false,

    )
