package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {
	public static final int NUM_MAX_CITAS = 10;
	static Citas citasClinica = new Citas(NUM_MAX_CITAS);

	private static void insertarCita() throws OperationNotSupportedException {

		try {
			Cita cita = Consola.leerCita();
			citasClinica.insertar(cita);
			System.out.println("");
			System.out.println("Cita asignada correctamente.");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			System.out.println("--Vuelva a introducir los datos--");
			System.out.println("");
			insertarCita();
		}

	}

	private static void buscarCita() throws OperationNotSupportedException {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fecha = Consola.leerFechaHora();
		Paciente paciente = new Paciente("paciente", "76633207J", "666666666");
		Cita cita = new Cita(paciente, fecha);
		Cita citaComprobada;
		citaComprobada = citasClinica.buscar(cita);

		if (citaComprobada == null) {
			System.out.println("");
			System.out.println("No existen citas para la fecha: " + fecha.format(formato));
			System.out.println("");
		} else {

			Cita[] citas = citasClinica.getCitas(fecha.toLocalDate());
			for (Cita citaPasada : citas) {
				if (cita.equals(citaPasada)) {
					System.out.println("");
					System.out.println("La cita es " + citaPasada);
					System.out.println("");
				}
			}

		}

		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpciones());
	}

	private static void borrarCita() throws OperationNotSupportedException {

		LocalDateTime fechaHora = Consola.leerFechaHora();
		Paciente paciente = new Paciente("paciente", "76633207J", "666666666");
		Cita cita = new Cita(paciente, fechaHora);
		try {

			citasClinica.borrar(cita);
			System.out.println("");
			System.out.println("Cita borrada.");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());

		} catch (OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());
		}

	}

	private static void mostrarCitas() throws OperationNotSupportedException {
		System.out.println("");
		System.out.println("Lista de todas las citas");
		System.out.println("");

		if (citasClinica.getTamano() == 0) {
			System.out.println("");
			System.out.println("--No hay citas para mostrar--");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());
		} else {
			Cita[] citas = citasClinica.getCitas();

			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());
		}
	}

	private static void mostrarCitasDia() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = Consola.leerFecha();
		System.out.println("");
		System.out.println("Lista de citas para fecha: " + fecha.format(formato));
		System.out.println("");
		Cita[] citasFecha = citasClinica.getCitas(fecha);
		boolean hayCitasFecha = false;
		for (int i = 0; i <= citasFecha.length - 1; i++) {
			if (citasFecha[i] != null) {
				hayCitasFecha = true;
			}
		}

		if (citasFecha.length == 0 || !hayCitasFecha) {
			System.out.println("--No hay citas para esta fecha--");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());
		} else {

			for (Cita cita : citasFecha) {
				if (cita != null) {
					System.out.println(cita);

				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpciones());
		}
	}

	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
		switch (opcion) {
		case SALIR:
			System.out.print("Cerrando sesión.");
			break;
		case INSERTAR_CITA:
			insertarCita();
			break;
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITAS_DIA:
			mostrarCitasDia();
			break;
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;

		}
	}

	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar las citas de la Clínica.");
		System.out.println("");
		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpciones());
	}

}
