package hello.thymeleaf.basic;

import hello.thymeleaf.basic.domain.User;
import hello.thymeleaf.basic.springbean.HelloBean;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.web.SpringBootMockServletContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {BasicController.class, HelloBean.class})
@AutoConfigureMockMvc
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
                .andExpect(view().name("basic/text-unescaped"))
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

    @Test
    void basicObjectsTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/basic/basic-objects"));

        //then
         perform.andDo(print())
                .andExpect(view().name("basic/basic-objects"))
                .andExpect(result -> {
                    MockHttpServletRequest request = result.getRequest();
                    assertThat(request.getAttribute("request").getClass()).isEqualTo(MockHttpServletRequest.class);
                    assertThat(request.getAttribute("response").getClass()).isEqualTo(MockHttpServletResponse.class);
                    assertThat(request.getAttribute("servletContext").getClass()).isEqualTo(SpringBootMockServletContext.class);
                })
                .andExpect(request().sessionAttribute("sessionData", "hello Session"));
    }

    @Test
    void basicDateTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/basic/date"));

        //then
        perform.andDo(print())
                .andExpect(view().name("basic/date"))
                .andExpect(result -> {
                    Assertions.assertThat(result.getRequest().getAttribute("localDateTime").getClass()).isEqualTo(LocalDateTime.class);
                });
    }

    @Test
    void linkTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/basic/link"));

        //then
        perform.andDo(print())
                .andExpect(view().name("basic/link"))
                .andExpect(model().attribute("param1","data1"))
                .andExpect(model().attribute("param2","data2"))
        ;
    }
}
