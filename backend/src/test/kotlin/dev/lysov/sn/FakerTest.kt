package dev.lysov.sn

import com.github.javafaker.Faker
import com.github.javafaker.service.FakeValuesService
import com.github.javafaker.service.RandomService
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Disabled
class FakerTest {

    @Test
    fun generateData() {
        val row = "%s;%s;%s;%s;%s;%s;%s;%s"
        val faker = Faker(Locale("ru"))
        val fakeValuesService = FakeValuesService(Locale("ru"), RandomService())
        val accounts = mutableListOf<String>();

        for (i in 1..1000000) {
            val gender = listOf("male", "female").random()
            accounts.add(row.format(
                    "user$i",
                    "\$2a\$10\$QfYRQVPZZ2S4j.VKVDRO0.QQc9pl2lQMRbCZXchqyvevAE2TSDQOi",
                    fakeValuesService.fetchString("name.${gender}_first_name"),
                    fakeValuesService.fetchString("name.${gender}_last_name"),
                    (15..75).random(),
                    if (gender == "male") 1 else 0,
                    faker.address().city(),
                    faker.commerce().department()
            ))
        }

        Files.write(Paths.get("insert-account.txt"), accounts);
    }
}