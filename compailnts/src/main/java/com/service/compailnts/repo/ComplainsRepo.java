package com.service.compailnts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.compailnts.model.Complains;

@Repository
public interface ComplainsRepo extends JpaRepository<Complains, Integer>{

}
