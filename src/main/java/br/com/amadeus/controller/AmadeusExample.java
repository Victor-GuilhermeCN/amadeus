package br.com.amadeus.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.Response;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightDestination;


public class AmadeusExample {
	public static void main(String[] args) throws ResponseException {
		FlightDestination resultado = buscarLocal(JOptionPane.showInputDialog("DIGITE O LOCAL"));
		if(resultado != null) {
			System.out.println(resultado);
		}else {
			System.out.println("NÃO FOI ENCOTRADO NENHUMA INFORMAÇÃO");
		}
		
	}

	public static FlightDestination buscarLocal(String local) {
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