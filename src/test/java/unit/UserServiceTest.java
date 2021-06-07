package unit;

import com.api.Controllers.UserController;
import com.api.Entities.User;
import com.api.ModelAssemblers.UserModelAssembler;
import com.api.Repositories.UserRepository;
import com.api.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.EntityModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Spy
    UserModelAssembler userModelAssembler;

    @InjectMocks
    UserService userService;

    @Test
    public void serviceShouldSaveAndReturnNewUserModel(){
        User user = new User(1L, "spacii", "qwerty", "sasha@gmail.com");
        EntityModel<User> userEntityModel = EntityModel.of(
                user,
                linkTo(methodOn(UserController.class).getUsers()).withRel("users"),
                linkTo(methodOn(UserController.class).getUserScores(user.getUserId())).withRel("scores")
        );

        when(userRepository.save(user)).thenReturn(user);

        EntityModel<User> result = userService.addUser(user);

        verify(userRepository).save(user);
        verify(userModelAssembler).toModel(user);

        assertThat(result).isEqualTo(userEntityModel);
    }
}