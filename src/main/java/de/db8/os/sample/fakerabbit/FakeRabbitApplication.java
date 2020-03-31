package de.db8.os.sample.fakerabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "de.db8.os.sample.fakerabbit.openfeign")
public class FakeRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeRabbitApplication.class, args);
	}
}
