package com.example.vendas;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VendaController {

    private final VendaRepository repositorio;
    private final VendaService vendaService;

    public VendaController(VendaRepository repositorio, VendaService vendaService) {
        this.repositorio = repositorio;
        this.vendaService = vendaService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/vendas";
    }

    @GetMapping("/vendas")
    public String listar(Model model) {
        model.addAttribute("vendas", repositorio.findAll(Sort.by("id")));
        return "lista";
    }

    @GetMapping("/vendas/nova")
    public String nova(Model model) {
        model.addAttribute("venda", new Venda());
        return "form";
    }

    @GetMapping("/vendas/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Venda venda = repositorio.findById(id).orElse(new Venda());
        model.addAttribute("venda", venda);
        return "form";
    }

    @PostMapping("/vendas/salvar")
    public String salvar(@ModelAttribute Venda venda, RedirectAttributes ra) {
        venda.setTotal(venda.getPrecoUnitario() * venda.getQuantidade());
        repositorio.save(venda);
        ra.addFlashAttribute("mensagem", "Venda salva com sucesso!");
        return "redirect:/vendas";
    }

    @GetMapping("/vendas/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        vendaService.excluirERenumerar(id);
        ra.addFlashAttribute("mensagem", "Venda excluida e IDs reorganizados!");
        return "redirect:/vendas";
    }
}
