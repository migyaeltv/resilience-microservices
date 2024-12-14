package tcc.eng.soft.order_microservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tcc.eng.soft.order_microservice.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}