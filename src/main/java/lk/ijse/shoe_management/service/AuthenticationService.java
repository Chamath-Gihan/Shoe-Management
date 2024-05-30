package lk.ijse.shoe_management.service;

import lk.ijse.shoe_management.auth.request.SignInRequest;
import lk.ijse.shoe_management.auth.request.SignUpRequest;
import lk.ijse.shoe_management.auth.response.JWTAuthResponse;

public interface AuthenticationService {

    JWTAuthResponse signIn(SignInRequest signInRequest);

    JWTAuthResponse signUp(SignUpRequest signUpRequest);
}
