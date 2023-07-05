package pt.ipvc.controllers;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.dal.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        List<Stock> stocks = StockBLL.index();

        model.addAttribute("userName", userName);
        model.addAttribute("stocks", stocks);

        return "home";
    }
}
