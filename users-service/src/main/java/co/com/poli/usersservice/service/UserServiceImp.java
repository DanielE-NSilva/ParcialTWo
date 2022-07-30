package co.com.poli.usersservice.service;

import co.com.poli.usersservice.clientFeign.BookingClient;
import co.com.poli.usersservice.persistence.entity.User;
import co.com.poli.usersservice.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final BookingClient bookingClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User delete(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null)
            return null;
        if (bookingClient.findByUserId(id).getCode().equals(204)) {
            userRepository.delete(user);
            return user;
        }else {
            return User.builder()
                    .id(0L)
                    .build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

}
