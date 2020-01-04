package com.SpringBoot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.SpringBoot.app.models.entity.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{
	


}
