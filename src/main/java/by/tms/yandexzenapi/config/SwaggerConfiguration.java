package by.tms.yandexzenapi.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .securitySchemes(singletonList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("by.tms.yandexzenapi.rest"))
				.paths(PathSelectors.any())
				.build();

	}

    private ApiKey apiKey() {
			return new ApiKey("SECURITY_REFERENCE", "Authorization", SecurityScheme.In.HEADER.name());
    }

    private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("YandexZen API")
				.description("Spring Boot REST API reference for developers")
				.version("1.0.0")
				.build();
	}
}
