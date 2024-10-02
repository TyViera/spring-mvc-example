package com.travelport.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello-world")
public class ExampleController {
  @GetMapping
  public ModelAndView helloWorld() {
    var modelAndView = new ModelAndView("index");
    modelAndView.addObject("bookList", List.of("book1", "book2", "book3"));
    return modelAndView;
  }

}
