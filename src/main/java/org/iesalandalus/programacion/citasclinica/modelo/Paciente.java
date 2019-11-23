package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {

	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "(9|6)[0-9]{8}";
	private String nombre;
	private String dni;
	private String telefono;

	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);

	}

	public Paciente(Paciente paciente) {

		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		}
		setNombre(paciente.nombre);
		setDni(paciente.dni);
		setTelefono(paciente.telefono);
	}

	private String formateaNombre(String nombre) {
		nombre = nombre.replaceAll("( )+", " ");
		nombre = nombre.trim();

		String[] palabras = nombre.split(" ");
		StringBuilder copiaNombre = new StringBuilder();
		for (int i = 0; i <= palabras.length - 1; i++) {

			palabras[i] = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();

			copiaNombre.append(palabras[i] + " ");
		}

		nombre = copiaNombre.toString();
		return nombre.trim();
	}

	private boolean comprobarLetraDni(String dni) {
		boolean esCorrecto;
		esCorrecto = false;

		Pattern patronDni = Pattern.compile(ER_DNI);

		Matcher comparadorDni = patronDni.matcher(dni);
		comparadorDni.matches();

		int numeroDni = Integer.parseInt(comparadorDni.group(1));

		int resto = numeroDni % 23;

		String[] letraEsperada = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
				"V", "H", "L", "C", "K", "E" };

		if (comparadorDni.group(2).equals(letraEsperada[resto])) {

			esCorrecto = true;

		}
		return esCorrecto;

	}

	private String getIniciales() {

		String iniciales = "";
		String[] palabras = getNombre().split(" ");
		for (int i = 0; i <= palabras.length - 1; i++) {

			iniciales = iniciales + palabras[i].charAt(0);
		}

		return iniciales.toUpperCase();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {

		if (nombre == null || nombre.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}
		this.nombre = formateaNombre(nombre);
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null || dni.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (comprobarLetraDni(dni) == false) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}

		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}

		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String toString = String.format("nombre=%s (%s), DNI=%s, teléfono=%s", getNombre(), getIniciales(), getDni(),
				getTelefono());
		return toString;
	}

}
