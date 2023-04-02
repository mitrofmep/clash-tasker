package ru.mitrofmep.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mitrofmep.dao.CollisionDAO;
import ru.mitrofmep.dao.EngineerDAO;
import ru.mitrofmep.models.Engineer;
import ru.mitrofmep.util.EngineerValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/engineers")
public class EngineerController {

    private final EngineerDAO engineerDAO;
    private final EngineerValidator engineerValidator;
    private final CollisionDAO collisionDAO;

    @Autowired
    public EngineerController(EngineerDAO engineerDAO, EngineerValidator engineerValidator, CollisionDAO collisionDAO) {
        this.engineerDAO = engineerDAO;
        this.engineerValidator = engineerValidator;
        this.collisionDAO = collisionDAO;
    }

    @GetMapping()
    public String index(Model model) {
        List<Engineer> engineers = engineerDAO.index();
        Map<Integer, Integer> collisionsForEachPerson = collisionDAO.getCollisionsPerPerson();

        List<Engineer> sortedEngineers = engineers.stream()
                .sorted((e1, e2) -> collisionsForEachPerson.get(e2.getId()) - collisionsForEachPerson.get(e1.getId()))
                .collect(Collectors.toList());

        model.addAttribute("engineers", sortedEngineers);
        model.addAttribute("collisionsForEachPerson", collisionsForEachPerson);
        return "engineers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("engineer", engineerDAO.show(id));
        model.addAttribute("collisions", collisionDAO.index(id));
        return "engineers/show";
    }

    @GetMapping("/new")
    public String newEngineer(@ModelAttribute("engineer") Engineer engineer) {
        return "engineers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("engineer") @Valid Engineer engineer,
                         BindingResult bindingResult) {

        engineerValidator.validate(engineer, bindingResult);

        if (bindingResult.hasErrors()) return "engineers/new";
        engineerDAO.save(engineer);
        return "redirect:/engineers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("engineer", engineerDAO.show(id));
        return "engineers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("engineer") @Valid Engineer engineer,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        engineerValidator.validate(engineer, bindingResult);


        if (bindingResult.hasErrors()) return "engineers/edit";
        engineerDAO.update(id, engineer);
        return "redirect:/engineers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        engineerDAO.delete(id);
        return "redirect:/engineers";
        
    }
}
