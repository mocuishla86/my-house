package org.mocuishla.myhouse.configuration;

import org.mocuishla.myhouse.adapters.output.airconditioner.FakeAirConditioner;
import org.mocuishla.myhouse.adapters.output.persistence.postgres.JpaActionRepository;
import org.mocuishla.myhouse.adapters.output.persistence.postgres.PostgresActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdaptersConfiguration {

    @Bean
    public AirConditioner getAirConditioner() {
        return new FakeAirConditioner();
    }


    @Bean
    public ActionRepository getActionRepository(JpaActionRepository jpaActionRepository) {
        return new PostgresActionRepository(jpaActionRepository);
    }

}
