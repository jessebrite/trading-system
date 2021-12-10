package com.group22.orderservice.service;

import com.group22.orderservice.model.ClientOrder;
import com.group22.orderservice.model.MarketDataResponse;
import com.group22.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
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


    public ClientOrder saveOrder(ClientOrder order){

//    MarketDataResponse[] result = restTemplate.getForObject(uri +order.getTicker(), MarketDataResponse[].class);
        MarketDataResponse result1 = restTemplate.getForObject(exchange1+"/md/"+order.getProduct(), MarketDataResponse.class);
        MarketDataResponse result2 = restTemplate.getForObject(exchange2+"/md/"+order.getProduct(), MarketDataResponse.class);
        List<MarketDataResponse> fullMarketData = List.of(result1, result2);
        System.out.println(fullMarketData);

        // List.of(result1, result2).forEach(System.out::println);
        switch (order.getSide()){
            case BUY:
                MarketDataResponse marketDataResponse = fullMarketData.stream().min(Comparator.comparing(MarketDataResponse::getBidPrice)).get();
                MarketDataResponse marketDataResponse2 = fullMarketData.stream().min(Comparator.comparing(MarketDataResponse::getBuyLimit)).get();
                double bidPrice = marketDataResponse.getBidPrice();
//                String exchange = marketDataResponse.getExchange();
//                System.out.println(exchange);
                double buyLimit = marketDataResponse2.getBuyLimit();
                if (order.getPrice() >= bidPrice && order.getQuantity() <= buyLimit){
                    if (bidPrice == result1.getBidPrice()){
                        String s = restTemplate.postForObject(exchange1 + apikey + "/order", order, String.class);
                        System.out.println(exchange1);
                    }else
                    {
                        String s1 = restTemplate.postForObject(exchange2 + apikey + "/order", order, String.class);
                        System.out.println(exchange2);
                    }
                }
        }
        return orderRepository.save(order);
    }

    public List<ClientOrder> saveOrders(List<ClientOrder> orders){
        return orderRepository.saveAll(orders);
    }

    public List<ClientOrder> getOrders(){
        return orderRepository.findAll();
    }

    public ClientOrder getOrderById(UUID id){
        return orderRepository.findById(id).orElse(null);
    }

    public ClientOrder getOrderByProduct(String product){
        return orderRepository.findByProduct(product);
    }

    public String deleteOrder(UUID id){
        orderRepository.deleteById(id);
        return "Order removed!!" + id;
    }

    public ClientOrder updateOrder(ClientOrder order){
        ClientOrder existingOrder = orderRepository.findById(order.getId()).orElse(null);
        existingOrder.setProduct(order.getProduct());
        existingOrder.setPrice(order.getPrice());
        existingOrder.setQuantity(order.getQuantity());
        return orderRepository.save(existingOrder);



    }

}
