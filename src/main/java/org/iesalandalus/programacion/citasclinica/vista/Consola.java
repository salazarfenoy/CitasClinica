package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;

import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("Menú");
		System.out.println("1. Insertar cita");
		System.out.println("2. Buscar cita");
		System.out.println("3. Borrar cita");
		System.out.println("4. Mostrar citas por día");
		System.out.println("5. Mostrar todas las citas");
		System.out.println("0. Salir");

	}

	public static Opciones elegirOpciones() {
		Opciones[] opcion = Opciones.values();
		System.out.print("Elija una opción:");
		int opcionElegida = Entrada.entero();
		while (opcionElegida < 0 || opcionElegida > 5) {
			System.out.print("Por favor, elija una opción correcta(entre 0 y 5):");
			opcionElegida = Entrada.entero();
		}

		return opcion[opcionElegida];
	}

	public static Paciente leerPaciente() throws OperationNotSupportedException {
		Paciente paciente;
		System.out.println("");
		System.out.print("Introduzca el nombre: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduzca el teléfono: ");
		String telefono = Entrada.cadena();
		System.out.print("Introduzca el DNI: ");
		String dni = Entrada.cadena();

		paciente = new Paciente(nombre, dni, telefono);

		return paciente;

	}

	public static LocalDateTime leerFechaHora() {
		String formatoCadena = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
		LocalDateTime fechaHora = null;
		boolean fechaValida = false;
		do {

			try {
				System.out.print("Introduzca una fecha y hora(dd/MM/aaaa HH:mm):");
				fechaHora = LocalDateTime.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);
		return fechaHora;

	}

	public static Cita leerCita() throws OperationNotSupportedException {

		Cita cita = new Cita(leerPaciente(), leerFechaHora());
		return cita;

	}

	public static LocalDate leerFecha() {
		String formatoCadena = "dd/MM/yyyy";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
		LocalDate fecha = null;
		boolean fechaValida = false;
		do {

			try {
				System.out.print("Introduzca una fecha(dd/MM/aaaa):");
				fecha = LocalDate.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);

		return fecha;

	}
}
