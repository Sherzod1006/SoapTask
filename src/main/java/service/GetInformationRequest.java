package service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "password",
        "username",
        "serviceId",
})
@XmlRootElement(name = "GetInformationRequest")
@Getter
@Setter
public class GetInformationRequest {
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private long serviceId;
}
