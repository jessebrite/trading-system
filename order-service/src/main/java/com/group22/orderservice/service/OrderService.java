package com.group22.orderservice.service;
import com.group22.orderservice.model.ClientOrder;
import com.group22.orderservice.model.MarketDataResponse;
import com.group22.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
@Slf4j
public class OrderService {

    @Value("${exchange1}")
    private String exchange1;

    @Value("${exchange2}")
    private String exchange2;

    @Value("${api_key}")
    private String apikey;

    @Value("${uri}")
    private String uri;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;


    public ClientOrder saveOrder(ClientOrder order) {

        MarketDataResponse[] results = restTemplate.getForObject(uri + order.getProduct(), MarketDataResponse[].class);
        if (results != null) {
            switch (order.getSide()) {
                case BUY:
                    MarketDataResponse marketDataResponse1 = Arrays.stream(results).min(Comparator.comparing(MarketDataResponse::getBidPrice)).get();
                    String myExchange = marketDataResponse1.getExchange();
                    if ((order.getPrice() <= marketDataResponse1.getBidPrice() && order.getQuantity() <= marketDataResponse1.getBuyLimit()) ){
                        if(myExchange.equals("EXCHANGE1")){
                            String buyResultFromExchange1 = restTemplate.postForObject(exchange1 + apikey + "/order", order, String.class);
                            order.setOrderId(buyResultFromExchange1);
                            log.info("order was successful on exchange one!");
                        }else {
                            String buyResultFromExchange2 = restTemplate.postForObject(exchange2 + apikey + "/order", order, String.class);
                            order.setOrderId(buyResultFromExchange2);
                            log.info("order was successful on exchange two !");
                        }

                    }else{
                        System.out.println("nope!");
                    }
                    break;

                case SELL:
                    MarketDataResponse marketDataResponse2 = Arrays.stream(results).max(Comparator.comparing(MarketDataResponse::getAskPrice)).get();
                    String new_exchange = marketDataResponse2.getExchange();
                    if(order.getPrice() <= marketDataResponse2.getAskPrice() && order.getQuantity() <= marketDataResponse2.getSellLimit()){
                        if(new_exchange.equals("EXCHANGE1")){
                            String sellResultFromExchange1 =  restTemplate.postForObject(exchange1 + apikey + "/order", order, String.class);
                            order.setOrderId(sellResultFromExchange1);
                            System.out.println("order was successful on exchange one!!!");
                        }else{
                            String buyResultFromExchange2 = restTemplate.postForObject(exchange2 + apikey + "/order", order, String.class);
                            order.setOrderId(buyResultFromExchange2);
                            System.out.println("order was successful on exchange two !!!!!!");
                        }


                    }else {
                        System.out.println("you asked price is greater than asked price");
                    }

            }
        }

        return orderRepository.save(order);

    }



    public List<ClientOrder> getOrders() {
        return orderRepository.findAll();
    }

    public ClientOrder getOrderById(String id) {
        return orderRepository.findByOrderId(id);
    }

    public ClientOrder getOrderByProduct(String product) {
        return orderRepository.findByProduct(product);
    }



//    public ClientOrder updateOrder(ClientOrder order){
//        ClientOrder existingOrder = orderRepository.findById(order.getId()).orElse(null);
//        existingOrder.setProduct(order.getProduct());
//        existingOrder.setPrice(order.getPrice());
//        existingOrder.setQuantity(order.getQuantity());
//        return orderRepository.save(existingOrder);
//
//
//
//    }


}

