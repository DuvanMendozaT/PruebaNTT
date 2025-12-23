package com.training.portal;

import com.training.portal.dto.CourseModel;
import com.training.portal.persistence.entity.CourseEntity;
import com.training.portal.persistence.entity.UserEntity;
import com.training.portal.persistence.mapper.CourseMapper;
import com.training.portal.persistence.repository.CourseRepository;
import com.training.portal.persistence.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Log4j2
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	CommandLineRunner initCourseDatabase(UserRepository userRepository) {
		return args -> {
			if (userRepository.count() == 0) {
				//fullstack
				userRepository.save(
						UserEntity.builder()
								.email("admin@correo.com")
								.fullName("Duvan Mendoza")
								.passwordHash(passwordEncoder.encode("123456789"))
								.role("ADMIN")
								.build());

				log.info("✔ Módulos iniciales creados");
			} else {
				log.info("ℹ Módulos ya existen, no se insertan");
			}
		};
	}
	@Bean
	CommandLineRunner initCourseDatabase(CourseRepository courseRepository) {
		return args -> {

			if (courseRepository.count() == 0) {
				//fullstack
				courseRepository.save(
						CourseEntity.builder()
								.title("Fullstack con Java y Angular")
								.description("Desarrollo completo de aplicaciones web usando Spring Boot y Angular")
								.module("Fullstack")
								.duration(40)
								.build()
				);

				courseRepository.save(
						CourseEntity.builder()
								.title("Arquitectura Fullstack Moderna")
								.description("Patrones, buenas prácticas y arquitectura para aplicaciones fullstack")
								.module("Fullstack")
								.duration(30)
								.build()
				);

				//apis
				courseRepository.save(
						CourseEntity.builder()
								.title("APIs REST con Spring Boot")
								.description("Diseño y construcción de APIs RESTful con Spring Boot")
								.module("APIs e Integraciones")
								.duration(25)
								.build()
				);

				courseRepository.save(
						CourseEntity.builder()
								.title("Integraciones SOAP y REST")
								.description("Consumo e integración de servicios SOAP y REST en Java")
								.module("APIs e Integraciones")
								.duration(20)
								.build()
				);
				//Cloud
				courseRepository.save(
						CourseEntity.builder()
								.title("Introducción a Cloud Computing")
								.description("Fundamentos de la computación en la nube y modelos de servicio")
								.module("Cloud")
								.duration(20)
								.build()
				);

				courseRepository.save(
						CourseEntity.builder()
								.title("AWS para Desarrolladores")
								.description("Uso de servicios AWS para aplicaciones backend modernas")
								.module("Cloud")
								.duration(35)
								.build()
				);
				//data
				courseRepository.save(
						CourseEntity.builder()
								.title("Fundamentos de Data Engineering")
								.description("Bases del procesamiento y modelado de datos a gran escala")
								.module("Data Engineer")
								.duration(30)
								.build()
				);

				courseRepository.save(
						CourseEntity.builder()
								.title("ETL y Pipelines de Datos")
								.description("Diseño e implementación de pipelines ETL para análisis de datos")
								.module("Data Engineer")
								.duration(28)
								.build()
				);

				log.info("✔ Módulos iniciales creados");
			} else {
				log.info("ℹ Módulos ya existen, no se insertan");
			}
		};
	}

}
