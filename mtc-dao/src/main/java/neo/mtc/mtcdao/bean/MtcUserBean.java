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
public class MtcUserBean {

    @JsonProperty(value = "mu_id")
    private String mubId;

    @JsonProperty(value = "mub_user_id")
    private String mubUserId;

    @JsonProperty(value = "mub_firstname")
    private String mubFirstname;

    @JsonProperty(value = "mub_lastname")
    private String mubLastname;

    @JsonProperty(value = "mub_username")
    private String mubUsername;

    @JsonProperty(value = "mub_password")
    private String mubPassword;

    @JsonProperty(value = "mub_email")
    private String mubEmail;

    @JsonProperty(value = "mub_phone")
    private String mubPhone;

}
