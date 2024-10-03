package com.travelport.controller;

import com.travelport.model.LoginModel;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello-world")
public class ExampleController {

  @GetMapping
  public String helloWorld(Model model, @RequestParam(name = "name", required = false) String name) {
    model.addAttribute("name", name);
    model.addAttribute("books", List.of("book1", "book2", "book3"));
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping(value = "/login")
  public String doLogin(LoginModel loginModel) {
    // validate username and password
    if (loginModel.getUsername().equals("naz") && loginModel.getPassword().equals("123")) {
      return "redirect:/hello-world?name=" + loginModel.getUsername();
    }
    return "login";
  }

}
