package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "prospects_accounts")
class ProspectsAccountsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prosa_acc_id", nullable = false)
    var prosaAccId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosa_acc_user_id", nullable = true)
    var prosaAccUserId: UsersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosa_acc_prosp_code", nullable = true)
    var prosaAccProspectCode: ProspectsModel? = null,

    @Column(name = "prosa_acc_active", nullable = true)
    var prosaAccProspectActive: String? = "true",

    @Column(name = "prosa_acc_activated_date", nullable = true)
    var prosaAccProspectActivationDate: Date? = null,

    @Column(name = "prosa_acc_deactivated_date", nullable = true)
    var prosaAccProspectDeactivationDate: Date? = null,
)