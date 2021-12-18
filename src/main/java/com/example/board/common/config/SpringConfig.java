package com.example.board.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    /**
     * DTO <-> Entity 변환용 ModelMapper Bean 등록
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
