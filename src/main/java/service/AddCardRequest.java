package service;

import lombok.Getter;
import lombok.Setter;
import payload.CardInfo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "cardInfo"
})
@XmlRootElement(name = "addCardRequest")
@Getter
@Setter
public class AddCardRequest {
    @XmlElement(required = true)
    private CardInfo cardInfo;
    public CardInfo getCardInfo(){
        return cardInfo;
    }
}
