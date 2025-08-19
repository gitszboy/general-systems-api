package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "organization_accounts")
class OrganizationAccountsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_acc_id", nullable = false)
    var orgAccId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_acc_user_id", nullable = true)
    var orgAccUserId: UsersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_acc_org_code", nullable = true)
    var orgAccOrganizationId: OrganizationModel? = null,

    @Column(name = "org_acc_active", nullable = true)
    var orgAccActive: String? = "true",

    @Column(name = "org_acc_activated_date", nullable = true)
    var agnAccActivationDate: Date? = null,

    @Column(name = "org_acc_deactivated_date", nullable = true)
    var agnAccDeactivationDate: Date? = null,
)