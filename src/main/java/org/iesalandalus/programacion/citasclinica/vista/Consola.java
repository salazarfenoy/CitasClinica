package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {
		
	}
	
	public static void mostrarMenu() {
		System.out.println("Menú");
		System.out.println("1. Insertar");
		System.out.println("2. Buscar");
		System.out.println("3. Borrar");
		System.out.println("4. Mostrar citas por día");
		System.out.println("5. Mostrar todas las citas");
		System.out.println("0. Salir");
	}
	
	public static Opciones elegirOpciones() {
		Opciones[] opcion= Opciones.values();
		System.out.print("Elija una opción:");
		int opcionElegida=Entrada.entero();
		while (opcionElegida<0 || opcionElegida>5) {
			System.out.print("Por favor, elija una opción correcta(entre 0 y 5):");
			opcionElegida=Entrada.entero();
		}
		
		return opcion[opcionElegida];
	}
	
	public static Paciente leerPaciente() throws OperationNotSupportedException {
		Paciente paciente;
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
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);
		System.out.print("Introduce una fecha y hora(AAAA, MM, DD, HH:mm)");
		String fechaHoraPedida = Entrada.cadena();
		
		while (!fechaHoraPedida.matches(Cita.FORMATO_FECHA_HORA)) {
			System.out.print("Introduce una fecha y hora(AAAA, MM, DD, HH:mm)");
			fechaHoraPedida = Entrada.cadena();
		}
		LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraPedida, formato);
		
		
		return fechaHora;
		
	}
	
	public static Cita leerCita() throws OperationNotSupportedException {
	leerPaciente();
		System.out.print("");
		leerFechaHora();
		
		Cita cita = new Cita(leerPaciente(), leerFechaHora());
		return cita;
		
	}
	
	public static LocalDate leerFecha() {
		String formatoCadena = ("yyyy, MM, dd");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoCadena);
		System.out.print("Introduce una fecha y hora(AAAA, MM, DD, HH:mm)");
		String fechaPedida = Entrada.cadena();
		
		while (!fechaPedida.matches(formatoCadena)) {
			System.out.print("Introduce una fecha y hora(AAAA, MM, DD, HH:mm)");
			fechaPedida = Entrada.cadena();
		}
		LocalDate fecha = LocalDate.parse(fechaPedida, formato);
		
		
		
		return fecha;
		
	}
}
