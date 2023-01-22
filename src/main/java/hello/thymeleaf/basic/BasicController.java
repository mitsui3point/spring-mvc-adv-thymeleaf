package hello.thymeleaf.basic;

import hello.thymeleaf.basic.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {

        User userA = User.builder().username("userA").age(10).build();
        User userB = User.builder().username("userB").age(10).build();

        List<User> users = Arrays.asList(userA, userB);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);
        return "basic/variable";
    }

    /**
     * @HttpServletRequest tomcat implements {@link RequestFacade}
     * @HttpServletResponse tomcat implements {@link ResponseFacade}
     * @HttpSession tomcat implements {@link StandardSessionFacade}
     * @ServletContext tomcat implements {@link org.apache.catalina.core.ApplicationContextFacade}
     */
    @GetMapping("/basic-objects")
    public String basicObjects(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session) {
        model.addAttribute("request", request);// request = org.apache.catalina.connector.RequestFacade@7d802f8b; interface HttpServletRequest 구현체
        model.addAttribute("response", response);// response = org.apache.catalina.connector.ResponseFacade@7347ce4b; interface HttpServletResponse 구현체
        model.addAttribute("plainSession", session);// session = org.apache.catalina.session.StandardSessionFacade@4c606a14; interface HttpSession 구현체
        model.addAttribute("servletContext", request.getServletContext());//servletContext = org.apache.catalina.core.ApplicationContextFacade@13b98349; interface ServletContext 구현체
        session.setAttribute("sessionData", "hello Session");
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String basicObjects(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }
}
