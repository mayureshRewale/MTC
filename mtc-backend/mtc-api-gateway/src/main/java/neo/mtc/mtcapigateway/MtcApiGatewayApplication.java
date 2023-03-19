package neo.mtc.mtcapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MtcApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtcApiGatewayApplication.class, args);
	}

}
