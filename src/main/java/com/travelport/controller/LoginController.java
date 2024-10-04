package com.travelport.controller;

import com.travelport.model.LoginModel;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  private static final String LOGIN_VIEW_NAME = "login";

  @GetMapping("/login")
  public String login() {
    return LOGIN_VIEW_NAME;
  }

  @PostMapping(value = "/login")
  public String doLogin(
      @Valid @ModelAttribute LoginModel loginModel, BindingResult bindingResult, Model model) {
    // validate username and password
    if (bindingResult.hasErrors()) {
      model.addAttribute("errors", mapErrors(bindingResult));
      return LOGIN_VIEW_NAME;
    }
    if (areValidCredentials(loginModel)) {
      return "redirect:/hello-world?name=" + loginModel.getUsername();
    }
    model.addAttribute("errors", List.of("Wrong credentials."));
    return LOGIN_VIEW_NAME;
  }

  private boolean areValidCredentials(LoginModel loginModel) {
    return (loginModel.getUsername().equals("youremail@mail.com")
        && loginModel.getPassword().equals("123"));
  }

  private List<String> mapErrors(BindingResult bindingResult) {
    var fieldErrors =
        bindingResult.getFieldErrors().stream()
            .map(
                x ->
                    x.getField()
                        + " "
                        + x.getDefaultMessage()
                        + ". Received: "
                        + x.getRejectedValue());
    var otherErrors =
        bindingResult.getGlobalErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage);
    return Stream.concat(fieldErrors, otherErrors).toList();
  }
}
