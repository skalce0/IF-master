package miage.IF.service;

import lombok.AllArgsConstructor;
import miage.IF.entity.Book;
import miage.IF.repository.BookEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookEntityRepository bookEntityRepository;


    public List<Book> findAll() {
        return bookEntityRepository.findAll();
    }

    public Book save(Book book) {
        return bookEntityRepository.save(book);
    }
}
