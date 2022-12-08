package miage.IF.api;

import miage.IF.entity.Student;
import miage.IF.repository.BookEntityRepository;
import miage.IF.repository.StudentEntityRepository;
import miage.IF.service.StudentService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class StudentApiTest {
    @Mock
    private StudentEntityRepository repository;
    @Mock
    private StudentService studentService;
    @Mock
    private BookEntityRepository bookEntityRepository;


    private StudentApi studentApi;

    private EasyRandom random;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        random = new EasyRandom();
        studentApi = new StudentApi(studentService,repository,bookEntityRepository);
    }
    @Test
    public void shouldGetAll() {
        //Given
        List<Student> students = random.objects(Student.class,5).collect(Collectors.toList());
        Mockito.when(repository.findAll()).thenReturn(students);

        //When
        List<Student> output = studentApi.getAll();
        //Then
        Assertions.assertThat(students.equals(output));
    }
}
