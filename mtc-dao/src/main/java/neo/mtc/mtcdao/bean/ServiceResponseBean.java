package neo.mtc.mtcdao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseBean {

    private Integer code;

    private Boolean status;

    private Object data;

    private String message;

    private String error;

}
