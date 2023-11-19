package com.microservice.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.entities.Worker;
import com.microservice.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	
	//TROCANDO O USO DO REST TEMPLATE PELO FEIGN CLIENT DO SPRING CLOUD
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(Long workerId, Integer days) {
		
		Worker worker = workerFeignClient.findById(workerId).getBody();
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days); //instancia hard coded ou mockada
	}
}