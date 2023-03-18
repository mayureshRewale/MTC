package neo.mtc.mtcuser.controller;

import neo.mtc.mtcdao.bean.ServiceResponseBean;
import neo.mtc.mtcuser.serviceInterface.IMtcUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class MtcUserDetailsController {

    @Autowired
    IMtcUserDetailsService iMtcUserDetailsService;

    @GetMapping("/user-details")
    public ServiceResponseBean getUserDetails(@RequestParam("user_id") String userId){
        return iMtcUserDetailsService.getUserDetails(userId);
    }

}
