package com.ag.generalsystemsapi.api.helpers

import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class ComputationResourceHelper {
    fun computeAnb(effectiveDate: Date, dateOfBirth: Date) : Long{
        //Calculate the ANB
        val diff: Duration = Duration.between(effectiveDate.toInstant(), dateOfBirth.toInstant())

        return diff.toDays().div((365).toLong()) + 1
    }

    fun computePensionTerm(effectiveDate: Date, dateOfBirth: Date, retireAge: Long) : Long{
        val diff: Duration = Duration.between(effectiveDate.toInstant(), dateOfBirth.toInstant())

        val anb = diff.toDays().div((365).toLong()) + 1

        val term = retireAge.minus(anb)

        return term
    }

    fun computeMaturityDate(effectiveDate: Date, term: Long) : Date{
        val cal = Calendar.getInstance()
        cal.time = effectiveDate
        cal.add(Calendar.YEAR, term.toInt())
        return cal.time
    }
}