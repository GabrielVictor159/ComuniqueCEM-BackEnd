import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    @Scheduled(cron = "0 0 0 * * *")
    public void runEveryDay() {
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void runEveryMonth() {
    }

}
