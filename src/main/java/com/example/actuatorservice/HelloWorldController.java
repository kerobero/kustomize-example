package com.example.actuatorservice;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/hello-world")
	@ResponseBody
	public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/healthz")
	@ResponseBody
	public String healthz() {
		return "OK";
	}

	@GetMapping("/file")
	@ResponseBody
	public String createFile(@RequestParam(name="name") String name) {
		File newFile = new File(name);
		try {
			boolean success_create = newFile.createNewFile();
			boolean success_delete = newFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "OK";
	}
}
