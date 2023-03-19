package neo.mtc.mtcuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableEurekaClient
@ComponentScan({"neo.mtc"})
@SpringBootApplication
public class MtcUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtcUserApplication.class, args);
	}

}
