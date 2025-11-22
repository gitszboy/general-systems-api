package com.ag.generalsystemsapi.api.service.impl

import org.springframework.stereotype.Service
import com.ticketing.tqgisclient.CreateTicketImplService

@Service
class TpTicketServiceImpl(private val createTicketService: CreateTicketImplService) {
    fun startNewWorkflowInstance(
        process: String?,
        user: String?,
        position: String?,
        module: String?,
        batchno: String?,
        quotecode: String?,
        claimno: String?
    ): String {
        val port = createTicketService.createTicketImplPort
        println("ticketing values = $process $user $position $module $batchno $quotecode $claimno")
        return port.startNewWorkflowInstance(process, user, position, module, batchno, quotecode, claimno)
    }
}