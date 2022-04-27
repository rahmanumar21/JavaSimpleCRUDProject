package rahman.crud.demo.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CRUDController {

    @Autowired
    private DataServices service;

    @GetMapping("/data")
    public String showDataList(Model model) {
        model.addAttribute("pageTitle", "Manage Data");
        List<Data> listData = service.listAll();
        model.addAttribute("listData", listData);

        return "data";

    }

    @GetMapping("/data/new")
    public String showNewForm(Model model) {
        model.addAttribute("data", new Data());
        model.addAttribute("pageTitle", "Add New");
        return "data_form";
    }

    @PostMapping("/data/save")
    public String saveUser(Data data, RedirectAttributes ra) {
        service.save(data);
        ra.addFlashAttribute("message", "The data has been saved successfully");
        return "redirect:/data";
    }

    @GetMapping("/data/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Data data = service.get(id);
            model.addAttribute("data", data);
            model.addAttribute("pageTitle", "Edit (ID: " + id + ")");
            return "data_form";
        } catch (DataNotFoundException e) {
            ra.addFlashAttribute("message", "The data has been save successfully.");
            return "redirect:/data";
        }
    }

    @GetMapping("/data/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The data ID " + id + " has been deleted.");
        } catch (DataNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/data";
    }

}
