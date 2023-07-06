package pt.ipvc.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.bll.OrdersBLL;
import pt.ipvc.dal.Users;
import pt.ipvc.dal.Stock;
import pt.ipvc.dal.Orders;
import pt.ipvc.models.UserOrderModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserOrderController {
    @GetMapping("/userOrders")
    public String userOrders(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        Users currentUser = UsersBLL.getByName(userName);
        int userId = currentUser.getId();

        OrdersBLL ordersBLL = new OrdersBLL();
        List<Orders> userOrders = ordersBLL.getUserOrders(userId);

        List<UserOrderModel> userOrderModels = new ArrayList<>();
        for (Orders order : userOrders) {
            UserOrderModel userOrderModel = new UserOrderModel();

            Stock stock = StockBLL.getById(order.getIdStock());
            if (stock != null) {
                userOrderModel.setProductDescription(stock.getDescription());
                userOrderModel.setPriceUnity(stock.getPrice());
            } else {
                userOrderModel.setProductDescription("");
                userOrderModel.setPriceUnity(Double.valueOf(""));

            }

            userOrderModel.setIdClient(order.getIdClient());
            userOrderModel.setOrderPrice(order.getOrderPrice());
            userOrderModel.setOrderQuantity(order.getOrderQuantity());
            userOrderModel.setDateStart(order.getDateStart());
            userOrderModel.setState(order.getState());
            userOrderModels.add(userOrderModel);
        }

        model.addAttribute("orders", userOrderModels);

        return "userOrders";
    }

    @PostMapping("/logoutOrder")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userName");

        return "redirect:/login";
    }
}
