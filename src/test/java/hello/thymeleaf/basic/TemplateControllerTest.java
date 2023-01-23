package hello.thymeleaf.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = {TemplateController.class})
public class TemplateControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    void fragmentTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/template/fragment"));
        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("template/fragment/fragmentMain"));
    }

    @Test
    void layoutTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/template/layout"));
        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("template/layout/layoutMain"));
    }

    @Test
    void layoutExtendTest() throws Exception {
        //given

        //when
        ResultActions perform = mvc.perform(get("/template/layoutExtend"));
        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("template/layoutExtend/layoutExtendMain"));
    }
}
