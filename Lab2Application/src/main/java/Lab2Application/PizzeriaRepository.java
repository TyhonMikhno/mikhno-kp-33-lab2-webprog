package Lab2Application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzeriaRepository extends JpaRepository<Pizzeria, Long> {
}
