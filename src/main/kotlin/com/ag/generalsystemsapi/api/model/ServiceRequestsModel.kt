package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.Date
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "service_requests")
class ServiceRequestsModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sr_id", nullable = false)
    var servReqId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sr_srt_id")
    var servReqType: ServiceRequestTypesModel? = null,

    @Column(name = "sr_prp_code", nullable = true)
    var servReqClientCode: Long? = null,

    @Column(name = "sr_pol_code", nullable = true)
    var servReqPolicyCode: Long? = null,

    @Column(name = "sr_agn_code", nullable = true)
    var servReqAgentCode: Long? = null,

    @Column(name = "sr_description", nullable = true)
    var servReqDescription: String? = null,

    @Column(name = "sr_date", nullable = true)
    var servReqDate: Date? = null,

    @Column(name = "sr_assign_to", nullable = true)
    var servReqAssignee: String? = null,

    @Column(name = "sr_assign_comments", nullable = true)
    var servReqAssignComments: String? = null,

    @Column(name = "sr_status", nullable = true)
    var servReqStatus: String? = null,

    @Column(name = "sr_update_date", nullable = true)
    var servReqUpdateDate: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sr_user_id")
    var servReqUserRequesting: UsersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sr_assign_user_id")
    var servReqUserAssignedTo: UsersModel? = null,
)