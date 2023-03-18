package neo.mtc.mtcuser.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import neo.mtc.mtcdao.bean.ServiceResponseBean;
import neo.mtc.mtcdao.entity.MtcUserEntity;
import neo.mtc.mtcdao.repository.MtcUserRepository;
import neo.mtc.mtcdao.response.MtcRegisterResponse;
import neo.mtc.mtcdao.response.MtcUserDetailResponse;
import neo.mtc.mtcuser.serviceInterface.IMtcUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class MtcUserDetailsServiceImpl implements IMtcUserDetailsService {

    @Autowired
    MtcUserRepository mtcUserRepository;

    @Override
    public ServiceResponseBean getUserDetails(String userId) {
        ServiceResponseBean serviceResponseBean;
        log.info("Request received in getting user details");

        try{
            MtcUserEntity mtcUserEntity = mtcUserRepository.findByMuUserId(userId);
            if(Objects.nonNull(mtcUserEntity)){
                MtcUserDetailResponse mtcUserDetailResponse = MtcUserDetailResponse.builder()
                        .qubUserId(mtcUserEntity.getMuUserId())
                        .qubFirstname(mtcUserEntity.getMuFirstname())
                        .qubLastname(mtcUserEntity.getMuLastname())
                        .qubUsername(mtcUserEntity.getMuPhone())
                        .qubPhone(mtcUserEntity.getMuPhone())
                        .qubEmail(mtcUserEntity.getMuEmail())
                        .build();

                serviceResponseBean = ServiceResponseBean.builder()
                        .status(Boolean.TRUE)
                        .data(mtcUserDetailResponse)
                        .message("User registered Successfully")
                        .build();
            }else {
                serviceResponseBean = ServiceResponseBean.builder()
                        .status(Boolean.FALSE)
                        .error("Unable to get user details... Please try again after sometime")
                        .build();
            }
        }catch (Exception e){
            log.error("Exception in getting user details : {}", e.getMessage());
            serviceResponseBean = ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .error("Unable to get user details... Please try again after sometime")
                    .build();
        }
        return serviceResponseBean;
    }

}
