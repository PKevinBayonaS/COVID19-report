package com.COVID19.estadisticascoronavirus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	DataService dataService;
	
	@GetMapping("/covid19")
	public String home(Model model) {
		List<CovidDAO> registers = dataService.getRegisters();
		int total= registers.stream().mapToInt(stat -> stat.getLatestCase()).sum();
		int totalNew= registers.stream().mapToInt(stat -> stat.getDiffCase()).sum();
		model.addAttribute("locationStates", dataService.getRegisters());
		model.addAttribute("totaldeCasosReportados", total);
		model.addAttribute("totaldenuevoscasos", totalNew);
		return"home";
	}
}
