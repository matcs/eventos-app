package com.eventosapp.controller;

import javax.validation.Valid;

import com.eventosapp.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.model.Guest;
import com.eventosapp.repository.GuestRepository;
import com.eventosapp.repository.EventRepository;

@Controller
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private GuestRepository guestRepository;

	@GetMapping(value="/registerEvent")
	public String formEvent() {
		return "event/formEvento";
	}
	
	@PostMapping(value="/registerEvent")
	public String formEvent(@Valid Event event, BindingResult result, RedirectAttributes ra) {
		/*if(result.hasErrors()) {
			ra.addFlashAttribute("mensagem", "VERIFIQUE SE OS CAMPOS FORAM INSERIDOS CORRETAMENTE");
			return "redirect:/registerEvent";
		}*/

		eventRepository.save(event);
		return "redirect:/registerEvent";
	}
	
	@RequestMapping("/events")
	public ModelAndView events() {
		ModelAndView mv = new ModelAndView("event/index");
		Iterable<Event> events = eventRepository.findAll();
		mv.addObject("event",events);
		return mv;
	}
	
	@GetMapping(value="/{eventId}")
	public ModelAndView eventDetails(@PathVariable("eventId") int eventId) {
		Event event = eventRepository.findByEventId(eventId);
		ModelAndView mv = new ModelAndView("event/eventDetails");
		mv.addObject("event", event);
		
		Iterable<Guest> guests = guestRepository.findByEvent(event);
		mv.addObject("guests",guests);
		
		return mv;
	}
	
	@PostMapping("/{eventId}")
	public String postDetailsEvent(@PathVariable("eventId") int eventId,
								   @Valid Guest guest, BindingResult result, RedirectAttributes ra) {

		final String redirect = String.format("redirect:/%s", eventId);

		if(result.hasErrors()) {
			ra.addFlashAttribute("message", "MAKE SURE THE FIELDS HAVE BEEN COMPLETED CORRECTLY");
			return redirect;
		}
		Event event = eventRepository.findByEventId(eventId);
		guest.setEvent(event);
		guestRepository.save(guest);
		ra.addFlashAttribute("message", "SUCCESSFULLY REGISTERED GUEST");

		return redirect;
	}
	
	/*@RequestMapping("/deletarEv")
	public String deletarEvento(int eventId) {
		Event event = eventRepository.findByEventId(eventId);
		eventRepository.delete(event);
		return "redirect:/events";
	}*/
	
	@RequestMapping("/deleteGuest")
	public String deleteGuest(int guestId) {
		Guest guest = guestRepository.findByGuestId(guestId);
		guestRepository.delete(guest);
		
		Event event = guest.getEvent();
		int eventId = event.getEventId();

		final String redirect = String.format("redirect:/%s", eventId);

		return redirect;
	}
	
}
