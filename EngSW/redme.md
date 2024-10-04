01/09/24: Vemos três diferenças críticas entre programação e engenharia de software: tempo, escala e as compensações em jogo. 
Em um projeto de engenharia de software, os engenheiros precisam estar mais preocupados com a passagem do tempo e a eventual necessidade de mudança. 
Em uma organização de engenharia de software, precisamos estar mais preocupados com escala e eficiência, tanto para o software que produzimos quanto para a organização 
que o está produzindo. Finalmente, como engenheiros de software, somos solicitados a tomar decisões mais complexas com resultados de maior risco,
geralmente com base em estimativas imprecisas de tempo e crescimento.

Comentário sobre:
Essa frase basicamente explica que programação e engenharia de software são coisas diferentes. Programar é escrever códigos, resolver problemas técnicos mais imediatos. Já engenharia de software envolve pensar no longo prazo, em como manter e adaptar o software com o passar do tempo, e como fazer tudo isso funcionar de maneira eficiente em grandes projetos e equipes. Também mostra que as decisões na engenharia de software são mais complexas e podem ter um impacto maior, porque estão lidando com variáveis mais incertas, como o tempo necessário para desenvolver e a escalabilidade do software.

12/08/24: - Atividade Trade Off.
Cite 3 exemplos de trade off que foram ou não citados anteriormente na aula

Conservação de Energia vs. Conforto: Uma pessoa pode reduzir o uso de aquecimento ou ar-condicionado para economizar energia e reduzir custos, mas isso pode diminuir o conforto dentro de casa. Alternativamente, aumentar o uso desses dispositivos melhora o conforto, mas aumenta o consumo de energia e os custos.

Crescimento Rápido vs. Estabilidade: Uma empresa pode decidir crescer rapidamente investindo pesadamente em expansão, o que pode aumentar o risco de instabilidade financeira. Em contrapartida, uma abordagem mais cautelosa e estável pode garantir segurança financeira, mas o crescimento pode ser mais lento.

Segurança vs. Liberdade: Em políticas públicas, aumentar a segurança, como a implementação de vigilância mais rigorosa ou restrições de movimento, pode reduzir a liberdade individual. Por outro lado, priorizar a liberdade pode significar menos controle e, potencialmente, menos segurança.


19/08/24: Atividade Java
Classe Empresa

  package engsw;
  
  import java.util.LinkedList;
  import java.util.List;

  
  public class Empresa {
  
  	private List<Funcionario> funcionarios = new LinkedList<Funcionario>();
  	
  	public void cadastrarFuncionario(Funcionario funcionario) {
  		funcionarios.add(funcionario);
  	}
  	
  	public List<Funcionario> buscarFuncionarioporCpf(String cpf){
  		List<Funcionario> funcionariosEncontrados = new LinkedList<Funcionario>();
  		
  		for(Funcionario funcionario:this.funcionarios) {
  			if(funcionario.getCpf().equals(cpf))
                funcionariosEncontrados.add(funcionario);
  		}
  			
  		
  		return funcionariosEncontrados;
  	}
	


 Classe Funcionário
 
 package engsw;

  public class Funcionario {
   private String nome;
   private String cpf;
   
   public Funcionario(String cpf, String nome) {
   
    this.cpf = cpf;
   	this.nome = nome;
    }
    public String getCpf() {
   	return cpf;
    }
    public String GetNome() {
   	return nome;
    }
    }



Classe TesteEmpresa

package engsw;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TesteEmpresa {

@Test 
void test() {
Empresa CeA = new Empresa();
 
	Funcionario Tecnica = new Funcionario("43943509855", "Bia");
	Funcionario Desenvolvedor = new Funcionario("54583098549", "Tiago");
	
	CeA.cadastrarFuncionario(Tecnica);
	CeA.cadastrarFuncionario(Desenvolvedor);
	
	assertEquals(CeA.getFuncionarios().size(), 2);
	
	List<Funcionario> Desenvolvedores = CeA.buscarFuncionarioporCpf("54583098549");
	assertEquals(Desenvolvedores.get(0).getCpf(),Desenvolvedor.getCpf());
	
	}
 }
