package com.dulanga.cal.cal.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bean {

    @org.springframework.context.annotation.Bean
    @Qualifier("modelMapper")
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
