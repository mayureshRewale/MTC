package neo.mtc.mtcuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"neo.mtc"})
@SpringBootApplication
public class MtcUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtcUserApplication.class, args);
	}

}
