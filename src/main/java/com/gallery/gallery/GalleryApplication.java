package com.gallery.gallery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gallery.gallery.model.User;
import com.gallery.gallery.repository.UserReposistory;

@SpringBootApplication
public class GalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserReposistory userReposistory){
		return args ->{
			User user = new User();
			user.setId(0);
			user.setName("admin");
			user.setPassword("admin");
			userReposistory.save(user);
		};
	}

}
