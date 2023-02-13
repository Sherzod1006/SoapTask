package service;

import entity.Card;
import entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import payload.CardInfo;
import payload.ServiceStatus;
import payload.UserInfo;
import repository.CardRepository;
import repository.UserRepository;
import transformer.CardHelper;
import transformer.UserHelper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

@Service
@Component
public class UserServiceImpl implements UserService {
    CardRepository cardRepository;
    UserRepository userRepository;
    UserHelper userHelper;
    CardHelper cardHelper;
    Random rn = new Random();
    int providerTrId = rn.nextInt(200000);

    @Override
    public PerformTransactionResponse performTransactionResponse(PerformTransactionRequest performTransactionRequest) {
        PerformTransactionResponse response = new PerformTransactionResponse();
        User user = userRepository.findByName(performTransactionRequest.getUsername());
        User client = userRepository.findById(performTransactionRequest.getTransactionId());

        if (isValidCardCheckingWithLunaAlgorithm(user.getCard().getCardNumber())
                && isValidCardCheckingWithLunaAlgorithm(client.getCard().getCardNumber())
                && user.getCard().getPassword().equals(performTransactionRequest.getPassword())
                && performTransactionRequest.getAmount() < 124500000
                && user.getCard().getBalance() >= performTransactionRequest.getAmount()) {
            long newBalanceUserCard = user.getCard().getBalance() - performTransactionRequest.getAmount();
            user.getCard().setBalance(newBalanceUserCard);
            long newBalanceClientCard = client.getCard().getBalance() + performTransactionRequest.getAmount();
            client.getCard().setBalance(newBalanceClientCard);
            userRepository.save(user);
            userRepository.save(client);
            response.setErrorMsg("Transaction successfully committed");
            response.setTimeStamp(Date.valueOf(LocalDate.now()));
            response.setStatus(1);
            response.setProviderTrId(providerTrId);
            return response;
        }
        response.setErrorMsg("Transaction is not eligible!");
        response.setStatus(0);
        response.setTimeStamp(Date.valueOf(LocalDate.now()));
        response.setProviderTrId(providerTrId);
        return response;
    }

    @Override
    public CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest) {
        CheckTransactionResponse response = new CheckTransactionResponse();
        User user = userRepository.findByName(checkTransactionRequest.getUsername());
        User client = userRepository.findById(checkTransactionRequest.getTransactionId());
        if (isValidCardCheckingWithLunaAlgorithm(user.getCard().getCardNumber())
                && isValidCardCheckingWithLunaAlgorithm(client.getCard().getCardNumber())
                && user.getCard().getPassword().equals(checkTransactionRequest.getPassword())) {
            response.setErrorMsg("Transaction successfully DONE");
            response.setTimeStamp(Date.valueOf(LocalDate.now()));
            response.setStatus(1);
            response.setState("SUCCESS");
            return response;
        }
        response.setErrorMsg("Transaction hasn't done!");
        response.setStatus(0);
        response.setTimeStamp(Date.valueOf(LocalDate.now()));
        response.setState("FAILURE");
        return response;
    }

    @Override
    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest cancelTransactionRequest) {
        CancelTransactionResponse response = new CancelTransactionResponse();
        User user = userRepository.findByName(cancelTransactionRequest.getUsername());
        User client = userRepository.findById(cancelTransactionRequest.getTransactionId());

        if (isValidCardCheckingWithLunaAlgorithm(user.getCard().getCardNumber())
                && isValidCardCheckingWithLunaAlgorithm(client.getCard().getCardNumber())
                && user.getCard().getPassword().equals(cancelTransactionRequest.getPassword())
                && cancelTransactionRequest.getAmount() < 124500000
                && client.getCard().getBalance() >= cancelTransactionRequest.getAmount()) {
            long newBalanceUserCard = user.getCard().getBalance() + cancelTransactionRequest.getAmount();
            user.getCard().setBalance(newBalanceUserCard);
            long newBalanceClientCard = client.getCard().getBalance() - cancelTransactionRequest.getAmount();
            client.getCard().setBalance(newBalanceClientCard);
            userRepository.save(user);
            userRepository.save(client);
            response.setErrorMsg("Transaction successfully canceled");
            response.setTimeStamp(Date.valueOf(LocalDate.now()));
            response.setStatus(1);
            return response;
        }
        response.setErrorMsg("You can not cancel transaction. Something is going wrong!");
        response.setStatus(0);
        response.setTimeStamp(Date.valueOf(LocalDate.now()));
        return response;
    }

    @Override
    public GetStatementResponse getStatement(GetStatementRequest getStatementRequest) {
        GetStatementResponse response = new GetStatementResponse();
        response.setErrorMsg("Ok");
        response.setStatus(1);
        response.setTimeStamp(Date.valueOf(LocalDate.now()));
        return response;
    }

    @Override
    public GetInformationResponse getInformation(GetInformationRequest getInformationRequest) {
        GetInformationResponse response = new GetInformationResponse();
        User user = userRepository.findByName(getInformationRequest.getUsername());
        if (Objects.equals(getInformationRequest.getPassword(), user.getCard().getPassword())) {
            UserInfo userInfo = mapUserToUserInfo(user);
            Card card = user.getCard();
            CardInfo cardInfo = mapCardToCardInfo(card);
            response.setUserInfo(userInfo);
            response.setCardInfo(cardInfo);
            response.setErrorMsg("Every Thing is Ok");
            response.setStatus(1);
            response.setTimeStamp(Date.valueOf(LocalDate.now()));
            return response;
        }
        UserInfo userInfo = mapUserToUserInfo(user);
        Card card = user.getCard();
        CardInfo cardInfo = mapCardToCardInfo(card);
        response.setUserInfo(userInfo);
        response.setCardInfo(cardInfo);
        response.setErrorMsg("Sorry something is wrong");
        response.setStatus(0);
        response.setTimeStamp(Date.valueOf(LocalDate.now()));
        return response;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        ChangePasswordResponse response = new ChangePasswordResponse();
        User user = userRepository.findByName(changePasswordRequest.getUsername());
        if (!Objects.equals(changePasswordRequest.getPassword(), user.getCard().getPassword())) {
            user.getCard().setPassword(changePasswordRequest.getPassword());
            userRepository.save(user);
            response.setErrorMsg("Card password changed successfully");
            response.setStatus(1);
            response.setTimeStamp(Date.valueOf(LocalDate.now()));
            response.setCardInfo(mapCardToCardInfo(user.getCard()));
            return response;
        }
        response.setErrorMsg("Card password did not change");
        response.setStatus(0);
        response.setTimeStamp(Date.valueOf(LocalDate.now()));
        response.setCardInfo(mapCardToCardInfo(user.getCard()));
        return response;
    }

    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) {
        AddUserResponse response = new AddUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!isValidPhoneNumber(addUserRequest.getUserInfo().getPhoneNumber())){
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Content is not added Successfully");
            response.setStatus(serviceStatus);
            return response;
        }
        User user = userHelper.prepareUserModel(addUserRequest);
        userRepository.save(user);
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content is added Successfully");
        response.setStatus(serviceStatus);
        response.setUserInfo(response.getUserInfo());
        return response;
    }

    @Override
    public AddCardResponse addCard(AddCardRequest addCardRequest) {
        if (isValidCardCheckingWithLunaAlgorithm(addCardRequest.getCardInfo().getCardNumber())) {
            Card card = cardHelper.prepareCardModel(addCardRequest);
            cardRepository.save(card);
            AddCardResponse response = new AddCardResponse();
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Content added Successfully");
            response.setStatus(serviceStatus);
            response.setCardInfo(response.getCardInfo());
            return response;
        } else {
            AddCardResponse response = new AddCardResponse();
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Content not added");
            response.setStatus(serviceStatus);
            return response;
        }
    }

    public static boolean isValidCardCheckingWithLunaAlgorithm(String cardNumber) {
        int totalSum = 0;
        StringBuilder sb = new StringBuilder();
        if (cardNumber.length() == 16) {
            for (int i = 0; i < cardNumber.length(); i++) {
                if (!Character.isDigit(cardNumber.charAt(i)))
                    return false;
                else if (i % 2 != 0)
                    sb.append(cardNumber.charAt(i));
                else {
                    int num = Integer.parseInt("" + cardNumber.charAt(i)) * 2;
                    if (num < 9)
                        sb.append(num);
                    else
                        sb.append((num % 10) + num / 10);
                }
            }
        }
        String newCardNumber = sb.toString();
        for (int i = 0; i < newCardNumber.length(); i++)
            totalSum += Integer.parseInt("" + newCardNumber.charAt(i));
        return totalSum % 10 == 0;
    }

    public static boolean isValidPhoneNumber(String phonenumber) {
        if (!phonenumber.startsWith("999"))
            return false;
        else if (phonenumber.length() == 16) {
            for (int i = 3; i < 16; i++)
                return Character.isDigit(phonenumber.charAt(i));
            return true;
        }
        return false;
    }

    public CardInfo mapCardToCardInfo(Card card) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardId(card.getId());
        cardInfo.setCardNumber(card.getCardNumber());
        cardInfo.setName(card.getName());
        cardInfo.setBalance(card.getBalance());
        cardInfo.setPassword(card.getPassword());
        return cardInfo;
    }

    public UserInfo mapUserToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setPhoneNumber(user.getPhoneNumber());
        return userInfo;
    }
}
