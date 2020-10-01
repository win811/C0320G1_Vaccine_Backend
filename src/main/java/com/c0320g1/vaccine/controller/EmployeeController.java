package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.exception.ViolatedException;
import com.c0320g1.vaccine.model.Employee;
import com.c0320g1.vaccine.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
   private EmployeeService employeeService;

//    Quân
   @RequestMapping("employee")
    public ResponseEntity<List<Employee>> getEmployeeList(){
      List<Employee> employeeList = employeeService.findAll();
      if(employeeList.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }else {
         return new ResponseEntity<>(employeeList,HttpStatus.OK);
      }
   }

    //    Quân
   @PutMapping("employee/update")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult)
           throws Exception{
     if(bindingResult.hasFieldErrors()){
       throw new ViolatedException(bindingResult);
     }
     try {
          employeeService.save(employee);
     }catch (Exception ex){
         throw new Exception("Lỗi không thể lưu dữ liệu");
     }
     return ResponseEntity.ok(employee);
   }

    //    Quân
   @PostMapping("employee/create")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody Employee employee,BindingResult bindingResult)
           throws Exception{
       if(bindingResult.hasFieldErrors()){
           throw new ViolatedException(bindingResult);
       }
       try{
           employeeService.save(employee);
       }catch (Exception ex){
           throw new Exception("Lỗi không thể lưu dữ liệu");
       }
       return new ResponseEntity<>(HttpStatus.CREATED);
   }

    //    Quân
   @DeleteMapping("employee/delete/{id}")
    public ResponseEntity<Void> removeEmployee(@PathVariable(name = "id") Long id){
       employeeService.delete(id);
       return  new ResponseEntity<>(HttpStatus.OK);
   }

    //    Quân
   @GetMapping("/employee/search")
    public ResponseEntity<Page<Employee>>  getSearchEmployee(@RequestParam(name = "code",required = false,defaultValue = "")String code ,
                                                             @RequestParam(name ="fullName",required = false,defaultValue = "")String fullName,
                                                             @RequestParam(name = "position",required = false,defaultValue = "")String position,
                                                             @RequestParam(name = "page",required = false,defaultValue = "0") int page) {
       Page<Employee> employees = employeeService.search(code,fullName,position,page);
       if(employees.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return ResponseEntity.ok(employees);
   }

}
