package miage.IF.api;

import lombok.AllArgsConstructor;
import miage.IF.api.dto.BookRequestDto;
import miage.IF.entity.Book;
import miage.IF.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class BookApi {
    private final BookService bookService;

    @GetMapping()
    public List<Book> getAll(){
        return bookService.findAll();
    }

    @PostMapping()
    public Book save(@RequestBody BookRequestDto requestDto){
        Book book = Book.
                builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .build();
        return bookService.save(book);
    }


}
