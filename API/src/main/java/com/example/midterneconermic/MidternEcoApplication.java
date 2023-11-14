package com.example.midterneconermic;

import com.example.midterneconermic.model.*;
import com.example.midterneconermic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@RequiredArgsConstructor
public class MidternEcoApplication implements CommandLineRunner {

	private final AccountRepository accountRepository;
	private final ProductRepository productRepository;


	public static void main(String[] args) {
		SpringApplication.run(MidternEcoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Account adminAccount = accountRepository.findByRole(Role.ADMIN);

		if( null == adminAccount) {
			Account acc = new Account();
			acc.setUsername("Admin");
			acc.setPassword(new BCryptPasswordEncoder().encode("admin"));
			acc.setRole(Role.ADMIN);
			accountRepository.save(acc);

			Account acc2 = new Account();
			acc2.setUsername("huutaitran");
			acc2.setPassword(new BCryptPasswordEncoder().encode("tai2k300"));
			acc2.setRole(Role.USER);
			accountRepository.save(acc2);


			Product product1 = new Product();
			product1.setName("Sample Product 1");
			product1.setCategory("Electronics");
			product1.setPrice(99.99);
			product1.setBrand("Brand X");
			product1.setColor("Black");
			product1.setThumbnails("thumbnail1.jpg");

			Product product2 = new Product();
			product2.setName("Sample Product 2");
			product2.setCategory("Clothing");
			product2.setPrice(49.99);
			product2.setBrand("Fashion Y");
			product2.setColor("Blue");
			product2.setThumbnails("thumbnail2.jpg");

			// Save the sample data to the database
			productRepository.save(product1);
			productRepository.save(product2);

		}

	}
}
