package arreglos;

import java.util.ArrayList;
import java.io.*;
import clases.Cursos;

public class ArregloCurso {
	
	
private ArrayList <Cursos> curso;
	
	public ArregloCurso(){
		curso=new ArrayList <Cursos>();
		cargarCursos();
	}
	
	public void adicionar(Cursos x){
		curso.add(x);
		grabarCurso();
	}
	public Cursos obtener(int i) {
		return curso.get(i);
	}
	
	public int tamaño() {
		return curso.size();
	}
	

	private void cargarCursos() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codCurso;
			String asignatura;
			int ciclo;
			int creditos;
			int hora;
			
			br = new BufferedReader(new FileReader("cursos.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				codCurso = Integer.parseInt(s[0].trim());
				asignatura = s[1].trim();
				ciclo = Integer.parseInt(s[2].trim());
				creditos = Integer.parseInt(s[3].trim());
				hora = Integer.parseInt(s[4].trim());
				
				adicionar(new Cursos(codCurso, asignatura, ciclo, creditos, hora));	
				
			}
			br.close();	
		}
		catch (Exception e) {
		}
	}
	
	private void grabarCurso() {
		try {
			PrintWriter pw;
			String linea;
			Cursos x;
			pw = new PrintWriter(new FileWriter("cursos.txt"));
			for(int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodCurso() + ";" +
						x.getAsignatura() + ";" +
						x.getCiclo() + ";" +
						x.getCreditos() + ";" +
						x.getHoras();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	
	public Cursos buscar(int codigoCurso) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodCurso() == codigoCurso)
				return obtener(i);
		return null;
	}
	
	public void eliminar(Cursos x) {
		curso.remove(x);
		grabarCurso();
	}
	
	public void actualizarArchivo() {
		grabarCurso();
	}


}
