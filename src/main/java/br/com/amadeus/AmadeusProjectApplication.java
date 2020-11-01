package br.com.amadeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightDestination;

@SpringBootApplication
public class AmadeusProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmadeusProjectApplication.class, args);
	}


	@GetMapping(path = "/buscaLocal")
	public static FlightDestination buscarLocal(@RequestParam(value ="local") String local) {
		Amadeus amadeus = Amadeus.builder("kbfprG6dW8wmitGSL07HAoJ816knV5PM", "T9DGQqxrzARDdAni").build();

		try {
			// Flight Inspiration Search
			FlightDestination[] flightDestinations = amadeus.shopping.flightDestinations
					.get(Params.with("origin", local));
			for (FlightDestination location : flightDestinations) {
				if (location.getOrigin().equals(local)) {
					return location;
				}
			}
			return null;
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
