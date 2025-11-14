package wee.sh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class WeeShBackendApplication

fun main(args: Array<String>) {
    runApplication<WeeShBackendApplication>(*args)
}
