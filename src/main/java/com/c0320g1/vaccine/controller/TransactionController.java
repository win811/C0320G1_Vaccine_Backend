package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.TransactionPatientDTO;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.service.TransactionPatientService;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionPatientService transactionPatientService;

    @Autowired
    VaccineService vaccineService;

    /**
     * Creator: Duy
     */
    @GetMapping("/admin/transaction/patient")
    public ResponseEntity<Page<TransactionPatientDTO>> getAllTransactionPatient(
            @RequestParam(defaultValue = "0") Long vaccineId,
            @RequestParam(defaultValue = "") String accountName,
            @RequestParam(defaultValue = "1") int page) {
        Page<TransactionPatientDTO> data = transactionPatientService.getAllTransaction(vaccineId, accountName, page);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * Creator: Duy
     */
    @DeleteMapping("/admin/transaction/patient")
    public ResponseEntity<?> deleteTransactionPatient(@RequestParam(required = true) Long id) throws EmptyResultDataAccessException  {
        transactionPatientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Creator: Duy
     */
    @PutMapping("/admin/transaction/patient")
    public ResponseEntity<?> updateTransactionPatient(@RequestBody TransactionPatientDTO transactionPatientDTO) throws IllegalArgumentException  {
        transactionPatientService.updateTransaction(transactionPatientDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * Creator: Duy
     */
    @GetMapping("/admin/vaccine")
    public ResponseEntity<List<Vaccine>> updateTransactionPatient() {
        List<Vaccine> vaccineList = vaccineService.getAllVaccine();
        return new ResponseEntity<>(vaccineList, HttpStatus.OK);
    }

}
