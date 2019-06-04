package br.com.caelum.estoque.cliente;

public class TesteServicoWeb {

	public TesteServicoWeb() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EstoqueWS cliente = new EstoqueWS_Service().getEstoqueWSPort();
		Filtros filtros = new Filtros();
		Filtro filtro = new Filtro();
		filtro.setNome("IPhone");
		filtro.setTipo("Livro");
		filtros.getFiltro().add(filtro);
		
		ListaItens itens = cliente.todosOsItens(filtros);
		
		System.out.println(itens);

	}

}
