package com.mscourse.hrpayroll.services;

import com.mscourse.hrpayroll.entities.Payment;
import com.mscourse.hrpayroll.entities.Worker;
import com.mscourse.hrpayroll.feignClients.WorkerFeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    //Utilizando RestTemplate

//    @Value("${hr-worker.host}")
//    private String workerHost;

//    @Autowired
//    private RestTemplate restTemplate;

//        public Payment getPayment(long workerId, int days){
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("id", String.valueOf(workerId));
//        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
//
//        return new Payment(worker.getName(), worker.getDailyIncome(), days);
//    }

    //Utilizando Feign
    @Autowired
    private WorkerFeignClients workerFeignClients;

    public Payment getPayment(long workerId, int days){
        Worker worker = workerFeignClients.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
