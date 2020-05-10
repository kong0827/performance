package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "user";
    }

    @GetMapping("/userInsert")
    public String userInsert(Model model, Integer id) {
        model.addAttribute("userId", id);
        return "userInsert";
    }

    @GetMapping("/indicators")
    public String indicators(Model model) {
        return "indicators";
    }

    @GetMapping("/indicatorsInsert")
    public String indicatorsInsert(Model model, Integer id) {
        model.addAttribute("indicatorsId", id);
        return "indicatorsInsert";
    }

    @GetMapping("/performances")
    public String performances(Model model) {
        return "performances";
    }

    @GetMapping("/check")
    public String check(Model model, Integer id) {
        model.addAttribute("pId", id);
        return "check";
    }

    @GetMapping("/performancesInsert")
    public String performancesInsert(Model model, Integer id) {
        model.addAttribute("pId", id);
        return "performancesInsert";
    }

    @GetMapping("/department")
    public String department(Model model) {
        return "department";
    }

    @GetMapping("/departmentInsert")
    public String departmentInsert(Model model, Integer id) {
        model.addAttribute("dId", id);
        return "departmentInsert";
    }

    @GetMapping("/role")
    public String role(Model model) {
        return "role";
    }

    @GetMapping("/roleInsert")
    public String roleInsert(Model model, Integer id) {
        model.addAttribute("roleId", id);
        return "roleInsert";
    }

    @GetMapping("/report")
    public String report(Model model) {
        return "report";
    }

    @GetMapping("/personal")
    public String personal(Model model) {
        return "personal";
    }

    @GetMapping("/personalInsert")
    public String personalInsert(Model model, Integer id) {
        model.addAttribute("pId", id);
        return "personalInsert";
    }

    @GetMapping("/personalInserts")
    public String personalInserts(Model model, Integer id) {
        model.addAttribute("pId", id);
        return "personalInserts";
    }

    @GetMapping("/task")
    public String task(Model model) {
        return "task";
    }

    @GetMapping("/taskInsert")
    public String taskInsert(Model model, Integer id) {
        model.addAttribute("id", id);
        return "taskInsert";
    }

    @GetMapping("/templateOne")
    public String templateOne(Model model) {
        return "templateOne";
    }

    @GetMapping("/taskCheck")
    public String taskCheck(Model model) {
        return "taskCheck";
    }

    @GetMapping("/taskCheckInsert")
    public String taskCheckInsert(Model model, Integer id) {
        model.addAttribute("id", id);
        return "taskCheckInsert";
    }

    @GetMapping("/taskList")
    public String taskList(Model model) {
        return "taskList";
    }

    @GetMapping("/submitList")
    public String submitList(Model model) {
        return "submitList";
    }

    @GetMapping("/taskUpdate")
    public String taskUpdate(Model model, Integer id, Integer states) {
        model.addAttribute("id", id);
        model.addAttribute("states", states);
        return "taskUpdate";
    }

    @GetMapping("/getreport")
    public String getreport(Model model, Integer id, Integer taskId) {
        model.addAttribute("userId", id);
        model.addAttribute("taskId", taskId);
        return "getreport";
    }

    @GetMapping("/submitLists")
    public String submitLists(Model model) {
        return "submitLists";
    }

    @GetMapping("/submitListss")
    public String submitListss(Model model) {
        return "submitListss";
    }

    @GetMapping("/complaint")
    public String complaint(Model model) {
        return "complaint";
    }

    @GetMapping("/complaintInsert")
    public String complaintInsert(Model model, Integer id) {
        model.addAttribute("id", id);
        return "complaintInsert";
    }

    @GetMapping("/complaintcheck")
    public String complaintcheck(Model model) {
        return "complaintcheck";
    }

    @GetMapping("/complaintList")
    public String complaintList(Model model) {
        return "complaintList";
    }

    @GetMapping("/plan")
    public String plan(Model model) {
        return "plan";
    }

    @GetMapping("/planInsert")
    public String planInsert(Model model, Integer id) {
        model.addAttribute("submitId", id);
        return "planInsert";
    }

    @GetMapping("/planList")
    public String planList(Model model) {
        return "planList";
    }

    @GetMapping("/plancheckList")
    public String plancheckList(Model model) {
        return "plancheckList";
    }

    @GetMapping("/totalList")
    public String totalList(Model model) {
        return "totalList";
    }
}
