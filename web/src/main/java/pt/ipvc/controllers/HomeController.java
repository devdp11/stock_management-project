package pt.ipvc.controllers;

import org.apache.tomcat.jni.Local;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ipvc.bll.OrdersBLL;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Orders;
import pt.ipvc.dal.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ipvc.models.HomeModel;
import pt.ipvc.models.OrderModel;
import pt.ipvc.models.UserOrderModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        List<Stock> stocks = StockBLL.index();

        // Filtra os produtos com producedQuantity > 0
        List<Stock> filteredStocks = stocks.stream()
                .filter(stock -> stock.getProducedQuantity() > 0)
                .collect(Collectors.toList());

        model.addAttribute("userName", userName);
        model.addAttribute("stocks", filteredStocks);

        return "home";
    }


    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute("order") @Valid OrderModel orderModel, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            // Lidar com erros de validação, se necessário
            return "home";
        }

        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userName");

        return "redirect:/login";
    }
}
