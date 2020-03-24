package com.eventosapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.model.Convidado;
import com.eventosapp.model.Evento;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired(required=true)
	EventoRepository er;
	
	@Autowired(required=true)
	ConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrarEvento",method = RequestMethod.GET)
	public String formEvento() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento",method = RequestMethod.POST)
	public String formEvento(@Valid Evento ev, BindingResult result, RedirectAttributes ra) {
		if(result.hasErrors()) {
			ra.addFlashAttribute("mensagem", "VERIFIQUE SE OS CAMPOS FORAM INSERIDOS CORRETAMENTE");
			return "redirect:/cadastrarEvento";
		}
		er.save(ev);
		ra.addFlashAttribute("mensagem", "EVENTO CADASTRADO COM SUCESSO");
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView eventos() {
		ModelAndView mv = new ModelAndView("evento/index");
		Iterable<Evento> evento =er.findAll();
		mv.addObject("evento",evento);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidado",convidados);
		
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo,
			@Valid Convidado convidado, BindingResult result, RedirectAttributes ra) {
		if(result.hasErrors()) {
			ra.addFlashAttribute("mensagem", "VERIFIQUE SE OS CAMPOS FORAM INSERIDOS CORRETAMENTE");
			return "redirect:/{codigo}";
		}
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		ra.addFlashAttribute("mensagem", "CONVIDADOS CADASTRADO COM SUCESSO");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarEv")
	public String deletarEvento(long codigo) {
		Evento ev = er.findByCodigo(codigo);
		er.delete(ev);
		return "redirect:/eventos";
	}
	
	@RequestMapping("/deletarCv")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long loncodigo = evento.getCodigo();
		String codigo = ""+loncodigo;
		
		return "redirect:/"+codigo;
	}
	
}
