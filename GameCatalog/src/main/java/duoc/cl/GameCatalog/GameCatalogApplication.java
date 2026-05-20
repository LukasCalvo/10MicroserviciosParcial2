package duoc.cl.GameCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GameCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameCatalogApplication.class, args);
	}

}
