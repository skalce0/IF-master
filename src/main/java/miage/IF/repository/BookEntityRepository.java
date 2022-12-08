package miage.IF.repository;

import miage.IF.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<Book,Integer> {
    public List<Book> findAll();

    Book findByCode(String code);
}
