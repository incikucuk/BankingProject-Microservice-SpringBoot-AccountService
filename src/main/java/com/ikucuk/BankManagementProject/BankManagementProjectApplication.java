package com.ikucuk.BankManagementProject;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")  //base entity icindeki createdby-date valuları ıcın olsturulmus audit classındaki Component burada da belirtilmistir ve Etkinleştirilmiştir.
@OpenAPIDefinition(
		info = @Info(
				title = "Bank Management Project Documentation",
				description = "Accounts microservice REST API Documentation",
				version = "1.0.0",
				contact = @Contact(
						name = "inci kucuk",
						email = "zJqU4@example.com",
						url = "www.ikucuk.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Banking Documentation",
				url = "https://spring.banking.github.org/docs"
		)
)
public class BankManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementProjectApplication.class, args);
	}

	//http://localhost:8181/swagger-ui/index.html    =>swagger connet link
	///h2-console'. Database available at 'jdbc:h2:mem:test'  =H2 db connet link

}
