package wg.app.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication


@EnableJpaRepositories(basePackages = {"wg.app"})
@EntityScan(basePackages = {"wg.app"})
@ComponentScan(basePackages = {"wg.app"})
public class WebApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebApp.class);

    }

}
