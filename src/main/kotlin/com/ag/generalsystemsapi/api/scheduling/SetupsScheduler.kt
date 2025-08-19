package com.ag.generalsystemsapi.api.scheduling

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SetupsScheduler {

    /*@Transactional
    @Scheduled(cron = "0 0 22 25 * ?", zone = "GMT+3:00") // run every 25 of the month at 11pm
    @SchedulerLock(name = "migrateSetupDataFromTQ", lockAtLeastFor = "PT2M", lockAtMostFor = "PT10M")
    fun migrateSetupDataFromTQ() {
        productsService.populateProductDetails()
    }*/

}