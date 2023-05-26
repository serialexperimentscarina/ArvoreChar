package br.com.serialexperimentscarina.arvorechar;

public interface IArvore {
	
	public void insert(char letra);
	public void search(char letra) throws Exception;
	public void remove(char letra) throws Exception;
	public void prefixSearch() throws Exception;
	public void infixSearch() throws Exception;
	public void postfixSearch() throws Exception;

}
