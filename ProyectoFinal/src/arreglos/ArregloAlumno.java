package arreglos;
import java.io.*;
import java.util.ArrayList;

import clases.Alumno;

public class ArregloAlumno {
	
	
private ArrayList <Alumno> alumno;
	
	public ArregloAlumno(){
		alumno=new ArrayList <Alumno>();
		cargarAlumnos();
	}
	
	public void adicionar(Alumno x){
		alumno.add(x);
		grabarAlumno();
	}
	
	public Alumno obtener(int i) {
		return alumno.get(i);
	}
	
	public int tama�o() {
		return alumno.size();
	}
	
	public Alumno buscarDNI(String dni) {
		Alumno x;
		for (int i=0; i<tama�o(); i++) {
			x = obtener(i);
			if (x.getDni().equals(dni))
				return x;
		}
		return null;
	}
	
	
	
	public Alumno buscar(int numeroAlumno) {
		for (int i=0; i<tama�o(); i++)
			if (obtener(i).getCodAlumno() == numeroAlumno)
				return obtener(i);
		return null;
	}
	
	
	
	public void eliminar(Alumno x) {
		alumno.remove(x);
		grabarAlumno();
	}
	
	private void cargarAlumnos() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codAlumno;
			String nombre;
			String apellido;
			String dni;
			int edad;
			int celular;
			int estado;
			
			br = new BufferedReader(new FileReader("alumnos.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				codAlumno = Integer.parseInt(s[0].trim());
				nombre = s[1].trim();
				apellido = s[2].trim();
				dni = s[3].trim();
				edad = Integer.parseInt(s[4].trim());
				celular = Integer.parseInt(s[5].trim());
				estado = Integer.parseInt(s[6].trim());
				
				adicionar(new Alumno(codAlumno, nombre, apellido, dni,edad,celular,estado));	
				
			}
			br.close();	
		}
		catch (Exception e) {
		}
	}
	
	
	private void grabarAlumno() {
		try {
			PrintWriter pw;
			String linea;
			Alumno x;
			pw = new PrintWriter(new FileWriter("alumnos.txt"));
			for(int i=0; i<tama�o(); i++) {
				x = obtener(i);
				linea = x.getCodAlumno() + ";" +
						x.getNombre() + ";" +
						x.getApellido() + ";" +
						x.getDni() + ";" +
						x.getEdad() + ";" +
						x.getCelular() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	
	public int numeroCorrelativo() {
		if (tama�o() == 0)
			return 202010001;
		else
			return obtener(tama�o()-1).getCodAlumno() + 1;
	}

	public void actualizarArchivo() {
		grabarAlumno();
	}
	

}
