package com.ag.generalsystemsapi.api.reporting

import com.ag.generalsystemsapi.thirdparty.repository.TpSystemReportTemplateRepository
import com.ag.generalsystemsapi.thirdparty.repository.TpSystemReportsRepository
import com.sun.java.util.collections.ArrayList
import oracle.apps.xdo.dataengine.DataProcessor
import oracle.apps.xdo.dataengine.Parameter
import oracle.apps.xdo.template.FOProcessor
import oracle.apps.xdo.template.RTFProcessor
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.stereotype.Component
import java.io.File
import java.sql.Connection
import java.sql.Driver
import javax.sql.DataSource


@Component
class OracleBIPublisherHelper {
    @Autowired
    @Value("\${thirdpartydb.datasource.jdbc-url}")
    lateinit var datasourceUrl: String

    @Autowired
    @Value("\${thirdpartydb.datasource.username}")
    lateinit var username: String

    @Autowired
    @Value("\${thirdpartydb.datasource.password}")
    lateinit var password: String

    @Autowired
    lateinit var tpSystemReportsRepo: TpSystemReportsRepository

    @Autowired
    lateinit var tpSystemReportTemplateRepo: TpSystemReportTemplateRepository

    fun simpleOracleDataSource(): DataSource {
        val dataSource = SimpleDriverDataSource()
        dataSource.setUrl(datasourceUrl)
        dataSource.username = username
        dataSource.password = password
        dataSource.setDriverClass((Class.forName("oracle.jdbc.OracleDriver") as Class<Driver?>))

        return dataSource
    }

    fun getReportsPath(fileName: String) : String{
        //val res = ClassPathResource("reports/comm_stmt.pdf").file
        return ClassPathResource("").file.absolutePath + File.separator + "reports" +File.separator+fileName
        //var data = res.absolutePath
        //data = data.replace("comm_stmt.pdf", fileName)
        //println("data ="+data+" other = "+res2)
    }

    fun runReport(rptCode: Long, params: MutableMap<String, Any?>) : ByteArray?{
        val report = tpSystemReportsRepo.findByReportCode(rptCode)
        var reportBytes: ByteArray? = null

        val agentCode = params["agentCode"]
        val endorsementCode = params["endorsementCode"]
        val branchCode = params["branchCode"]
        val transNo = params["transNo"]
        val policyCode = params["policyCode"]

        if(report!=null){
            dataEngine(report.reportDataFile,
                       report.reportName,
                       agentCode?.let { it.toString().toLong() },
                       endorsementCode?.let { it.toString().toLong() },
                       branchCode?.let { it.toString().toLong() },
                       transNo?.let { it.toString().toLong() },
                       policyCode?.let { it.toString().toLong() },)

            val template = tpSystemReportTemplateRepo.findByReportTemplReportCode(rptCode)
            reportBytes = processorEngine(template.reportTemplFile, template.reportTemplStyleFile, report.reportName)
        }

        return reportBytes
    }
    fun dataEngine(dataFile: String,
                   reportName: String,
                   agentCode: Long?,
                   endorsementCode: Long?,
                   branchCode: Long?,
                   transNo: Long?,
                   policyCode: Long?){

        val data = getReportsPath(dataFile)
        val dataProcessor = DataProcessor()
        dataProcessor.setDataTemplate(data)
        val output = getReportsPath("$reportName.xml")

        val parameters2: ArrayList = dataProcessor.parameters
        val it = parameters2.iterator()
        while (it.hasNext()){
            val p = it.next() as Parameter
            when(p.name){
                "V_AGN_CODE" ->{
                    p.value = agentCode
                }
                "V_ENDR_CODE" ->{
                    p.value = endorsementCode
                }
                "V_TRANS_NO" ->{
                    p.value = transNo
                }
                "V_BRN_CODE" ->{
                    p.value = branchCode
                }
                "V_POL_CODE" ->{
                    p.value = policyCode
                }
            }
        }

        val dt: DataSource = simpleOracleDataSource()
        val conn: Connection = DataSourceUtils.getConnection(dt)

        dataProcessor.setParameters(parameters2)
        dataProcessor.setConnection(conn)
        dataProcessor.setOutput(output)
        dataProcessor.processData()
    }

    fun processorEngine(templateFile: String, styleFile: String, reportName: String) : ByteArray?{
        val processor = FOProcessor()
        var bytes: ByteArray? = null

        val rtfProcessor = RTFProcessor(getReportsPath(templateFile)) //input template
        rtfProcessor.setOutput(getReportsPath(styleFile)) // output file
        rtfProcessor.process()

        val data = getReportsPath("$reportName.xml")
        processor.setData(data)
        processor.setTemplate(getReportsPath(styleFile))
        processor.setOutput(getReportsPath("$reportName.pdf"))
        val output = getReportsPath("$reportName.pdf")
        processor.setOutputFormat(FOProcessor.FORMAT_PDF);

        processor.generate()

        val file = File(output)
        return FileUtils.readFileToByteArray(file)
    }

}