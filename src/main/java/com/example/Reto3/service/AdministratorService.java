package com.example.Reto3.service;

import com.example.Reto3.entities.Administrator;
import com.example.Reto3.repository.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public List<Administrator> getAll() {
        return administratorRepository.getAll();
    }

    public Optional<Administrator> getProduct(int id) {
        return administratorRepository.getAdministrator(id);
    }

    public Administrator save(Administrator p) {
        if (p.getId() == null) {
            return administratorRepository.save(p);
        } else {
            Optional<Administrator> e = administratorRepository.getAdministrator(p.getId());
            if (e.isPresent()) {
                return p;
            } else {
                return administratorRepository.save(p);
            }
        }
    }

    public Administrator update(Administrator p) {
        if (p.getId() != null) {
            Optional<Administrator> q = administratorRepository.getAdministrator(p.getId());
            if (q.isPresent()) {
                if (p.getName() != null) {
                    q.get().setName(p.getName());
                }
                if (p.getPassword() != null) {
                    q.get().setPassword(p.getPassword());
                }
                if (p.getEmail() != null) {
                    q.get().setEmail(p.getEmail());
                }
                administratorRepository.save(q.get());
                return q.get();
            } else {
                return p;
            }
        } else {
            return p;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Administrator> p = administratorRepository.getAdministrator(id);
        if (p.isPresent()) {
            administratorRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }


}
