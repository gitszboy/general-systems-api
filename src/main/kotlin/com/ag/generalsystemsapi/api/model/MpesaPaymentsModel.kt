package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "mpesa_payments")
class MpesaPaymentsModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpesa_code", nullable = true)
    var mpesaPayCode: Long?,

    @Column(name = "mpesa_date", nullable = true)
    var mpesaPayDate: Date? = null,

    @Column(name = "mpesa_mech_req_id", nullable = true)
    var mpesaPayMerchReqId: String? = null,

    @Column(name = "mpesa_check_req_id", nullable = true)
    var mpesaPayCheckReqId: String? = null,

    @Column(name = "mpesa_result_code", nullable = true)
    var mpesaPayResultCode: Long? = null,

    @Column(name = "mpesa_result_desc", nullable = true)
    var mpesaPayResultDesc: String? = null,

    @Column(name = "mpesa_receipt_no", nullable = true)
    var mpesaPayReceiptNumber: String? = null,

    @Column(name = "mpesa_telephone", nullable = true)
    var mpesaPayTelephone: String? = null,

    @Column(name = "mpesa_receipt_amt", nullable = true)
    var mpesaPayReceiptAmount: Double? = null,

    @Column(name = "mpesa_receipt_date", nullable = true)
    var mpesaPayReceiptDate: Date? = null,

    @Column(name = "mpesa_processed", nullable = true)
    var mpesaPayProcessed: String? = "No",

    @Column(name = "mpesa_processed_date", nullable = true)
    var mpesaPayProcessedDate: Date? = null,

)