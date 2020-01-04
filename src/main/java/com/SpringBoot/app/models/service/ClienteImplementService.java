package com.SpringBoot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SpringBoot.app.models.dao.IClienteDao;
import com.SpringBoot.app.models.entity.Cliente;

@Service
public class ClienteImplementService implements IClienteService{
	
	@Autowired
	private IClienteDao iclienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) iclienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		iclienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return iclienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		iclienteDao.deleteById(id);
		
	}

}
