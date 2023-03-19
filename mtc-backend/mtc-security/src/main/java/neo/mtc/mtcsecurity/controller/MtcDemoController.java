package neo.mtc.mtcsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/security/demo")
public class MtcDemoController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet(){
        return ResponseEntity.ok("Greetings !!!!");
    }

}
