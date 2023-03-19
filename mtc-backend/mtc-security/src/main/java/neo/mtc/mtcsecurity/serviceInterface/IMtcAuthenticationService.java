package neo.mtc.mtcsecurity.serviceInterface;


import neo.mtc.mtcdao.bean.ServiceResponseBean;
import neo.mtc.mtcdao.request.MtcLoginRequest;
import neo.mtc.mtcdao.request.MtcUserDetailRequest;

public interface IMtcAuthenticationService {
    ServiceResponseBean register(MtcUserDetailRequest request);

    ServiceResponseBean registerAdmin(MtcUserDetailRequest request);

    ServiceResponseBean authenticate(MtcLoginRequest request);

    ServiceResponseBean getOtp(String otpType, String phone, Boolean isResend);

    ServiceResponseBean verifyOtp(String otp, String phone, String otpType);
}
