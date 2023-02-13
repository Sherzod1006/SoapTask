package service;

import lombok.Getter;
import lombok.Setter;
import payload.CardInfo;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "errorMsg",
        "status",
        "timeStamp",
        "cardInfo"
})
@XmlRootElement(name = "ChangePasswordResponse")
@Getter
@Setter
public class ChangePasswordResponse {
    @XmlElement(required = true)
    private String errorMsg;
    @XmlElement(required = true)
    private int status;
    @XmlElement(required = true)
    private Date timeStamp;
    @XmlElement(required = true)
    private CardInfo cardInfo;
}
