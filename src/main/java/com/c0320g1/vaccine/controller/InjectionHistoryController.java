package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.model.VerifyToken;
import com.c0320g1.vaccine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class InjectionHistoryController {

    @Autowired
    InjectionHistoryService injectionHistoryService;
    //    CREATE BY ANH ĐỨC
    @Autowired
    private PatientService patientService;
    @Autowired
    private VerifyTokenService verifyTokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VaccineService vaccineService;

    //    Quân
    @GetMapping("/injection-list")
    public ResponseEntity<Page<InjectionHistoryDTO>> getListInjected(@RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                                     @RequestParam(name = "injected", defaultValue = "") String injected,
                                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<InjectionHistoryDTO> injectionHistoryDTO = injectionHistoryService.search(fullName, injected, page);
        if (injectionHistoryDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(injectionHistoryDTO);
    }

    @PostMapping("injection/verify")
    public ResponseEntity<Map<String, Object>> sendToken(@RequestBody String email) throws MessagingException {
        Map<String, Object> response = new HashMap<>();
        verifyTokenService.deleteAllByEmail(email);
        Random random = new Random();
        int number = random.nextInt(999999);
        LocalDateTime timNow = LocalDateTime.now();
        VerifyToken verifyToken = new VerifyToken();
        verifyToken.setEmail(email);
        verifyToken.setCode(String.valueOf(number));
        verifyToken.setTimeCreate(timNow);
        verifyTokenService.save(verifyToken);
        String code = "Mã xác thực : " + String.valueOf(number);
        emailService.sendSimpleHTMLMessage(email, code, "");
        response.put("status", HttpStatus.OK);
        response.put("message", "Mã xác nhận đã được gửi về email của bạn, vui lòng kiểm tra email");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //    CREATE BY ANH ĐỨC
    @PostMapping("injection/registration")
    public ResponseEntity<Map<String, Object>> registration(@RequestBody InjectionHistory injectionHistory) {
        Map<String, Object> response = new HashMap<>();
        if (vaccineService.findById(injectionHistory.getVaccine().getId()) == null) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "Vắc xin này không tồn tại ");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (!verifyTokenService.checkTokenVerify(injectionHistory.getPatient().getEmail(), injectionHistory.getPatient().getCode())) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "Mã xác minh không chính xác hoặc đã hết hạn, vui lòng lấy mã xác nhận mới");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
//        Kiểm tra xem bệnh nhân đã tồn tại hay chưa, nếu chưa sẽ tạo mới bệnh nhân vào database rồi trả về
        injectionHistory.setPatient(patientService.checkPatient(injectionHistory.getPatient()));
        this.injectionHistoryService.save(injectionHistory);
        response.put("status", HttpStatus.OK);
        response.put("message", "Đăng kí tiêm chủng theo yêu cầu thành công ! ");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    // Thành Long
    @GetMapping("/account/injection-history/{accountId}")
    public ResponseEntity<Page<InjectionHistory>> getAllInjectionHistoryByAccountId (@PathVariable(value = "accountId") Long accountId,
                                                                                     @PageableDefault(value = 5) Pageable pageable){
        Page<InjectionHistory> injectionHistories = injectionHistoryService.findInjectionHistoryByAccountId(accountId, pageable);
        return ResponseEntity.ok(injectionHistories);
    }
}
