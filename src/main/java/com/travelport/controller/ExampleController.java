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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello-world")
public class ExampleController {

  @GetMapping
  public String helloWorld(
      Model model, @RequestParam(name = "name", required = false) String name) {
    model.addAttribute("name", name);
    model.addAttribute("books", List.of("book1", "book2", "book3"));
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping(value = "/login")
  public String doLogin(
      @Valid @ModelAttribute LoginModel loginModel, BindingResult bindingResult, Model model) {
    // validate username and password
    if (bindingResult.hasErrors()) {
      model.addAttribute("errors", mapErrors(bindingResult));
      return "login";
    }
    if (areValidCredentials(loginModel)) {
      return "redirect:/hello-world?name=" + loginModel.getUsername();
    }
    model.addAttribute("errors", List.of("Wrong credentials."));
    return "login";
  }

  private boolean areValidCredentials(LoginModel loginModel) {
    return (loginModel.getUsername().equals("nazaret.vieraipanaque@travelport.com")
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
