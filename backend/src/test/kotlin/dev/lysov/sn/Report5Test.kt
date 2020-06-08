package dev.lysov.sn

import com.github.javafaker.Faker
import com.github.javafaker.service.FakeValuesService
import com.github.javafaker.service.RandomService
import dev.lysov.sn.model.Account
import dev.lysov.sn.repository.AccountRepository
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@Disabled
@SpringBootTest
class Report5Test {

    @Autowired
    lateinit var accountRepository: AccountRepository;

    companion object {
        private val log = LoggerFactory.getLogger(Report5Test::class.java)
    }

    @Test
    fun insertData() {
        val faker = Faker(Locale("ru"))
        val fakeValuesService = FakeValuesService(Locale("ru"), RandomService())
        var result = 1;

        for (i in 1..1000000) {
            try {
                val gender = listOf("male", "female").random()
                val account = Account(
                        username = "user$i",
                        password = "\$2a\$10\$QfYRQVPZZ2S4j.VKVDRO0.QQc9pl2lQMRbCZXchqyvevAE2TSDQOi",
                        firstName = fakeValuesService.fetchString("name.${gender}_first_name"),
                        lastName = fakeValuesService.fetchString("name.${gender}_last_name"),
                        age = (15..75).random(),
                        gender = if (gender == "male") 1 else 0,
                        city = faker.address().city(),
                        description = faker.commerce().department()
                );
                accountRepository.save(account)
                log.info("new row id = ${account.id}")
                result++
            } catch (e: Exception) {
                log.info("BREAK")
                break
            }
        }

        log.info("count rows = $result");
    }
}