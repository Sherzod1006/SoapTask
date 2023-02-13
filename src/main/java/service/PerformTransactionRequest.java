package service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "password",
        "username",
        "amount",
        "serviceId",
        "transactionId",
        "transactionTime"
})
@XmlRootElement(name = "PerformTransactionRequest")
@Getter
@Setter
public class PerformTransactionRequest {
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private long amount;
    @XmlElement(required = true)
    private long serviceId;
    @XmlElement(required = true)
    private long transactionId;
    @XmlElement
    private Date transactionTime;
}
