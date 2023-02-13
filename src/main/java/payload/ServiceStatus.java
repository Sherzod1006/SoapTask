package payload;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceStatus", propOrder = {
        "status",
        "message",
})
public class ServiceStatus {

    @XmlElement (required = true)
    private String status;
    @XmlElement(required = true)
    private String message;
}
