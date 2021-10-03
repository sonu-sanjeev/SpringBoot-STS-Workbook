package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Address;
import com.example.service.AddressService;

@RestController
@RequestMapping("/api/address/")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("getById")
	public Address getAddressById(@RequestParam int id) {
		Address address = addressService.getAddress(id);
		return address;
	}
}
