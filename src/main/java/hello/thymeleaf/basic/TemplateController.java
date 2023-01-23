package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
    /**
     * 페이지가 많이 없고 단순한 경우 사용하면 용이
     * : 중복된 코드들이 많이 생긴다.
     */
    @GetMapping("/fragment")
    public String fragment() {
        return "template/fragment/fragmentMain";
    }

    /**
     * 보통 레이아웃을 많이 사용한다.
     */
    @GetMapping("/layout")
    public String layout() {
        return "template/layout/layoutMain";
    }

    @GetMapping("/layoutExtend")
    public String layoutExtend() {
        return "template/layoutExtend/layoutExtendMain";
    }
}
