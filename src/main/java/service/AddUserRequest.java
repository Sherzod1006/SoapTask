package service;

import lombok.Getter;
import lombok.Setter;
import payload.UserInfo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "userInfo"
})
@XmlRootElement(name = "addUserRequest")
@Getter
@Setter
public class AddUserRequest {

    @XmlElement(required = true)
    private UserInfo userInfo;

    public UserInfo getUserInfo(){
        return userInfo;
    }
}
