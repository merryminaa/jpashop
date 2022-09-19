package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Getter @Setter
public class OrderSearch {

    private String username;
    private OrderStatus orderStatus;

}
