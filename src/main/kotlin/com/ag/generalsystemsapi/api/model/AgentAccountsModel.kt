package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "agent_accounts")
class AgentAccountsModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agn_acc_id", nullable = false)
    var agnAccId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agn_acc_user_id", nullable = true)
    var agnAccUserId: UsersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agn_acc_agn_code", nullable = true)
    var agnAccAgentId: AgentsModel? = null,

    @Column(name = "agn_acc_active", nullable = true)
    var agnAccAgentActive: String? = "true",

    @Column(name = "agn_acc_activated_date", nullable = true)
    var agnAccAgentActivationDate: Date? = null,

    @Column(name = "agn_acc_deactivated_date", nullable = true)
    var agnAccAgentDeactivationDate: Date? = null,
)