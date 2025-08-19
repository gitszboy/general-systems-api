package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.service.IReportFillerService
import net.sf.jasperreports.engine.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.stereotype.Service
import java.io.InputStream
import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import javax.sql.DataSource

@Service
class ReportFillerServiceImpl : IReportFillerService {

    @Autowired
    @Value("\${apidb.datasource.jdbc-url}")
    lateinit var datasourceUrl: String

    @Autowired
    @Value("\${apidb.datasource.username}")
    lateinit var username: String

    @Autowired
    @Value("\${apidb.datasource.password}")
    lateinit var password: String

    @Autowired
    @Qualifier("dataSource")
    var prDataSource: DataSource? = null

    override fun compileReport(reportFileName: String) : JasperReport? {
        var jasperReport: JasperReport? = null
        try {
            var reportStream: InputStream = javaClass.getResourceAsStream("/$reportFileName")
            jasperReport = JasperCompileManager.compileReport(reportStream)
            //JRSaver.saveObject(jasperReport, reportFileName!!.replace(".jrxml", ".jasper"))
        } catch (ex: JRException) {
            Logger.getLogger(ReportFillerServiceImpl::class.java.name).log(Level.SEVERE, null, ex)
        }
        return jasperReport
    }

    override fun fillReport(jasperReport: JasperReport?, parameters: MutableMap<String, Any>?) : JasperPrint? {
        var jasperPrint: JasperPrint? = null
        //val dt: DataSource = getDataSource()!!
        val conn: Connection = DataSourceUtils.getConnection(prDataSource!!)//dt)
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn)
            conn.close()
        } catch (ex: JRException) {
            Logger.getLogger(ReportFillerServiceImpl::class.java.name).log(Level.SEVERE, null, ex)
        } catch (ex: SQLException) {
            Logger.getLogger(ReportFillerServiceImpl::class.java.name).log(Level.SEVERE, null, ex)
        } finally {
            conn.close()
        }
        return jasperPrint
    }

    /*fun getDataSource(): DataSource? {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.mariadb.jdbc.Driver")
        dataSourceBuilder.url(datasourceUrl)
        dataSourceBuilder.username(username)
        dataSourceBuilder.password(password)

        return dataSourceBuilder.build()
    }*/
}