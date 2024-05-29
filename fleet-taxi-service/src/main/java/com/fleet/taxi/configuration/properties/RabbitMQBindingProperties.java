package com.fleet.taxi.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQBindingProperties {

    private String queueName;
    private String routingKey;
}
