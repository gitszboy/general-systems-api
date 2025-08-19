package com.ag.generalsystemsapi.api.reporting

import net.sf.jasperreports.engine.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.Driver
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import javax.sql.DataSource

@Component
class JasperReportsHelper {
    @Autowired
    @Value("\${apidb.datasource.jdbc-url}")
    lateinit var datasourceUrl: String

    @Autowired
    @Value("\${apidb.datasource.username}")
    lateinit var username: String

    @Autowired
    @Value("\${apidb.datasource.password}")
    lateinit var password: String

    fun compileReport(reportFileName: String) : JasperReport? {
        var jasperReport: JasperReport? = null
        try {
            val reportStream = javaClass.getResourceAsStream("/$reportFileName")
            jasperReport = JasperCompileManager.compileReport(reportStream)
        } catch (ex: JRException) {
            Logger.getLogger(JasperReportsHelper::class.java.name).log(Level.SEVERE, null, ex)
        }
        return jasperReport
    }

    fun fillJasperReport(jasperReport: JasperReport?, parameters: MutableMap<String, Any>?) : JasperPrint? {
        var jasperPrint: JasperPrint? = null

        val dt: DataSource = simpleDataSource()
        val conn: Connection = DataSourceUtils.getConnection(dt)
        //val conn: Connection = DataSourceUtils.getConnection(prDataSource!!)//dt)
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn)
        } catch (ex: JRException) {
            ex.printStackTrace()
            Logger.getLogger(JasperReportsHelper::class.java.name).log(Level.SEVERE, null, ex)
        } catch (ex: SQLException) {
            ex.printStackTrace()
            Logger.getLogger(JasperReportsHelper::class.java.name).log(Level.SEVERE, null, ex)
        } finally {
            DataSourceUtils.doCloseConnection(conn, dt)
        }
        return jasperPrint
    }

    fun simpleDataSource(): DataSource {
        val dataSource = SimpleDriverDataSource()
        dataSource.setUrl(datasourceUrl)
        dataSource.username = username
        dataSource.password = password
        dataSource.setDriverClass((Class.forName("org.mariadb.jdbc.Driver") as Class<Driver?>))

        return dataSource
    }
}