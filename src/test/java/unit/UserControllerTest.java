package unit;

import com.api.Controllers.UserController;
import com.api.Entities.User;
import com.api.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.EntityModel;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void controllerShouldReturnCorrectEntityModel(){
        Long userId = 1L;
        User user = new User(1L, "spacii", "qwerty", "sasha@gmail.com");

        EntityModel<User> userEntityModel = EntityModel.of(
                user,
                linkTo(methodOn(UserController.class).getUsers()).withRel("users"),
                linkTo(methodOn(UserController.class).getUserScores(user.getUserId())).withRel("scores")
        );

        when(userService.getUserById(any())).thenReturn(userEntityModel);

        EntityModel<User> result = userController.getUserById(userId);

        verify(userService).getUserById(userId);
        assertThat(result).isEqualTo(userEntityModel);
    }
}
