package transformer;

import entity.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import service.AddCardRequest;

@Component
public class CardHelper {
    public Card prepareCardModel(AddCardRequest request){
        Card card = new Card();
        BeanUtils.copyProperties(request.getCardInfo(),card);
        return card;
    }
}
