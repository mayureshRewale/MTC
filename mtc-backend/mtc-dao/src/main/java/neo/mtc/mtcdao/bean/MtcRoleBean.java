package neo.mtc.mtcdao.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MtcRoleBean {

    @JsonProperty(value = "mrb_id")
    private String mrbId;

    @JsonProperty(value = "mrb_name")
    private String mrbName;

    @JsonProperty(value = "mrb_description")
    private String mrbDescription;

}
