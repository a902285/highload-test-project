package dev.lysov.sn.repository

import dev.lysov.sn.model.Account
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.Statement

@Repository
class AccountRepositoryImpl(
        val jdbcTemplate: JdbcTemplate
) : AccountRepository {

    override fun findAll() =
            jdbcTemplate.query("select * from account", rowMapper())

    override fun findOne(id: Long) =
            jdbcTemplate.queryForObject("select * from account where id = ?", arrayOf(id), rowMapper())

    override fun save(account: Account) = if (account.id == null) {
        create(account)
    } else {
        update(account)
    }

    private fun create(account: Account): Account {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ connection ->
            val ps = connection.prepareStatement("insert into account (username, password, first_name, last_name, age, gender, city, description) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
            ps.setString(1, account.username)
            ps.setString(2, account.password)
            ps.setString(3, account.firstName)
            ps.setString(4, account.lastName)
            ps.setObject(5, account.age)
            ps.setObject(6, account.gender)
            ps.setString(7, account.city)
            ps.setString(8, account.description)
            ps
        }, keyHolder)
        account.id = keyHolder.key?.toLong()
        return account
    }

    private fun update(account: Account): Account {
        jdbcTemplate.update { connection ->
            val ps = connection.prepareStatement("update account set first_name = ?, " +
                    "last_name = ?, age = ?, gender = ?, city = ?, description = ? where id = ?")
            ps.setString(1, account.firstName)
            ps.setString(2, account.lastName)
            ps.setObject(3, account.age)
            ps.setObject(4, account.gender)
            ps.setString(5, account.city)
            ps.setString(6, account.description)
            ps.setLong(7, account.id!!)
            ps
        }
        return account
    }

    override fun delete(id: Long) {
        jdbcTemplate.update("delete from account where id = ?") {
            it.setLong(1, id)
        }
    }

    override fun findByUsername(username: String) =
            jdbcTemplate.queryForObject("select * from account where username = ?", arrayOf(username), rowMapper())

    private fun rowMapper() = { rs: ResultSet, _: Int ->
        val account = Account(
                id = rs.getLong("id"),
                username = rs.getString("username"),
                password = rs.getString("password"),
                firstName = rs.getString("first_name"),
                lastName = rs.getString("last_name"),
                gender = rs.getInt("gender"),
                city = rs.getString("city"),
                description = rs.getString("description")
        )

        val age = rs.getInt("age")
        account.age = if (!rs.wasNull()) {
            age
        } else {
            null
        }
        val gender = rs.getInt("gender")
        account.gender = if (!rs.wasNull()) {
            gender
        } else {
            null
        }

        account
    }
}