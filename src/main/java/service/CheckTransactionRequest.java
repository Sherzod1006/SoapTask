package service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "password",
        "username",
        "transactionId",
        "transactionTime"
})
@XmlRootElement(name = "CheckTransactionRequest")
@Getter
@Setter
public class CheckTransactionRequest {
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private long transactionId;
    @XmlElement
    private Date transactionTime;
}
