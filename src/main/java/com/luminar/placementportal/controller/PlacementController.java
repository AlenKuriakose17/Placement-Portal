package com.luminar.placementportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luminar.placementportal.model.PlacementModel;
import com.luminar.placementportal.service.PlacementService;

@Controller
public class PlacementController {

	@Autowired
	private PlacementService placementService;
	
	@GetMapping("/viewPlacementList")
	public String viewPlacementList(Model model) {
		model.addAttribute("listPlacements", placementService.getAllPlacements());
		return "placement_list";
		
	}
	
	@GetMapping("/showNewPlacementForm")
	public String showNewPlacementForm(Model model) {
	    model.addAttribute("addORupdate", new PlacementModel()); // or whatever model class you're using
	    return "add_placement"; // Thymeleaf will render add_placement.html
	}
	
	@GetMapping("/placementUpdateForm/{id}")
	public String placementUpdateForm(@PathVariable(value = "id") long id, Model model) {
		// get placement from the service
		PlacementModel placement = placementService.getPlacementById(id);
		// set placement as a model attribute to pre-populate the form
		model.addAttribute("addORupdate", placement);
		return "update_placement";
	}
	
	@GetMapping("/showDetailed/{id}")
	public String showDetailed (@PathVariable(value = "id") long id, Model model) {
		
		PlacementModel placement = placementService.getPlacementById(id);
		
		model.addAttribute("detailedview", placement);
		return "detailed_view";
	}

	
	@PostMapping("/savePlacementAdmin")
	public String savePlacementAdmin(@ModelAttribute("addORupdate") PlacementModel placement) {
// save User to database
		placementService.savePlacment(placement);
		return "redirect:/viewPlacementList";
	}
	
	@GetMapping("/deletePlacement/{id}")
	public String deletePlacement(@PathVariable(value = "id") long id) {
		// call delete user method
		this.placementService.deletePlacementById(id);
		return "redirect:/viewPlacementList";
	}
	
	
}
