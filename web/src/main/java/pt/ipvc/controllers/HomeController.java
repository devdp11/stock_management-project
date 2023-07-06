package pt.ipvc.controllers;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.dal.Orders;
import pt.ipvc.dal.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ipvc.models.OrderModel;
import pt.ipvc.models.UserOrderModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute("order") @Valid OrderModel orderModel, BindingResult result, Model model) {
        return "home";
    }
}