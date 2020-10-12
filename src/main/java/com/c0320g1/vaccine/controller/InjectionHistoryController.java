package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.*;
import com.c0320g1.vaccine.repository.AccountRepository;
import com.c0320g1.vaccine.repository.PatientRepository;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
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
    //    CREATE BY ANH ĐỨC
    @Autowired
    private VerifyTokenService verifyTokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VaccineService vaccineService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    AccountRepository accountRepository;


    //    Quân
//    @GetMapping("/injection-list")
//    public ResponseEntity<Page<InjectionHistoryDTO>> getListInjected(@RequestParam(name = "fullName", defaultValue = "") String fullName,
//                                                                     @RequestParam(name = "injected", defaultValue = "") String injected,
//                                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
//        Page<InjectionHistoryDTO> injectionHistoryDTO = injectionHistoryService.search(fullName, injected, page);
//        if (injectionHistoryDTO.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ResponseEntity.ok(injectionHistoryDTO);
//    }

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

    //    CREATE BY ANH ĐỨC
    @PostMapping("injection/verify")
    public ResponseEntity<Map<String, Object>> sendToken(@RequestBody String email) throws MessagingException {
        Map<String, Object> response = new HashMap<>();
        verifyTokenService.deleteAllByEmail(email);
        Random random = new Random();
        int number = random.nextInt(899999) + 100000;
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
    @PostMapping("injection/verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody Patient patient) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (!verifyTokenService.checkTokenVerify(patient.getEmail(), patient.getCode())) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("message", "Mã xác minh không chính xác hoặc đã hết hạn, vui lòng lấy mã xác minh mới");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "Mã xác minh không chính xác hoặc đã hết hạn, vui lòng lấy mã xác minh mới");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        verifyTokenService.deleteAllByEmail(patient.getEmail());
        response.put("status", HttpStatus.OK);
        response.put("message", "Xác minh thành công ");
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
        try {
            injectionHistory.setPatient(patientService.checkPatient(injectionHistory.getPatient()));
        } catch (Exception ex
        ) {
            System.out.println(ex);
        }
//        Kiểm tra xem bệnh nhân đã tồn tại hay chưa, nếu chưa sẽ tạo mới bệnh nhân vào database rồi trả về
        injectionHistory.setRegisterType("yêu cầu");
        injectionHistory.setIsInjected("chưa tiêm");
        injectionHistory.setReponseContent("chưa xác định");
        this.injectionHistoryService.save(injectionHistory);
        String email = injectionHistory.getPatient().getEmail();
        String text = "Bạn đã đăng ký tiêm chủng theo yêu cầu vào ngày " + injectionHistory.getInjectionDate().getDayOfMonth() + " tháng " +
                injectionHistory.getInjectionDate().getMonthValue() + " tại trung tâm tiêm chủng vắc xin C0320G1-Center - địa chỉ : " +
                "34 Cao Xuân Huy, Khuê trung, Cẩm Lệ, Đà nẵng. Khi đi vui lòng mang theo chứng minh thư, Xin cảm ơn!";
        try {
            this.emailService.sendSimpleHTMLMessage(email, "Đăng ký tiêm thành công", text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        response.put("status", HttpStatus.OK);
        response.put("message", "Đăng kí tiêm chủng theo yêu cầu thành công ! ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //An
    @GetMapping("/injection-add-history")
    public ResponseEntity<InjectionHistory> register(@RequestParam(value = "id") Long id,
                                                     @RequestParam(value = "vaccineId") Long vaccineId,
                                                     @RequestParam(value = "accountId") Long accountId,
                                                     @RequestParam(value = "injectionDate") String injectionDate) {
        InjectionHistory injectionHistory1 = new InjectionHistory();
        LocalDate dayNew = LocalDate.parse(injectionDate);
        LocalTime timenew = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(dayNew, timenew);

        injectionHistory1.setPatient(patientRepository.findById(id).orElse(null));
        injectionHistory1.setVaccine(vaccineRepository.findById(vaccineId).orElse(null));
        injectionHistory1.setAccount(accountRepository.findById(accountId).orElse(null));
        injectionHistory1.setRegisterType("định kỳ");
        injectionHistory1.setReponseContent("chưa xác định");
        injectionHistory1.setIsInjected("chưa tiêm");
        injectionHistory1.setInjectionDate(dateTime);
        injectionHistoryService.save(injectionHistory1);
        return ResponseEntity.ok(injectionHistory1);
    }


    //An
    @PostMapping("/injection-add")
    public ResponseEntity<InjectionHistory> addInjectionHistory(@RequestBody InjectionHistory injectionHistory) {
        injectionHistoryService.save(injectionHistory);
        return ResponseEntity.ok(injectionHistory);
    }

    // Thành Long
    @GetMapping("/account/injection-history/{accountId}")
    public ResponseEntity<Page<InjectionHistory>> getAllInjectionHistoryByAccountId(@PathVariable(value = "accountId") Long accountId,
                                                                                    @PageableDefault(value = 5) Pageable pageable) {
        Page<InjectionHistory> injectionHistories = injectionHistoryService.findInjectionHistoryByAccountId(accountId, pageable);
        return ResponseEntity.ok(injectionHistories);
    }

    //Tùng
    @GetMapping("/account/injection-history/get/{id}")
    public ResponseEntity<InjectionHistory> getInjectionHistory(@PathVariable Long id) {
        InjectionHistory injectionHistory = injectionHistoryService.findById(id);
        if (injectionHistory == null) {
            return new ResponseEntity<InjectionHistory>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<InjectionHistory>(injectionHistory, HttpStatus.OK);
    }

    //Tùng
    @PutMapping("/account/injection-history/reply/{id}")
    public ResponseEntity<InjectionHistory> replyInjection(@PathVariable Long id, @RequestBody InjectionHistory injectionHistory) {
        InjectionHistory injection = injectionHistoryService.findById(id);
        injection.setReponseContent(injectionHistory.getReponseContent());
        injectionHistoryService.save(injection);
        return ResponseEntity.ok().body(injection);
    }

    @GetMapping("/account/injection-history/vaccine")
    public ResponseEntity<List<Vaccine>> getApiOfVaccine() {
        List<Vaccine> vaccinesList = vaccineRepository.findAll();
        return new ResponseEntity<List<Vaccine>>(vaccinesList, HttpStatus.OK);
    }

    @GetMapping("/account/injection-history/account")
    public ResponseEntity<List<Account>> getApiOfAccount() {
        List<Account> accountList = accountRepository.findAll();
        return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
    }

    @GetMapping("/account/injection-history/patient")
    public ResponseEntity<List<Patient>> getApiOfPatient() {
        List<Patient> patientsList = patientRepository.findAll();
        return new ResponseEntity<List<Patient>>(patientsList, HttpStatus.OK);
    }
}
