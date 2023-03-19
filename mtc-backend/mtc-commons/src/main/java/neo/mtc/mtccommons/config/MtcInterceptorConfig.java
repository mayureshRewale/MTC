package neo.mtc.mtccommons.config;

import neo.mtc.mtccommons.interceptor.MtcApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MtcInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    MtcApiInterceptor mtcApiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mtcApiInterceptor);
    }

}
