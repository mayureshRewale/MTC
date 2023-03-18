package neo.mtc.mtcdao.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MtcUserDetailResponse {

    @JsonProperty(value = "id")
    private String qubId;

    @JsonProperty(value = "user_id")
    private String qubUserId;

    @JsonProperty(value = "firstname")
    private String qubFirstname;

    @JsonProperty(value = "lastname")
    private String qubLastname;

    @JsonProperty(value = "username")
    private String qubUsername;

    @JsonProperty(value = "password")
    private String qubPassword;

    @JsonProperty(value = "email")
    private String qubEmail;

    @JsonProperty(value = "phone")
    private String qubPhone;

}
