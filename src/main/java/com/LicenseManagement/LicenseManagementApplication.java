package com.LicenseManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@SpringBootApplication
public class LicenseManagementApplication {
	static final String FILECONFIGURATION = "config.yml";

	public static void main(String[] args) {
		Path path = FileSystems.getDefault().getPath(FILECONFIGURATION);
		if (!Files.exists(path)) {
			System.out.println("Not detect file configuration, creating file... ");
			setup();
			System.out.println("File configuration created successfully. Now please modify it");
		} else {
			SpringApplication.run(LicenseManagementApplication.class, args);
		}
	}
	
	private static void setup() {
		// Create a DumperOptions object to set the indentation and enable comments
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setIndent(2);
		options.setPrettyFlow(true);
		Yaml yaml = new Yaml(options);
		Map<String, Object> data1 = Map.of(
				"username", "idk",
				"password", "idk"
		);
		Map<String, Object> data2 = Map.of(
				"usernamemysql", "user",
				"passwordmysql", "admin",
				"mysqlhost", "localhost",
				"mysqlport", 3306,
				"defaultdatabase", "mydb"

		);
		String comment1 = "# Authentication when use restapi\n";
		String comment2 = "# Mysql setup\n";
		try {
			// Use FileWriter to write to the file
			try (FileWriter writer = new FileWriter(FILECONFIGURATION)) {
				// Add comments to the YAML file
				writer.write(comment1);

				// Dump the data structure to the YAML file
				yaml.dump(data1, writer);
				writer.write("\n");
				writer.write(comment2);
				yaml.dump(data2, writer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
