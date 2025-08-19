package com.ag.generalsystemsapi.api.helpers

import com.ag.generalsystemsapi.api.util.ResultFactory
import org.springframework.stereotype.Component
import kotlin.math.roundToInt

@Component
class InterestResourceHelper {

    fun compoundInterest(rate: Double, period: Long, time: Double, principal: Double): MutableMap<String, Double?>{
        var interest = 0.0
        var newPrincipal = 0.0
        val computation = mutableMapOf<String, Double?>()
        try{
            newPrincipal = principal.times(Math.pow(1.0 + (rate.div(100.00)).div(period), time)).roundToInt().toDouble()
            interest = newPrincipal.minus(principal).roundToInt().toDouble()
            computation["newPrincipal"] = newPrincipal
            computation["interest"] = interest
        }catch(e: Exception){
            e.printStackTrace()
            ResultFactory.getFailResult<String>("Compound Interest Failure")
        }
        return computation
    }

    fun yearlyCompoundInterest(rate: Double, period: Long, time: Double, principal: Double): Double{
        var interest = 0.0
        var newPrincipal = 0.0
        var computation = 0.0
        try{
            newPrincipal = principal.times(Math.pow(1.0 + (rate.div(100.00)).div(period), time)).roundToInt().toDouble()
            interest = newPrincipal.minus(principal).roundToInt().toDouble()
            computation = interest
        }catch(e: Exception){
            e.printStackTrace()
            ResultFactory.getFailResult<String>("Compound Interest Failure")
        }
        return computation
    }

    fun simpleInterest(rate: Double, period: Long, time: Double, principal: Double): MutableMap<String, Double?>{
        var interest = 0.0
        var newPrincipal = 0.0
        val computation = mutableMapOf<String, Double?>()
        try{
            newPrincipal = principal.times(1.0 + ((rate.div(100.00)).times(time.div(period))))
            interest = newPrincipal.minus(principal)
            computation["newPrincipal"] = newPrincipal
            computation["interest"] = interest
        }catch(e: Exception){
            e.printStackTrace()
            ResultFactory.getFailResult<String>("Simple Interest Failure")
        }
        return computation
    }

    fun yearlySimpleInterest(rate: Double, period: Long, time: Double, principal: Double): Double{
        var interest = 0.0
        var newPrincipal = 0.0
        var computation = 0.0
        try{
            newPrincipal = principal.times(1.0 + ((rate.div(100.00)).times(time.div(period))))
            interest = newPrincipal.minus(principal)
            computation = interest
        }catch(e: Exception){
            e.printStackTrace()
            ResultFactory.getFailResult<String>("Simple Interest Failure")
        }
        return computation
    }

    fun annualConverter(amount: Double?, frequency: String, term: Long) : Double{
        var convertAmt = amount.let { amount }?:0.0
        when(frequency){
            "A" -> {
            convertAmt = convertAmt.let { amount }?:0.0
            }
            "S" -> {
                convertAmt = convertAmt.times(2)
            }
            "Q" -> {
                convertAmt = convertAmt.times(4)
            }
            "M" -> {
                convertAmt = convertAmt.times(12)
            }
            "F" -> {
                convertAmt = convertAmt.div(term)
            }
        }
        return convertAmt
    }
}