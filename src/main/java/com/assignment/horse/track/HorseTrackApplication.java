package com.assignment.horse.track;

import com.assignment.horse.track.service.DataLoaderService;
import com.assignment.horse.track.service.ExecuteCommandFromUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class HorseTrackApplication implements CommandLineRunner {

	@Autowired
	private ConfigurableApplicationContext context;

	@Autowired
	DataLoaderService dataLoaderService;

	@Autowired
	ExecuteCommandFromUserService executeCommandFromUserService;
	public static void main(String[] args) {
		SpringApplication.run(HorseTrackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataLoaderService.initializeDataInDB();
		Scanner sc = new Scanner(System.in);
		String commandString = "";
		while(!Arrays.asList("Q","q").contains(( commandString = sc.nextLine()))){
			executeCommandFromUserService.executeCommand(commandString);
		}
		System.exit(SpringApplication.exit(context));
	}
}
