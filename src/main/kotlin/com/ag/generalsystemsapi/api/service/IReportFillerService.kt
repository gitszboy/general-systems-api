package com.ag.generalsystemsapi.api.service

import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport

interface IReportFillerService {
     fun compileReport(reportFileName: String) : JasperReport?

     fun fillReport(jasperReport: JasperReport?, parameters: MutableMap<String, Any>?) : JasperPrint?

}