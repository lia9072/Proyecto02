package clases;

public class Matricula {
	
	private int codMatricula;
	private int codAlumno;
	private int codCurso;
	private String fecha;
	private String hora;
	private int estado;
	
	public Matricula (int codMatricula,String fecha,String hora,int codAlumno, int codCurso,int estado){
		this.codMatricula=codMatricula;
		this.fecha=fecha;
		this.hora=hora;
		this.codAlumno=codAlumno;
		this.codCurso=codCurso;	
		this.estado=estado;
	}

	public int getCodMatricula() {
		return codMatricula;
	}

	public void setCodMatricula(int codMatricula) {
		this.codMatricula = codMatricula;
	}

	public int getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(int codAlumno) {
		this.codAlumno = codAlumno;
	}

	public int getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	

}
