package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;


public interface EmployeeService {
    //    Quân
    List<Employee> findAll();
    //    Quân
    void save (Employee employee);
    //    Quân
    void delete (Long id);
    //    Quân
    Page<Employee> search (String code,String fullName,String position,int page);
}
