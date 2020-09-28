package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.JwtRequest;
import com.c0320g1.vaccine.dto.JwtResponse;
import com.c0320g1.vaccine.model.Account;
import com.c0320g1.vaccine.model.ConfirmationToken;
import com.c0320g1.vaccine.repository.AccountRepository;
import com.c0320g1.vaccine.repository.ConfirmationTokenRepository;
import com.c0320g1.vaccine.security.JwtTokenUtil;
import com.c0320g1.vaccine.security.MyUserDetailsService;
import com.c0320g1.vaccine.service.AccountService;
import com.c0320g1.vaccine.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private MyUserDetailsService userDetailServiceImpl;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/hello")
    public String getHello() {
        return "Hello";
    }

    //creator : tien dang ki tai khoan
    @PostMapping(value = "/register")
    public ResponseEntity<Account> registration(@RequestBody Account account) {
        Account existingAccount = accountService.findAccountByEmail(account.getEmail());
        if (existingAccount != null) {
            return new ResponseEntity<>(account, HttpStatus.NOT_FOUND);
        } else {
            String password = passwordEncoder.encode(account.getPassword());
            account.setPassword(password);
            accountService.saveAccount(account);
            ConfirmationToken confirmationToken = new ConfirmationToken(account);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(account.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("quangtien14dt1bkdn@gmail.com");
            mailMessage.setText("To confirm your account, please click here: "
                    + "http://localhost:8082/api/v1/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
        }
        accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    //creator: ham xac nhan kich hoat email
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ModelAndView modelAndView;
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            Account account = accountService.findAccountByEmail(token.getAccount().getEmail());
            account.setConfirmStatus(true);
            accountService.saveAccount(account);
            modelAndView = new ModelAndView("accountVerified");
        } else {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("message", "The link is invalid or broken");
        }
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        Account accountLogin = accountService.findAccountByUserName(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtToken, accountLogin.getId(), accountLogin.getFullName(), userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
