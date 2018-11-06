package com.rfontana.projetoavaliacao.controller;

import com.rfontana.projetoavaliacao.domain.Invoice;
import com.rfontana.projetoavaliacao.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    private InvoiceService invoiceService;

    private static int currentPage = 1;
    private static int pageSize = 20;

    @Autowired
    public HomeController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/viewinvoices", method = RequestMethod.GET)
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);

        Page<Invoice> invoicePage = invoiceService.findAllPageable(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("invoicePage", invoicePage);

        int totalPages = invoicePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "viewinvoices";
    }


}
