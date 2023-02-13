package payload;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInfo", propOrder = {
        "userId",
        "name",
        "phoneNumber"
})
@Setter
@Getter
public class UserInfo {
    private long userId;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String phoneNumber;


}
