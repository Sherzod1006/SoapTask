package service;

import lombok.Getter;
import lombok.Setter;
import payload.CardInfo;
import payload.ServiceStatus;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder ={
        "status",
        "cardInfo"
})

@XmlRootElement(name = "AddCardResponse")

@Getter
@Setter
public class AddCardResponse {
    @XmlElement(required = true)
    private ServiceStatus status;
    @XmlElement(required = true)
    private CardInfo cardInfo;
}

