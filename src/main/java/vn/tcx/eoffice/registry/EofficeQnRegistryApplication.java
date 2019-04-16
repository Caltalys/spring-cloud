package vn.tcx.eoffice.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EofficeQnRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EofficeQnRegistryApplication.class, args);
	}

}
