package java3dproject;

import javax.media.j3d.Appearance;

import javax.media.j3d.Shape3D;

import com.sun.j3d.utils.geometry.Sphere;


public class Celestial{
	
	
	
	//Atributos
	String nome;
	double raio;
	String massa;
	int periodo_orbital;
	double distancia_sol;
	String descricao;
	String periodo_orbital_string;
	
	
	//Contrutores
	public Celestial(String nome) {
		super();
		this.nome = nome;
		
		if(nome == "Sol") {
			raio = 696340;
			descricao = "O Sol é a estrela central do Sistema Solar. Todos os outros corpos do Sistema Solar, como planetas, planetas anões, asteroides, cometas e poeira, bem como todos os satélites associados a estes corpos, giram ao seu redor.";
			massa = "1,989 × 10^30 kg";
		}
		else if(nome == "Mercúrio") {
			raio = 2439.7;
			descricao = "Mercúrio é o menor e mais interno planeta do Sistema Solar, orbitando o Sol a cada 87,969 dias terrestres. A sua órbita tem a maior excentricidade e o seu eixo apresenta a menor inclinação em relação ao plano da órbita dentre todos os planetas do Sistema Solar.";
			massa = "3,285 × 10^23 kg";
			periodo_orbital = 88;
			distancia_sol = 5791000;
			periodo_orbital_string="88";
		} else if(nome == "Vénus") {
			raio = 6051.8;
			descricao = "Vénus é o segundo planeta do Sistema Solar em ordem de distância a partir do Sol, orbitando-o a cada 224,7 dias. Recebeu seu nome em homenagem à deusa romana do amor e da beleza Vénus, equivalente a Afrodite.";
			massa = "4,867 × 10^24 kg";
			periodo_orbital = 225;
			distancia_sol = 11582000;
			periodo_orbital_string="225";
		} else if(nome == "Terra") {
			raio = 6371;
			descricao = "A Terra é o terceiro planeta mais próximo do Sol, o mais denso e o quinto maior dos oito planetas do Sistema Solar. É também o maior dos quatro planetas telúricos. É por vezes designada como Mundo ou Planeta Azul.";
			massa = "5,972 × 10^24 kg";
			periodo_orbital = 365;
			distancia_sol = 17373000;
			periodo_orbital_string="365";
		} else if(nome == "Lua") {
			raio = 1737.1;
			descricao = "A Lua é o único satélite natural da Terra e o quinto maior do Sistema Solar. É o maior satélite natural de um planeta no sistema solar em relação ao tamanho do seu corpo primário, tendo 27% do diâmetro e 60% da densidade da Terra, o que representa 1/81 da sua massa.";
			massa = "7,349 x 10^22 kg";
			periodo_orbital = 27;
			distancia_sol = 384400;
			periodo_orbital_string="27";
		} else if(nome == "Marte") {
			raio = 3389.5;
			descricao = "Marte é o quarto planeta a partir do Sol, o segundo menor do Sistema Solar. Batizado em homenagem ao deus romano da guerra, muitas vezes é descrito como o Planeta Vermelho, porque o óxido de ferro predominante em sua superfície lhe dá uma aparência avermelhada.";
			massa = "6,39 × 10^23 kg";
			periodo_orbital = 687;
			distancia_sol = 23164000;
			periodo_orbital_string="687";
		} else if(nome == "Júpiter") {
			raio = 69911;
			descricao = "Júpiter é o maior planeta do Sistema Solar, tanto em diâmetro quanto em massa, e é o quinto mais próximo do Sol. Possui menos de um milésimo da massa solar, contudo tem 2,5 vezes a massa de todos os outros planetas em conjunto. É um planeta gasoso, junto com Saturno, Urano e Netuno";
			massa = "1,898 × 10^27 kg";
			periodo_orbital = 4368;
			distancia_sol = 28955000;
			periodo_orbital_string="4368";
		} else if(nome == "Saturno") {
			raio = 58232;
			descricao = "Saturno é o sexto planeta a partir do Sol e o segundo maior do Sistema Solar atrás de Júpiter. Pertencente ao grupo dos gigantes gasosos, possui cerca de 95 massas terrestres e orbita a uma distância média de 9,5 unidades astronômicas.";
			massa = "5,683 × 10^26 kg";
			periodo_orbital = 10585;
			distancia_sol = 34746000;
			periodo_orbital_string="10585";
		} else if(nome == "Urano") {
			raio = 25362;
			descricao = "Urano é o sétimo planeta a partir do Sol, o terceiro maior e o quarto mais massivo dos oito planetas do Sistema Solar.";
			massa = "8,681 × 10^25 kg";
			periodo_orbital = 30660;
			distancia_sol = 40537000;
			periodo_orbital_string="30660";
		} else if(nome == "Neptuno") {
			raio = 24622;
			descricao = "Neptuno é o oitavo planeta do Sistema Solar, o último a partir do Sol. Pertencente ao grupo dos gigantes gasosos, possui um tamanho ligeiramente menor que o de Urano, mas maior massa, equivalente a 17 massas terrestres.";
			massa = "1,024 × 10^26 kg";
			periodo_orbital = 60225;
			distancia_sol = 46328000;
			periodo_orbital_string="60225";
		}		
	}

	//Métodos
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getRaio() {
		return raio;
	}


	public void setRaio(double raio) {
		this.raio = raio;
	}


	public String getMassa() {
		return massa;
	}


	public void setMassa(String massa) {
		this.massa = massa;
	}


	public int getPeriodo_orbital() {
		return periodo_orbital;
	}


	public void setPeriodo_orbital(int periodo_orbital) {
		this.periodo_orbital = periodo_orbital;
	}


	public double getDistancia_sol() {
		return distancia_sol;
	}


	public void setDistancia_sol(double distancia_sol) {
		this.distancia_sol = distancia_sol;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPeriodo_orbital_string() {
		return periodo_orbital_string;
	}

	public void setPeriodo_orbital_string(String periodo_orbital_string) {
		this.periodo_orbital_string = periodo_orbital_string;
	}
	
	
}
