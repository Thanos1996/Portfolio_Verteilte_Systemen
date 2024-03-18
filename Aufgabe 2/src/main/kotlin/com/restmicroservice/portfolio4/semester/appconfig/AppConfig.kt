
import com.restmicroservice.portfolio4.semester.datasource.AutoDatenBank
import com.restmicroservice.portfolio4.semester.datasource.ReifenDatenBank
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun autoDatenBank(): AutoDatenBank {
        return autoDatenBank() // Instantiate and return the MyBean instance
    }

    @Bean
    fun reifenDatenBank(): ReifenDatenBank {
        return reifenDatenBank() // Instantiate and return the MyBean instance
    }
}