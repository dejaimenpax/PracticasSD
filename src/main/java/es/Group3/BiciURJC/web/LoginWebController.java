package es.Group3.BiciURJC.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {

    @RequestMapping("/login")
    public String login(Model model) { return "login"; }

    @RequestMapping("/loginError")
    public String loginError(Model model) {
        return "loginError";
    }

}