package hello.thymeleaf.basic;

import hello.thymeleaf.basic.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasicController.class)
public class BasicControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void textBasicTest() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/basic/text-basic"));
        //then
        perform.andDo(print())
                .andExpect(view().name("basic/text-basic"))
                .andExpect(model().attribute("data", "Hello <b>Spring!</b>"));
    }

    @Test
    void textUnescapeTest() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/basic/text-unescaped"));
        //then
        perform.andDo(print())
                .andExpect(view().name("basic/text-unescape"))
                .andExpect(model().attribute("data", "Hello <b>Spring!</b>"));
    }

    @Test
    void variableTest() throws Exception {
        //given
        User userA = User.builder().username("userA").age(10).build();
        User userB = User.builder().username("userB").age(10).build();

        List<User> users = Arrays.asList(userA, userB);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        //when
        ResultActions perform = mvc.perform(get("/basic/variable"));

        //then
        perform.andDo(print())
                .andExpect(view().name("basic/variable"))
                .andExpect(model().attribute("user", userA))
                .andExpect(model().attribute("users", users))
                .andExpect(model().attribute("userMap", userMap))
        ;
    }
}
