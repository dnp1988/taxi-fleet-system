package com.fleet.taxi.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {

    private RabbitMQBookingProperties booking;
    private RabbitMQTaxiProperties taxi;
}
