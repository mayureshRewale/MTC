package neo.mtc.mtcsecurity.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import neo.mtc.mtcdao.bean.ServiceResponseBean;
import neo.mtc.mtcdao.entity.MtcRoleEntity;
import neo.mtc.mtcdao.entity.MtcUserEntity;
import neo.mtc.mtcdao.repository.MtcRoleRepository;
import neo.mtc.mtcdao.repository.MtcUserRepository;
import neo.mtc.mtcdao.request.MtcLoginRequest;
import neo.mtc.mtcdao.request.MtcUserDetailRequest;
import neo.mtc.mtcdao.response.MtcLoginResponse;
import neo.mtc.mtcdao.response.MtcRegisterResponse;
import neo.mtc.mtcdao.utils.CommonUtils;
import neo.mtc.mtcsecurity.config.MtcJwtService;
import neo.mtc.mtcsecurity.serviceInterface.IMtcAuthenticationService;
import neo.mtc.mtcsecurity.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class MtcAuthenticationServiceImpl implements IMtcAuthenticationService {

    @Autowired
    MtcUserRepository mtcUserRepository;

    @Autowired
    MtcRoleRepository mtcRoleRepository;

    @Autowired
    MtcJwtService mtcJwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public ServiceResponseBean register(MtcUserDetailRequest request) {
        log.info("Request received in register : {}", request);
        try{
            if(Objects.nonNull(mtcUserRepository.findByMuUsername(request.getPhone()))){
                log.info("User already exists");
                return ServiceResponseBean.builder()
                        .status(Boolean.FALSE)
                        .error("User with mobile number already exists")
                        .build();
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            MtcUserEntity user = MtcUserEntity.builder()
                    .muFirstname(request.getFirstname())
                    .muLastname(request.getLastname())
                    .muUsername(request.getPhone())
                    .muPhone(request.getPhone())
                    .muEmail(request.getEmail())
                    .muPassword(encoder.encode(request.getPassword()))
                    .muUserId(CommonUtils.generateRandomString(30))
                    .mtcRoles(getUserRole())
                    .muUpdatedDate(LocalDateTime.now())
                    .build();
            user = mtcUserRepository.save(user);

            String jwtToken = mtcJwtService.generateToken(user);
            MtcRegisterResponse mtcRegisterResponse = MtcRegisterResponse.builder()
                    .userId(user.getMuUserId())
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .username(request.getPhone())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .userId(user.getMuUserId())
                    .accessToken(jwtToken)
                    .build();

            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(mtcRegisterResponse)
                    .message("User registered Successfully")
                    .build();
        }catch (Exception e){
            log.error("Exception in register : {}", e.getMessage());
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .error("Unable to register... Please try again after sometime")
                    .build();
        }
    }

    @Override
    public ServiceResponseBean registerAdmin(MtcUserDetailRequest request) {
        log.info("Request received in registerAdmin");
        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            MtcUserEntity user = MtcUserEntity.builder()
                    .muFirstname(request.getFirstname())
                    .muLastname(request.getLastname())
                    .muUsername(request.getPhone())
                    .muPhone(request.getPhone())
                    .muEmail(request.getEmail())
                    .muPassword(encoder.encode(request.getPassword()))
                    .muUserId(CommonUtils.generateRandomString(30))
                    .mtcRoles(getAdminRole())
                    .muUpdatedDate(LocalDateTime.now())
                    .build();
            user = mtcUserRepository.save(user);

            String jwtToken = mtcJwtService.generateToken(user);
            MtcRegisterResponse qbRegisterResponse = MtcRegisterResponse.builder()
                    .userId(user.getMuUserId())
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .username(request.getPhone())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .userId(user.getMuUserId())
                    .accessToken(jwtToken)
                    .build();

            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(qbRegisterResponse)
                    .message("Admin User registered Successfully")
                    .build();
        }catch (Exception e){
            log.error("Exception in registerAdmin : {}", e.getMessage());
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .error("Unable to register... Please try again after sometime")
                    .build();
        }
    }

    @Override
    public ServiceResponseBean authenticate(MtcLoginRequest request) {
        log.info("Request received in authenticate");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            MtcUserEntity qbUser = mtcUserRepository.findByMuUsername(request.getUsername());
            String jwtToken = mtcJwtService.generateToken(qbUser);

            MtcLoginResponse qbLoginResponse = MtcLoginResponse.builder()
                    .userId(qbUser.getMuUserId())
                    .firstname(qbUser.getMuFirstname())
                    .lastname(qbUser.getMuLastname())
                    .username(qbUser.getMuPhone())
                    .phone(qbUser.getMuPhone())
                    .email(qbUser.getMuEmail())
                    .userId(qbUser.getMuUserId())
                    .accessToken(jwtToken)
                    .build();

            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(qbLoginResponse)
                    .message("User logged In Successfully")
                    .build();
        }catch (Exception e){
            log.error("Exception in authenticate : {}", e.getMessage());
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .error("Unable to login... Please try again after sometime")
                    .build();
        }
    }

    @Override
    public ServiceResponseBean getOtp(String otpType, String phone, Boolean isResend) {
        log.info("Request received in getOtp");
        try{
            if(Objects.isNull(isResend) || !isResend){
                setOtp(otpType, phone);
            }else if(isResend){
                try{
                    redisUtils.deleteKey(otpType.toUpperCase() + "_" + phone);
                    setOtp(otpType, phone);
                }catch (Exception e){
                    log.error("Exception in getting redis key : {}", e.getMessage());
                    setOtp(otpType, phone);
                }
            }
            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .message("OTP send successfully")
                    .build();
        }catch (Exception e){
            log.error("Exception in getOtp : {}", e.getMessage());
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .error("Unable to send otp... Please try again after sometime")
                    .build();
        }
    }

    @Override
    public ServiceResponseBean verifyOtp(String otp, String phone, String otpType) {
        log.info("Request received in verifyOtp");
        try{
            String redisKey = otpType + "_" +phone;
            String redisOtp = redisUtils.getValue(redisKey);
            if(Objects.nonNull(redisOtp) && redisOtp.equals(otp)){
                redisUtils.deleteKey(redisKey);
                return ServiceResponseBean.builder()
                        .status(Boolean.TRUE)
                        .data(Map.of("isOtpVerified", Boolean.TRUE))
                        .message("OTP Verified")
                        .build();
            }else{
                return ServiceResponseBean.builder()
                        .status(Boolean.FALSE)
                        .data(Map.of("isOtpVerified", Boolean.FALSE))
                        .error("Invalid OTP")
                        .build();
            }
        }catch(Exception e){
            log.error("Exception in verifyOtp : {}", e.getMessage());
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .error("Unable to verify otp... Please try again after sometime")
                    .build();
        }
    }

    private void setOtp(String otpType, String phone) {
        String redis_key = otpType.toUpperCase() + "_" + phone;
        String otp = CommonUtils.generateRandomInt(6);
        redisUtils.setValue(redis_key, otp, 300);
        log.info("OTP : {}", otp);
    }

    private Set<MtcRoleEntity> getUserRole() {
        MtcRoleEntity qbRole = mtcRoleRepository.findByMrName("ROLE_USER");
        return Set.of(qbRole);
    }

    private Set<MtcRoleEntity> getAdminRole() {
        MtcRoleEntity qbRole = mtcRoleRepository.findByMrName("ROLE_ADMIN");
        return Set.of(qbRole);
    }
}
