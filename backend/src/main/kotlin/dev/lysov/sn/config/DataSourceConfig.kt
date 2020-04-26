package dev.lysov.sn.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource


/**
 * DataSourceConfig.
 *
 * @author Aleksei_Lysov
 */
@Configuration
class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    fun masterDataSource() = HikariDataSource()

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    fun slaveDataSource() = HikariDataSource()

    @Bean
    @Primary
    fun masterJdbcTemplate(masterDataSource: DataSource,
                           properties: JdbcProperties) = jdbcTemplate(masterDataSource, properties)

    @Bean
    fun slaveJdbcTemplate(@Qualifier("slaveDataSource") slaveDataSource: DataSource,
                          properties: JdbcProperties) = jdbcTemplate(slaveDataSource, properties)

    private fun jdbcTemplate(dataSource: DataSource, properties: JdbcProperties): JdbcTemplate {
        val jdbcTemplate = JdbcTemplate(dataSource)
        val template = properties.template
        jdbcTemplate.fetchSize = template.fetchSize
        jdbcTemplate.maxRows = template.maxRows
        if (template.queryTimeout != null) {
            jdbcTemplate.queryTimeout = template.queryTimeout.seconds.toInt()
        }
        return jdbcTemplate
    }
}