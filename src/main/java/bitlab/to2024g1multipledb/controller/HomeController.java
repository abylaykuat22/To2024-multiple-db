package bitlab.to2024g1multipledb.controller;

import bitlab.to2024g1multipledb.service.DeveloperService;
import bitlab.to2024g1multipledb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private ItemService itemService;
  @Autowired
  private DeveloperService developerService;

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("developers", developerService.getDevelopers());
    model.addAttribute("items", itemService.getItems());
    return "home";
  }
}
