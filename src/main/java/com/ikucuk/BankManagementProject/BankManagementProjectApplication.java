package com.ikucuk.BankManagementProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")  //base entity icindeki createdby-date valuları ıcın olsturulmus audit classındaki Component burada da belirtilmistir ve Etkinleştirilmiştir.
public class BankManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementProjectApplication.class, args);
	}

}
