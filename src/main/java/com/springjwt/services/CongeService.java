package com.springjwt.services;

import com.springjwt.repositories.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CongeService implements ICongeService {



    @Autowired
    CongeRepository congeRepository;
    @Override
    public Long countConge() {
        return congeRepository.count();
    }
}
