package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "client_accounts")
class ClientAccountsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clnt_acc_id", nullable = false)
    var clntAccId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clnt_acc_user_id", nullable = true)
    var clntAccUserId: UsersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clnt_acc_prp_code", nullable = true)
    var clntAccClientCode: ClientsModel? = null,

    @Column(name = "clnt_acc_active", nullable = true)
    var clntAccClientActive: String? = "true",

    @Column(name = "clnt_acc_activated_date", nullable = true)
    var clntAccClientActivationDate: Date? = null,

    @Column(name = "clnt_acc_deactivated_date", nullable = true)
    var clntAccClientDeactivationDate: Date? = null,
)