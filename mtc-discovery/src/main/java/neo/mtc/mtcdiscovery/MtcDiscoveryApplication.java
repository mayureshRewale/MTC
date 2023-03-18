package neo.mtc.mtcdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MtcDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtcDiscoveryApplication.class, args);
	}

}
