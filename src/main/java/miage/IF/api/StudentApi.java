package miage.IF.api;

import lombok.AllArgsConstructor;
import miage.IF.api.dto.BookRequestDto;
import miage.IF.entity.Book;
import miage.IF.entity.Student;
import miage.IF.repository.BookEntityRepository;
import miage.IF.repository.StudentEntityRepository;
import miage.IF.service.StudentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
@AllArgsConstructor
public class StudentApi {
    private final StudentService studentService;
    private final StudentEntityRepository studentEntityRepository;
    private final BookEntityRepository bookEntityRepository;

    @GetMapping()
    public List<Student> getAll(){
        return studentService.getAll();
    }

    @PostMapping()
    public Student save(@RequestBody Student s){
        return studentService.save(s);
    }

    @PutMapping()
    public Student update(@RequestBody Student s, @RequestParam String email){ // students?email=david@gmail.com
        return studentService.update(s,email);
    }
/*
    @PutMapping("books")
    public Student updateStudentBook(@RequestParam Integer id, @RequestBody List<Book> books){ // students?email=david@gmail.com
        Optional<Student> student = studentEntityRepository.findById(id);
        student.ifPresent(value -> value.setBooks(books));
        books.forEach( b-> b.setStudent(student.get()));
        return studentService.save(student.orElse(null));
    }

 */

    @PutMapping("books")
    public Student updateStudentBook(@RequestParam Integer id, @RequestBody List<BookRequestDto> books){ // students?email=david@gmail.com
        return studentService.save(id,books);
    }


    @DeleteMapping("/email/{email}")
    public void delete(@PathVariable String email){ // students/email/david@gmail.com
        studentService.delete(email);

    }

    @PutMapping("{id}/new-email")
    public Student updateEmailWithId(@PathVariable Integer id, @RequestParam String email){
        Optional<Student> student = studentEntityRepository.findById(id);
        if (student.isEmpty()) {
            return null;
        }
        student.ifPresent(value -> value.setEmail(email));
        return studentEntityRepository.save(student.orElse(null));
    }

}
