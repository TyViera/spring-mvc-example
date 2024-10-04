package com.travelport.controller;

import com.travelport.model.Users;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello-world")
public class ExampleController {

  protected static final Map<String, Users> DB;

  static {
    DB = new HashMap<>();
    DB.put("1", new Users("1", "Naz", List.of("Nazbook1", "Nazbook2", "Nazbook3")));
    DB.put("2", new Users("2", "Adria", List.of("Adriabook1", "Adriabook2", "Adriabook3")));
    DB.put("3", new Users("3", "Daniel", List.of("Danielbook1", "Danielbook2", "Danielbook3")));
    DB.put("4", new Users("4", "David", List.of()));
  }

  @GetMapping
  public String helloWorld(
      Model model,
      @RequestParam(name = "name", required = false) String name,
      @RequestHeader(name = "customheader", required = false) String customHeader,
      @CookieValue(name = "c-session") String cookie) {
    model.addAttribute("name", name);
    model.addAttribute("customerHeader", customHeader);
    model.addAttribute("books", List.of("book1", "book2", "book3"));
    return "index";
  }

  @GetMapping("/{id}/details")
  public String getById(Model model, @PathVariable("id") String id) {
    var user = DB.get(id);
    model.addAttribute("name", user.name());
    model.addAttribute("books", user.books());
    return "index";
  }
}
