package pxl.be.services;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigServer
public class ConfigServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServiceApplication.class).run(args);
    }
}
