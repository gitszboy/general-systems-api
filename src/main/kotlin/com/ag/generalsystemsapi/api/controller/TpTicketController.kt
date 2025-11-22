package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.payload.ServiceRequest
import com.ag.generalsystemsapi.api.service.impl.TpTicketServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ticketing")
@Tag(name = "Third Party Tickets Controller", description = "Endpoint - This service manages calls relating to Third Party Tickets")
@CrossOrigin(origins = ["*"])
class TpTicketController(private val tpTicketServiceImpl: TpTicketServiceImpl) {
    @Operation(summary = "Create Tp Ticket", description = "Create Tp Ticket")
    @RequestMapping(value = ["/createTicket"], method = [RequestMethod.POST])
    fun createTicket(
        @RequestParam(name= "process", required = false) process: String?,
        @RequestParam(name= "user", required = false) user: String?,
        @RequestParam(name= "position", required = false) position: String?,
        @RequestParam(name= "module", required = false) module: String?,
        @RequestParam(name= "batchno", required = false) batchno: String?,
        @RequestParam(name= "quotecode", required = false) quotecode: String?,
        @RequestParam(name= "claimno", required = false) claimno: String?
    ) : ResponseEntity<Void> {
         tpTicketServiceImpl.startNewWorkflowInstance(
            process, user, position, module, batchno, quotecode, claimno
        )
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}