package com.codedecode.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codedecode.demo.index.Indexer;


@SpringBootApplication
public class CodeDecodeCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeDecodeCrudApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner buildIndex(Indexer indexer) {
		return (ApplicationArguments args) -> {
			indexer.indexPersistedData("com.codedecode.demo.entity.Posting");
		};
	}

}