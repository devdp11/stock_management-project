package pt.ipvc.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pt.ipvc.bll.OrdersBLL;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Orders;
import pt.ipvc.dal.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ipvc.dal.Users;
import pt.ipvc.models.HomeModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        Users currentUser = UsersBLL.getByName(userName);
        int userId = currentUser.getId();


        List<Stock> stocks = StockBLL.index();

        List<Stock> filteredStocks = stocks.stream()
                .filter(stock -> stock.getProducedQuantity() > 0)
                .collect(Collectors.toList());

        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("stocks", filteredStocks);

        return "home";
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody HomeModel order, HttpServletRequest request) {


            int userId = (int) request.getSession().getAttribute("userId");
            System.out.println("User ID: " + userId);

            Orders newOrder = new Orders();

            int idStock = order.getIdStock();
            int quantity = order.getOrderQuantity();
            double orderPrice = order.getOrderPrice();
            String dateStart = order.getDateStart();
            String state = order.getState();

            newOrder.setIdStock(idStock);
            newOrder.setIdClient(userId);
            newOrder.setOrderQuantity(quantity);
            newOrder.setOrderPrice(orderPrice);
            newOrder.setDateStart(dateStart);
            newOrder.setState(state);

            OrdersBLL.create(newOrder);

            return new ResponseEntity<>(Collections.singletonMap("message", "Order created successfully."), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userName");

        return "redirect:/login";
    }
}
