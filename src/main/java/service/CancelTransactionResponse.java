package service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "errorMsg",
        "status",
        "timeStamp",
})
@XmlRootElement(name = "CancelTransactionResponse")
@Getter
@Setter
public class CancelTransactionResponse {
    @XmlElement(required = true)
    private String errorMsg;
    @XmlElement(required = true)
    private int status;
    @XmlElement(required = true)
    private Date timeStamp;
}
