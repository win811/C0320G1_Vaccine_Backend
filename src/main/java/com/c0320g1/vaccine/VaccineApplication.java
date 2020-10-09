package com.c0320g1.vaccine;

import com.c0320g1.vaccine.controller.VaccineController;
import com.c0320g1.vaccine.service.Impl.VaccineServiceImpl;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VaccineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaccineApplication.class, args);
    }

    @Bean
    public VaccineServiceImpl getVaccineServiceImpl() {
        return new VaccineServiceImpl();
    }

//    @Bean(name = "vaccine bean tạo tay ")
//    public VaccineController getVaccineController() {
//        return new VaccineController(this.getVaccineServiceImpl());
//    }
}
