package com.CargoSite.controller;

import com.CargoSite.models.Cargo;
import com.CargoSite.models.Post;
import com.CargoSite.models.UserCargo;
import com.CargoSite.services.CargoService;
import com.CargoSite.services.PostService;
import com.CargoSite.services.UserCargoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CargoController {

    @Autowired
    private CargoService cargoService;


    @Autowired
    private PostService postService;

    @Autowired
    private UserCargoService userCargoService;

    @GetMapping("/")
    public String viewLoginPage(@ModelAttribute("UserCargo") UserCargo usercargo, Model model) {
        model.addAttribute("UserCargo", usercargo);
        return "index";
    }

    @RequestMapping("/registry")
    public String showNewSessionForm(@ModelAttribute("UserCargo") UserCargo usercargo){
        return "registry";
    }

    @GetMapping(value="/login")
    public String login(@ModelAttribute("UserCargo") UserCargo usercargo, HttpSession session){
        if (userCargoService.checkLoginAndPassword(usercargo)) {
            session.setAttribute("UserCargo", usercargo);
            return "redirect:/main_cargo";}
        else {return "redirect:/";}
    }

    @GetMapping("/main_cargo")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        List<Cargo> listCargo = cargoService.listAll(keyword);
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        return "main-cargo";
    }

    @GetMapping("/new_cargo")
    public String showNewSessionForm(Model model){
        Cargo cargo = new Cargo();
        model.addAttribute("Cargo", cargo);
        return "new-cargo";
    }

    @RequestMapping(value = "/save_cargo", method = RequestMethod.POST)
    public String saveSession(@ModelAttribute("Session") Cargo cargo){
        cargoService.save(cargo);
        return "redirect:/main_cargo";
    }

    @RequestMapping("/edit_cargo/{id}")
    public ModelAndView showEditSessionFrom(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit-cargo");
        Cargo cargo = cargoService.get(id);
        mav.addObject("Cargo", cargo);
        return mav;
    }
    @RequestMapping("/delete_cargo/{id}")
    public String deleteSession(@PathVariable(name = "id") Long id){
        cargoService.delete(id);
        return "redirect:/main_cargo";
    }

    @GetMapping("/autoblog")
    public String viewAutoblogPage(@SessionAttribute("UserCargo") UserCargo usercargo) {
        return "autoblog";
    }

    @GetMapping(value="/check")
    public String login(@SessionAttribute("UserCargo") UserCargo usercargo){
        if (usercargo.getRole() == 1) { // ���� ���� == 1, ����� ��� ����� � �� ��� �������
            return "redirect:/autoblog_main_admin";
        }
        else {return "redirect:/autoblog";}
    }

    @GetMapping("/autoblog_main")
    public String viewAutoblogMainPage(@Param("keyword") String keyword, Model model) {
        List<Post> listPost = postService.listAll(keyword);
        model.addAttribute("listPost", listPost);
        model.addAttribute("keyword", keyword);
        return "main-autoblog";
    }

    @GetMapping("/autoblog_main_admin")
    public String viewAutoblogMainAdminPage(@Param("keyword") String keyword, Model model) {
        List<Post> listPost = postService.listAll(keyword);
        model.addAttribute("listPost", listPost);
        model.addAttribute("keyword", keyword);
        return "main-autoblog-admin";
    }

    @GetMapping("/new_post")
    public String newPost(Model model){
        Post post = new Post();
        model.addAttribute("Post", post);
        return "new-post";
    }

    @RequestMapping(value = "/save_post", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("Post") Post post){
        postService.save(post);
        return "redirect:/autoblog_main_admin";
    }

    @GetMapping("/edit_post")
    public String editPost(Model model){
        Post post = new Post();
        model.addAttribute("Post", post);
        return "edit-post";
    }




}