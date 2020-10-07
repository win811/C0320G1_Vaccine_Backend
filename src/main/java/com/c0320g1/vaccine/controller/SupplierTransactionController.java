package com.c0320g1.vaccine.controller;


import com.c0320g1.vaccine.exception.ViolatedException;
import com.c0320g1.vaccine.model.SupplierTransaction;
import com.c0320g1.vaccine.service.SupplierTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class SupplierTransactionController {

    @Autowired
    private SupplierTransactionService supplierTransactionService;


    @GetMapping("/admin/supplier-transaction")
    public ResponseEntity<Page<SupplierTransaction>> getAllTransaction(
            @RequestParam(name="tradingCode", defaultValue = "") String tradingCode,
            @RequestParam(name = "vaccineCode", defaultValue = "") String vaccineCode,
            @RequestParam(name = "billCode", defaultValue = "") String billCode,
            @RequestParam(name = "vaccineType", defaultValue = "") String vaccineType,
            @RequestParam(name = "supplier", defaultValue = "") String supplier,
            @RequestParam("page") int currentPage
    ){
        Page<SupplierTransaction> supplierTransactions;
        Specification<SupplierTransaction> search = supplierTransactionService.getFilter(tradingCode, vaccineCode, billCode, vaccineType,supplier);
        if (search != null) {
            supplierTransactions = supplierTransactionService.findTransactionByCriteria(search, currentPage);
        }else{
            supplierTransactions = supplierTransactionService.findAll(currentPage);
        }
        return ResponseEntity.ok(supplierTransactions);
    }


    @PostMapping("/admin/supplier-transaction/create")
    public ResponseEntity<SupplierTransaction> createNewTransaction(@Valid @RequestBody SupplierTransaction supplierTransaction, BindingResult bindingResult) throws ViolatedException {
        if (bindingResult.hasErrors()){
            throw new ViolatedException(bindingResult);
        }
        supplierTransaction.setIsDelete(Boolean.FALSE);
        supplierTransactionService.save(supplierTransaction);
        return new ResponseEntity<SupplierTransaction>(supplierTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/admin/supplier-transaction/get/{id}")
    public ResponseEntity<SupplierTransaction> getTransaction(@PathVariable Long id){
        SupplierTransaction supplierTransaction = supplierTransactionService.findById(id);
        if(supplierTransaction == null){
            return new ResponseEntity<SupplierTransaction>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<SupplierTransaction>(supplierTransaction, HttpStatus.OK);
    }

    @PutMapping("/admin/supplier-transaction/update/{id}")
    public ResponseEntity<SupplierTransaction> updateTransaction(@PathVariable Long id, @RequestBody SupplierTransaction supplierUpdate){
        SupplierTransaction supplierTransaction = supplierTransactionService.findById(id);
        supplierTransaction.setTradingCode(supplierUpdate.getTradingCode());
        supplierTransaction.setVaccineCode(supplierUpdate.getVaccineCode());
        supplierTransaction.setVaccineType(supplierUpdate.getVaccineType());
        supplierTransaction.setAmount(supplierUpdate.getAmount());
        supplierTransaction.setSupplier(supplierUpdate.getSupplier());
        supplierTransaction.setTradingDate(supplierUpdate.getTradingDate());
        supplierTransaction.setPrice(supplierUpdate.getPrice());
        supplierTransaction.setTotal(supplierUpdate.getTotal());
        supplierTransaction.setIsDelete(supplierUpdate.getIsDelete());
        supplierTransactionService.save(supplierTransaction);
        return ResponseEntity.ok().body(supplierTransaction);
    }

    @PutMapping("/admin/supplier-transaction/delete/{id}")
    public Map<String, Boolean> deleteTransaction(@PathVariable(value = "id") Long id){
        SupplierTransaction supplierTransaction = supplierTransactionService.findById(id);
        supplierTransactionService.delete(supplierTransaction);
        supplierTransactionService.save(supplierTransaction);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
