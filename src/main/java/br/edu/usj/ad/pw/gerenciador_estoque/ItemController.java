package br.edu.usj.ad.pw.gerenciador_estoque;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
    
    @GetMapping(value="/")
    public ModelAndView getIndex() {
        List<Item> lista = new ArrayList<>();
        lista = itemRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lista", lista);
        return modelAndView;
    }

    @PostMapping(value="/adicionar")
    public ModelAndView postAdicionar(Item item) {
        itemRepository.save(item);
        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    @GetMapping(value = "/detalhes/{id}")
    public ModelAndView getDetalhes(@PathVariable Long id){
        Item item = new Item();
        item = itemRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("item", item);
        return modelAndView;
    }
    
    @GetMapping(value = "/deletar/{id}")
    public String getDeletar(@PathVariable Long id){
        itemRepository.deleteById(id);
        return "redirect:/";
    }
    
    @GetMapping(value = "/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("detalhes");
        Item item = new Item();
        item = itemRepository.findById(id).get();
        modelAndView.addObject("item", item);
        return modelAndView;
    }
}