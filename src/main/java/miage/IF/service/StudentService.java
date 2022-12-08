package miage.IF.service;

import lombok.AllArgsConstructor;
import miage.IF.api.dto.BookRequestDto;
import miage.IF.entity.Book;
import miage.IF.entity.Student;
import miage.IF.repository.BookEntityRepository;
import miage.IF.repository.StudentEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentEntityRepository studentEntityRepository;
    private final BookEntityRepository bookEntityRepository;


    public List<Student> getAll() {
        return studentEntityRepository.findAll();
    }

    @Transactional
    public Student save(Student s) {
        return studentEntityRepository.save(s);
    }

    @Transactional
    public Student save(Integer id, List<BookRequestDto> books) {
        Optional<Student> studentOptional = studentEntityRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return null;
        }
        Student student = studentOptional.get();
        List<Book> listBook = new ArrayList<>();
        for (BookRequestDto book : books) {
            Book bookEntity = bookEntityRepository.findByCode(book.getCode());
            if (bookEntity == null) {
                bookEntity = bookEntityRepository.save(new Book(book.getCode(), book.getName()));
            }
            listBook.add(bookEntity);
        }
        student.setBooks(listBook);
        return studentEntityRepository.save(student);
    }

    public void delete(String email) {
        Optional<Student> student = studentEntityRepository.findByEmail(email);
        student.ifPresent(studentEntityRepository::delete);
    }

    public Student update(Student s, String email) {
        Optional<Student> studentOptional = studentEntityRepository.findById(s.getId());
        if (studentOptional.isEmpty()) {
            return null;
        }
        Student student = studentOptional.get();
        student.setEmail(email);
        return studentEntityRepository.save(student);
    }
}
