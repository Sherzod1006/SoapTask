package payload;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardInfo", propOrder = {
        "cardId",
        "name",
        "password",
        "cardNumber",
        "balance"
})
@Getter
@Setter
public class CardInfo {
    private long cardId;
    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String cardNumber;
    @XmlElement(required = true)
    private long balance;
}
