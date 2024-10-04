package com.travelport.controller;

import com.travelport.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

  @GetMapping("json/{id}/details")
  public ResponseEntity<Users> getById(@PathVariable("id") String id) {
    var user = ExampleController.DB.get(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
  }
}
