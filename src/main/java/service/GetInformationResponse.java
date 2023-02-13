package service;

import lombok.Getter;
import lombok.Setter;
import payload.CardInfo;
import payload.UserInfo;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "errorMsg",
        "status",
        "timeStamp",
})
@XmlRootElement(name = "GetInformationResponse")
@Getter
@Setter
public class GetInformationResponse {
    @XmlElement(required = true)
    private String errorMsg;
    @XmlElement(required = true)
    private int status;
    @XmlElement(required = true)
    private Date timeStamp;

    @XmlElement(required = true)
    private CardInfo cardInfo;

    @XmlElement(required = true)
    private UserInfo userInfo;


}
