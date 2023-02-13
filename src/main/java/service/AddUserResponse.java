package service;

import lombok.Getter;
import lombok.Setter;
import payload.ServiceStatus;
import payload.UserInfo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder ={
        "status",
        "userInfo"
})

@XmlRootElement(name = "AddUserResponse")

@Getter
@Setter
public class AddUserResponse {
    @XmlElement(required = true)
    private ServiceStatus status;
    @XmlElement(required = true)
    private UserInfo userInfo;
}
