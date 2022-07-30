package co.com.poli.usersservice;

import co.com.poli.usersservice.clientFeign.BookingClient;
import co.com.poli.usersservice.persistence.entity.User;
import co.com.poli.usersservice.persistence.repository.UserRepository;
import co.com.poli.usersservice.service.UserService;
import co.com.poli.usersservice.service.UserServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private BookingClient bookingClient;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImp(userRepository,bookingClient);
        User user = User.builder()
                .id(4L)
                .name("Carlos")
                .lastname("Gonzales").build();

        Mockito.when(userRepository.findById(4L))
                .thenReturn(Optional.of(user));
    }

    @Test
    public void whenFindById(){
        User userFind = userService.findById(4L);
        Assertions.assertThat(userFind.getName()).isEqualTo("Carlos");
    }


}
