package service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "errorMsg",
        "status",
        "state",
        "timeStamp",
})
@XmlRootElement(name = "CheckTransactionResponse")
@Getter
@Setter
public class CheckTransactionResponse {
    @XmlElement(required = true)
    private String errorMsg;
    @XmlElement(required = true)
    private int status;
    @XmlElement(required = true)
    private String state;
    @XmlElement(required = true)
    private Date timeStamp;
}
