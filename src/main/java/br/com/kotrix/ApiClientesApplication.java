package br.com.kotrix;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.kotrix.model.entity.Cliente;
import br.com.kotrix.model.repository.ClienteRepository;

@SpringBootApplication
public class ApiClientesApplication {
	
	@Bean
	public CommandLineRunner run ( @Autowired ClienteRepository repository ) {
		return args->{
			
			Cliente c1 = new Cliente (null, "Fulano", "00000000000", LocalDate.now());
			Cliente c2 = new Cliente (null, "Ciclano", "11111111111", LocalDate.now());
			
			repository.save(c1);
			repository.save(c2);
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiClientesApplication.class, args);
	}

}
