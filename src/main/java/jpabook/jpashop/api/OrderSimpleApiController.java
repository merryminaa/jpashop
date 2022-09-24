package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Theme: xToOne
 * Order
 * Order -> Member (ManyToOne)
 * Order -> Delivery (OneToOne)
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        for (Order order : orders) {
            order.getMember().getName(); //LAZY 프록시 초기화
            order.getDelivery().getAddress(); //LAZY 프록시 초기화
        }
        return orders;
        //문제점: 무한루프 장애 발생(Order -> Member -> Order -> Member...)
    }

    //dto 사용
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        List<SimpleOrderDto> collect = orders.stream()
                .map(m -> new SimpleOrderDto(m))
                .collect(Collectors.toList());
        return collect;
        //문제점: 1+N 문제 발생
    }


    //fetch join 사용
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> collect = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return collect;
    }
    
    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 프록시 초기화
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); //LAZY 프록시 초기화
        }
    }
}
