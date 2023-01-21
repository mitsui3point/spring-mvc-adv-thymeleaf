package hello.thymeleaf.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
        //given

        //when
        ResultActions perform = mvc.perform(get("/basic/text-basic"));
        //then
        perform.andDo(print())
                .andExpect(view().name("basic/text-basic"))
                .andExpect(model().attribute("data", "Hello <b>Spring!</b>"));
    }

    @Test
    void textUnescapeTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/basic/text-unescaped"));
        //then
        perform.andDo(print())
                .andExpect(view().name("basic/text-unescape"))
                .andExpect(model().attribute("data", "Hello <b>Spring!</b>"));
    }
}
