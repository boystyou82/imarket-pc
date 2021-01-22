package kr.co.imarket_pc;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontRouterController implements ErrorController {

    @GetMapping({"/", "/error"})
    public String handleError() {
        return "/index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}