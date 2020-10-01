package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.Employee;
import com.c0320g1.vaccine.repository.EmployeeRepository;
import com.c0320g1.vaccine.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
     Pageable pageableDefault = PageRequest.of(0,4);

    //    Quân
    @Override
    public List<Employee> findAll() {
            return employeeRepository.findAllByIsDeleteFalse();
    }

    //    Quân
    @Override
    public void save(Employee employee) {
         employeeRepository.save(employee);
    }

    //    Quân
    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findAllByIdAndIsDeleteFalse(id);
        employee.setIsDelete(true);
       employeeRepository.save(employee);
    }

    //    Quân
    @Override
    public Page<Employee> search(String code, String fullName, String position, int page) {
        if(page>0){
            Pageable pageable = PageRequest.of(--page,4);
            return employeeRepository.findByCodeContainingAndFullNameContainingAndPositionContainingAndIsDeleteFalse(code,fullName,position,pageable);
        }
        return employeeRepository.findByCodeContainingAndFullNameContainingAndPositionContainingAndIsDeleteFalse(code,fullName,position,pageableDefault);

    }
}
