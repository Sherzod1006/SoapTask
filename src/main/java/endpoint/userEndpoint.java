package endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import service.*;

@Endpoint
public class userEndpoint {
    private static final String NAMESPACE_URI = "http://service/example.com";
    private final UserServiceImpl userService;

    public userEndpoint(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request){
        return userService.addUser(request);
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCardRequest")
    @ResponsePayload
    public AddCardResponse addCard(@RequestPayload AddCardRequest request){
        return userService.addCard(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PerformTransactionRequest")
    @ResponsePayload
    public PerformTransactionResponse performTransaction(@RequestPayload PerformTransactionRequest request){
        return userService.performTransactionResponse(request);
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CancelTransactionRequest")
    @ResponsePayload
    public CancelTransactionResponse cancelTransaction(@RequestPayload CancelTransactionRequest request){
        return userService.cancelTransaction(request);
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTransactionRequest")
    @ResponsePayload
    public CheckTransactionResponse checkTransaction(@RequestPayload CheckTransactionRequest request){
        return userService.checkTransaction(request);
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangePasswordRequest")
    @ResponsePayload
    public ChangePasswordResponse changePassword(@RequestPayload ChangePasswordRequest request){
        return userService.changePassword(request);
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInformationRequest")
    @ResponsePayload
    public GetInformationResponse getInformation(@RequestPayload GetInformationRequest request){
        return userService.getInformation(request);
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStatementRequest")
    @ResponsePayload
    public GetStatementResponse getStatement(@RequestPayload GetStatementRequest request){
        return userService.getStatement(request);
    }


}
