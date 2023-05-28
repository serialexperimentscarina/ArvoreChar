package br.com.serialexperimentscarina.arvorechar;

public class ArvoreChar implements IArvore{
	
	No raiz;
	
	public ArvoreChar() {
		raiz = null;
	}

	@Override
	public void insert(char letra) {
		No no = new No();
		no.dado = letra;
		no.esquerda = null;
		no.direita = null;
		insertLeaf(no, raiz);
	}

	private void insertLeaf(No no, No raizAtual) {
		if (raiz == null) {
			raiz = no;
		} else if ((int)no.dado < raizAtual.dado) {
			if (raizAtual.esquerda == null) {
				raizAtual.esquerda = no;
			} else {
				insertLeaf(no, raizAtual.esquerda);
			}
		} else if ((int)no.dado >= raizAtual.dado) {
			if (raizAtual.direita == null) {
				raizAtual.direita = no;
			} else {
				insertLeaf(no, raizAtual.direita);
			}
		}
	}

	@Override
	public void search(char letra) throws Exception {
		try {
			No no = nodeSearch(raiz, letra);
			int level = nodeLevel(raiz, letra);
			System.out.println(no + " - [Nível: " + level + " ]");
		} catch (Exception e) {
			throw new Exception("Valor não encontrado!");
		}
		
	}

	private No nodeSearch(No raizAtual, char letra) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else if ((int)letra < raizAtual.dado) {
			return nodeSearch(raizAtual.esquerda, letra);
		} else if ((int)letra > raizAtual.dado) {
			return nodeSearch(raizAtual.direita, letra);
		} else {
			return raizAtual;
		}
	}

	private int nodeLevel(No raizAtual, char letra) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else if ((int)letra < raizAtual.dado) {
			return 1 + nodeLevel(raizAtual.esquerda, letra);
		} else if ((int)letra > raizAtual.dado) {
			return 1 + nodeLevel(raizAtual.direita, letra);
		} else {
			return 0;
		}
	}

	@Override
	public void remove(char letra) throws Exception {
		try {
			removeChild(raiz, letra);
		} catch (Exception e) {
			throw new Exception("Valor não encontrado!");
		}
	}

	private No removeChild(No raizAtual, char letra) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else if ((int)letra < raizAtual.dado) {
			raizAtual.esquerda = removeChild(raizAtual.esquerda, letra);
		} else if ((int)letra > raizAtual.dado) {
			raizAtual.direita = removeChild(raizAtual.direita, letra);
		} else {
			if (raizAtual.esquerda == null && raizAtual.direita == null) {
				raizAtual = null;
			} else if (raizAtual.esquerda == null) {
				raizAtual = raizAtual.direita;
			} else if (raizAtual.direita == null) {
				raizAtual = raizAtual.esquerda;
			} else {
				No no = raizAtual.direita;
				while (no.esquerda != null) {
					no = no.esquerda;
				}
				raizAtual.dado = no.dado;
				no.dado = letra;
				raizAtual.direita = removeChild(raizAtual.direita, letra);
			}
		}
		return raizAtual;
		
	}

	@Override
	public void prefixSearch() throws Exception {
		System.out.println("Busca préfixa: ");
		prefix(raiz);
	}

	private void prefix(No raizAtual) throws Exception{
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else {
			System.out.print(raizAtual.dado + " ");
			if (raizAtual.esquerda != null) {
				prefix(raizAtual.esquerda);
			}
			if (raizAtual.direita != null) {
				prefix(raizAtual.direita);
			}
		}
	}

	@Override
	public void infixSearch() throws Exception {
		System.out.println("Busca infixa: ");
		infix(raiz);
	}

	private void infix(No raizAtual) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else {
			if (raizAtual.esquerda != null) {
				infix(raizAtual.esquerda);
			}
			System.out.print(raizAtual.dado + " ");
			if (raizAtual.direita != null) {
				infix(raizAtual.direita);
			}
		}
	}

	@Override
	public void postfixSearch() throws Exception {
		System.out.println("Busca pósfixa: ");
		postfix(raiz);		
	}

	private void postfix(No raizAtual) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else {
			if (raizAtual.esquerda != null) {
				postfix(raizAtual.esquerda);
			}
			if (raizAtual.direita != null) {
				postfix(raizAtual.direita);
			}
			System.out.print(raizAtual.dado + " ");
		}
	}

}
