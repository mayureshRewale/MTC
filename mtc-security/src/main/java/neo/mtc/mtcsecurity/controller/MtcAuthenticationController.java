package neo.mtc.mtcsecurity.controller;

import neo.mtc.mtcdao.bean.ServiceResponseBean;
import neo.mtc.mtcdao.request.MtcLoginRequest;
import neo.mtc.mtcdao.request.MtcUserDetailRequest;
import neo.mtc.mtcsecurity.serviceInterface.IMtcAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security/auth")
public class MtcAuthenticationController {

    @Autowired
    IMtcAuthenticationService iMtcAuthenticationService;

    @PostMapping("/get-otp")
    public ResponseEntity<ServiceResponseBean> getOtp(@RequestParam("otp_type") String otpType,
                                                      @RequestParam("phone") String phone,
                                                      @RequestParam(value = "is_resend") Boolean isResend){
        return ResponseEntity.ok(iMtcAuthenticationService.getOtp(otpType, phone, isResend));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ServiceResponseBean> verifyOtp(@RequestParam("otp_type") String otpType,
                                                         @RequestParam("otp") String otp,
                                                         @RequestParam("phone") String phone){
        return ResponseEntity.ok(iMtcAuthenticationService.verifyOtp(otp, phone, otpType));
    }

    @PostMapping("/register")
    public ResponseEntity<ServiceResponseBean> register(@RequestBody MtcUserDetailRequest request){
        return ResponseEntity.ok(iMtcAuthenticationService.register(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<ServiceResponseBean> registerAdmin(@RequestBody MtcUserDetailRequest request){
        return ResponseEntity.ok(iMtcAuthenticationService.registerAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ServiceResponseBean> register(@RequestBody MtcLoginRequest request){
        return ResponseEntity.ok(iMtcAuthenticationService.authenticate(request));
    }

}
