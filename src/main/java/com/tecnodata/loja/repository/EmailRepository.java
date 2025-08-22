package com.tecnodata.loja.repository;

import com.tecnodata.loja.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

}
