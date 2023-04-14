import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
    
    @Autowired
    private UsuariosService usuariosService;
    @Scheduled(cron = "0 0 0 * * *")
    public void runEveryDay() {
        usuariosService.updateForTimeSenhasTemporarias();
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void runEveryMonth() {

    }

}
