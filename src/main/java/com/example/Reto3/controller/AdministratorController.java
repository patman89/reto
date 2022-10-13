package com.example.Reto3.controller;

import com.example.Reto3.entities.Administrator;
import com.example.Reto3.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdministratorController {

    @Autowired
    private AdministratorService AdministratorService;

    @GetMapping("/all")
    public List<Administrator> getAll() {
        return AdministratorService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrator save(@RequestBody Administrator p) {
        return AdministratorService.save(p);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public Administrator update(@RequestBody Administrator administrator) {
        return AdministratorService.update(administrator);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return AdministratorService.delete(id);
    }
}
