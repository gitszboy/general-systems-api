package com.ag.generalsystemsapi.api.reporting

import lombok.Getter
import lombok.Setter
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import net.sf.jasperreports.export.SimplePdfExporterConfiguration
import net.sf.jasperreports.export.SimplePdfReportConfiguration
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Component
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

@Component
@Getter
@Setter
class JasperReportExporter {

    fun exportToPdf(fileName: String?, author: String?, jasperPrint: JasperPrint?) : ByteArray? {

        // print report to file
        var output: File? = null
        var outputByteArray: ByteArray? = null
        val exporter = JRPdfExporter()
        exporter.setExporterInput(SimpleExporterInput(jasperPrint))
        exporter.exporterOutput = SimpleOutputStreamExporterOutput(fileName)
        val reportConfig = SimplePdfReportConfiguration()
        reportConfig.isSizePageToContent = true
        reportConfig.isForceLineBreakPolicy = false
        val exportConfig = SimplePdfExporterConfiguration()
        exportConfig.metadataAuthor = author
        exportConfig.isEncrypted = true
        exportConfig.setAllowedPermissionsHint("PRINTING")
        exporter.setConfiguration(reportConfig)
        exporter.setConfiguration(exportConfig)
        try {
            exporter.exportReport()
            output = File(fileName)
            outputByteArray = FileUtils.readFileToByteArray(output)
        } catch (ex: JRException) {
            Logger.getLogger(JasperReportExporter::class.java.name).log(Level.SEVERE, null, ex)
        }
        return outputByteArray
    }
}