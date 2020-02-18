package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tw.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher ,Integer>{

}
