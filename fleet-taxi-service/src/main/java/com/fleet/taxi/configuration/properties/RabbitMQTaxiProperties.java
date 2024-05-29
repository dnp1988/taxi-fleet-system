package com.fleet.taxi.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQTaxiProperties {

    private String exchangeName;
    private RabbitMQBindingProperties status;
    private RabbitMQBindingProperties claim;
}
