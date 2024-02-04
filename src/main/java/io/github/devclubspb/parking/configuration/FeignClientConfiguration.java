package io.github.devclubspb.parking.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "io.github.devclubspb.parking.client")
public class FeignClientConfiguration {
}
