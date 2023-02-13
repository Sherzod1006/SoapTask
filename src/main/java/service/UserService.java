package service;


public interface UserService {
    PerformTransactionResponse performTransactionResponse(PerformTransactionRequest performTransactionRequest);
    CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest);
    CancelTransactionResponse cancelTransaction(CancelTransactionRequest cancelTransactionRequest);
    GetStatementResponse getStatement(GetStatementRequest getStatementRequest);
    GetInformationResponse getInformation(GetInformationRequest getInformationRequest);
    ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest);
    AddUserResponse addUser(AddUserRequest addUserRequest);
    AddCardResponse addCard(AddCardRequest addCardRequest);
}
