package com.ag.generalsystemsapi.api.helpers

import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDate
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

    fun getFirstAndLastDate(date: Date): Pair<Date, Date> {
        val calendar = Calendar.getInstance()
        calendar.time = date

        // First day of month
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDay = calendar.time

        // Last day of month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val lastDay = calendar.time

        return Pair(firstDay, lastDay)
    }
}