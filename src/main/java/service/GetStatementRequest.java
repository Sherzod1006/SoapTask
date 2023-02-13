package service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "password",
        "username",
        "timeStamp"
})
@XmlRootElement(name = "ChangePasswordRequest")
@Getter
@Setter
public class GetStatementRequest {
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String username;
    @XmlElement
    private Date timeStamp;
}
