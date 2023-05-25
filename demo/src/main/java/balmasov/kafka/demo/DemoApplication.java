package balmasov.kafka.demo;

import balmasov.kafka.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class DemoApplication {

	private final Producer producer;

	@Autowired
	DemoApplication(Producer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(DemoApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			String fileName = "D:\\IdeaProjects\\kafka-example\\demo\\src\\main\\resources\\file.txt";
			File file = new File(fileName);

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			String line;

			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			while((line = br.readLine()) != null) {
				producer.sendMessage(line);
			}
			stopWatch.stop();
			producer.sendMessage(String.format("File has been finished to read for time %s", stopWatch.getTotalTimeSeconds()));
		};
	}
}
