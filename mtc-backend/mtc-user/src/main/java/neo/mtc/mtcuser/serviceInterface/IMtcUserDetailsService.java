package neo.mtc.mtcuser.serviceInterface;

import neo.mtc.mtcdao.bean.ServiceResponseBean;

public interface IMtcUserDetailsService {

    ServiceResponseBean getUserDetails(String userId);

}
